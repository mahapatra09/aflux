import { connect } from 'react-redux'

import { setSettingsAction } from '../redux-actions/aFluxActionTypes';
import { setElementBasePropertiesAction } from '../redux-actions/aFluxActionTypes';
import ReduxAsyncFlowsApp from '../redux-components/ReduxAsyncFlowsApp'


import { refreshTools } from './ToolContainerFunctions';
import { refresh } from './ReduxWorkspaceContainerFunctions';
import { setInitialActivityProperties } from './ActivityContainerFunctions';


var client = require('../functions/client');

export function exComponentDidMount(store,host) {
  client({method: 'GET',
    mode:'no-cors',
    path: host+'/settings'}).done(
      response => {window.reduxStore.dispatch(setSettingsAction(response.entity.nodeElement))});


  client({method: 'GET',
        mode:'no-cors',
        path: host+'/elementBaseProperties'}).done(
          response => {window.reduxStore.dispatch(setElementBasePropertiesAction(response.entity))});

  setInitialActivityProperties(store,host,
      window.reduxStore.getState().aFlux.currentActivity,
      window.reduxStore.getState().aFlux.activities,
      window.reduxStore.getState().aFlux.currentActivityIndex);
  refreshTools(host);
  refresh(host);
}

const mapStateToProps = state => {
  return {
    elementBaseProperties:state.aFlux.elementBaseProperties,
    settings:state.aFlux.settings,
    host:state.aFlux.host,
    aFlux:state.aFlux
  }
}

const mapDispatchToProps = dispatch => {
  return {}
}

const ReduxAsyncFlowsAppContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxAsyncFlowsApp)



export default ReduxAsyncFlowsAppContainer
