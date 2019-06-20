import { SET_ACTIVITY_BASE_PROPERTIES } from '../redux-actions/aFluxActionTypes';
import { SET_ELEMENT_BASE_PROPERTIES } from '../redux-actions/aFluxActionTypes';
import { SET_SETTINGS } from '../redux-actions/aFluxActionTypes';
import { SET_HOST } from '../redux-actions/aFluxActionTypes';



/**
 * This is a reducer, a pure function with (state, action) => state signature.
 * It describes how an action transforms the state into the next state.
 *
 * The shape of the state is up to you: it can be a primitive, an array, an object,
 * or even an Immutable.js data structure. The only important part is that you should
 * not mutate the state object, but return a new object if the state changes.
 *
 * In this example, we use a `switch` statement and strings, but you can use a helper that
 * follows a different convention (such as function maps) if it makes sense for your
 * project.
 */
export function mainReducer(
  state = {
    flowElements:[],
    flowConnectors:[],
    settings:{
      nodeElement:{
        width:120,
        height:28,
        topConnectorMargin:10,
        bottomConnectorMargin:10,
        leftConnectorMargin:10,
        connectorDistance:15,
        rightConnectorMargin:0,
        textHeight:12
      }
    },
    sampleState:0
  },
  action) {
  switch (action.type) {
  case SET_ACTIVITY_BASE_PROPERTIES:
    return Object.assign({}, state, {
      sampleState: 2
    });
  case SET_ELEMENT_BASE_PROPERTIES:
    return Object.assign({},state,{
      elementBaseProperties:action.basePropertiesArray
    });
  case SET_SETTINGS:
//      return Object.assign({},state,{settings:Object.assign({},state.settings,{nodeElement:action.nodeSettings})
      return Object.assign({},state,
          {settings:{...state.settings,nodeElement:action.nodeSettings}});
  case SET_HOST:
          return Object.assign({},state,{
            host:action.host
          });
  default:
    return state
  }
}
