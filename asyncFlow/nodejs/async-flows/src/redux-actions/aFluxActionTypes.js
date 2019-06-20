





export const SET_ELEMENT_BASE_PROPERTIES='SET_ELEMENT_BASE_PROPERTIES'


export function setElementBasePropertiesAction(basePropertiesArray) {
  return {
    type: SET_ELEMENT_BASE_PROPERTIES,
    basePropertiesArray:basePropertiesArray
  }
}

export const SET_HOST='SET_HOST'


export function setHostAction(hostUrl) {
  return {
    type: SET_HOST,
    host:hostUrl
  }
}


export const SET_SETTINGS='SET_SETTINGS'


export function setSettingsAction(nodeSettings) {
  return {
    type: SET_SETTINGS,
    nodeSettings:nodeSettings
  }
}
export const APPLY_GENERAL_ACTION='APPLY_GENERAL_ACTION'
export function applyGeneralActionAction(objectValue,title) {
  if (title==undefined) {
    console.log("Warning APPLY_GENERAL_ACTION undefined");
  }
  return Object.assign({},{type: APPLY_GENERAL_ACTION,value:objectValue,logActionValue:title});
}
