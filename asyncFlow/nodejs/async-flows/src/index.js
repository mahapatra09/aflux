
import React from 'react';
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore } from 'redux'
import  mainReducer  from './redux-reducers/aFluxMainReducer';
import {exComponentDidMount} from './redux-containers/ReduxAsyncFlowsAppContainer';
import ReduxAsyncFlowsAppContainer from './redux-containers/ReduxAsyncFlowsAppContainer'
import { setHostAction } from './redux-actions/aFluxActionTypes';





//import AsyncFlowsApp from './AsyncFlowsApp';


// import todoApp from './reducers'
// import App from './components/App'
// Create a Redux store holding the state of your app.
// Its API is { subscribe, dispatch, getState }.
var store = createStore(mainReducer)
var host="http://localhost:8080/";
store.dispatch(setHostAction(host));

// You can use subscribe() to update the UI in response to state changes.
// Normally you'd use a view binding library (e.g. React Redux) rather than subscribe() directly.
// However it can also be handy to persist the current state in the localStorage.
store.subscribe(() =>
  console.log(store.getState())
)
window.reduxStore=store;
console.log(window.reduxStore);


// saveStore(store);

render(
  <Provider store={store}>
    <ReduxAsyncFlowsAppContainer />
  </Provider>,
  document.getElementById('root')
);
exComponentDidMount(store,host);
