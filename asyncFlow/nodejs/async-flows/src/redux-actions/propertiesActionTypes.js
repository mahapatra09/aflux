
export const SET_HELP='SET_HELP';

export function setHelpAction(hint,help) {
  return {
    type: SET_HELP,
    hint: hint,
    help:help
  }
}


export const CHANGE_NODE_PROPERTY='CHANGE_NODE_PROPERTY'

export function changeNodePropertyAction(activityId,nodeId,propertyId,value) {
  return {
    type: CHANGE_NODE_PROPERTY,
    activityId:activityId,
    nodeId:nodeId,
    propertyId:propertyId,
    value:value
  }
}
