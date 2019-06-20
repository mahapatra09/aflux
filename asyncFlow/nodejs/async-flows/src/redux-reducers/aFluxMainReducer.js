import { combineReducers } from 'redux'
import AsyncFlowsAppReducer from './AsyncFlowsAppReducer';
import WorkspaceReducer from './WorkspaceReducer';

const mainReducer = combineReducers({
  aFlux:AsyncFlowsAppReducer,
  workspace:WorkspaceReducer
})

export default mainReducer
