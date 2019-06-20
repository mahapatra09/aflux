
export const DESELECT_NODE='DESELECT_NODE';
export const DESELECT_LINK='DESELECT_LINK';
export const BEGIN_DRAG_FOOTER='BEGIN_DRAG_FOOTER';
export const FINISH_DRAG_FOOTER='FINISH_DRAG_FOOTER';
export const CLOSE_PLUGIN_UPLOADER_DIALOG='CLOSE_PLUGIN_UPLOADER_DIALOG'

export const CHANGE_FOOTER_Y_POSITION='CHANGE_FOOTER_Y_POSITION';

export function changeFooterYPositionAction(newYPosition) {
  return {
    type: CHANGE_FOOTER_Y_POSITION,
    footerYPosition:newYPosition
  }
}

export const SET_INFO_PROPERTIES='SET_INFO_PROPERTIES';

export function setInfoPropertiesAction(infoPropertiesState) {
  return {
    type: SET_INFO_PROPERTIES,
    infoProperties:infoPropertiesState
  }
}

export const SET_TIMEOUT_HANDLE='SET_TIMEOUT_HANDLE';

export function setTimeoutHandleAction(handle) {
  return {
    type: SET_TIMEOUT_HANDLE,
    handle:handle
  }
}



export const SET_RUNNING_PROCESSES='SET_RUNNING_PROCESSES';

export function showRunningProcessesAction(processes) {
  return {
    type: SET_RUNNING_PROCESSES,
    processes:processes
  }
}


export const SET_OUTPUT='SET_OUTPUT'

export function setOutputAction(messages) {
  return {
    type: SET_OUTPUT,
    messages:messages
  }
}



export const SET_TOOLSET='SET_TOOLSET'

export function setToolsetAction(toolSet) {
  return {
    type: SET_TOOLSET,
    toolSet:toolSet
  }
}


export const SET_UPDATE_CURRENT_JOB='SET_UPDATE_CURRENT_JOB'

export function setUpdateCurrentJobAction(job) {
  return {
    type: SET_UPDATE_CURRENT_JOB,
    job:job
  }
}



export const OPEN_PLUGIN_MANAGER='OPEN_PLUGIN_MANAGER'

export function openPluginManagerAction(newPluginList) {
  return {
    type: OPEN_PLUGIN_MANAGER,
    newPluginList:newPluginList,
    pluginManagerPopupOpened:true
  }
}


export const SET_ACTIVITY_BASE_PROPERTIES='SET_ACTIVITY_BASE_PROPERTIES'

export function setActivityBasePropertiesAction(basePropertiesArray,currentActivity,activities) {
  return {
    type: SET_ACTIVITY_BASE_PROPERTIES,
    activityBaseProperties:basePropertiesArray,
    currentActivity:currentActivity,
    activities:activities
  }
}


export const SELECT_NODE_ACTION='SELECT_NODE_ACTION'

export function selectNodeAction(id,p,flowElement) {
  return {
    type:SELECT_NODE_ACTION,
    position:p,
    flowElement:flowElement,
    id:id
  }
}


export const CREATE_NODE_ACTION='CREATE_NODE_ACTION'


export function createNodeAction(p,absolute,typeId,newNode) {
  return {
    type:CREATE_NODE_ACTION,
    p:p,
    absolute:absolute,
    typeId:typeId,
    newNode:newNode
  };
}

  export const MOVE_NODE_ELEMENT='MOVE_NODE_ELEMENT';


  export function moveNodeElementAction(flowElements) {
    return {
      type:MOVE_NODE_ELEMENT,
      flowElements:flowElements
    };
  };


  export const FINISH_MOVE_NODE_ELEMENT='FINISH_MOVE_NODE_ELEMENT';


  export function finishMoveNodeElementAction() {
    return {
      type:FINISH_MOVE_NODE_ELEMENT,
    };
  };



  export const DESELECT_TOOL='DESELECT_TOOL';


  export function deselectToolAction(flowElements) {
    return {
      type:DESELECT_TOOL,
      flowElements:flowElements
    };
  };

  export const MOVE_TOOL='MOVE_TOOL';


  export function moveToolAction(newNode,absolutePosition) {
    return {
      type:MOVE_TOOL,
      newNode:newNode,
      absolutePosition:absolutePosition
    };
  };


  export const SET_FLOW_ACTIVITIES='SET_FLOW_ACTIVITIES'

  export function setFlowActivitiesAction(activities,currentActivityIndex,flowElements,flowConnectors,connectorPositions) {
    return {
      type: SET_FLOW_ACTIVITIES,
      activities:activities,
      currentActivityIndex:currentActivityIndex,
      flowElements:flowElements,
      flowConnectors:flowConnectors,
      connectorPositions:connectorPositions
    }
  }

  export const OPEN_NEW_ACTIVITY_DIALOG='OPEN_NEW_ACTIVITY_DIALOG'

  export function openNewActivityDialogAction() {
    return {
      type: OPEN_NEW_ACTIVITY_DIALOG
    }
  }


  export const CLOSE_NEW_ACTIVITY_DIALOG='CLOSE_NEW_ACTIVITY_DIALOG'

  export function closeNewActivityDialogAction() {
    return {
      type: CLOSE_NEW_ACTIVITY_DIALOG
    }
  }

  export const CREATE_NEW_ACTIVITY='CREATE_NEW_ACTIVITY'

  export function createNewActivityAction(currentActivityIndex,activities) {
    return {
      type: CREATE_NEW_ACTIVITY,
      currentActivityIndex:currentActivityIndex,
      activities:activities,
    }
  }



  export const OPEN_REMOVE_ACTIVITY_DIALOG='OPEN_REMOVE_ACTIVITY_DIALOG'

  export function openRemoveActivityDialogAction() {
    return {
      type: OPEN_REMOVE_ACTIVITY_DIALOG
    }
  }


  export const CLOSE_REMOVE_ACTIVITY_DIALOG='CLOSE_REMOVE_ACTIVITY_DIALOG'

  export function closeRemoveActivityDialogAction() {
    return {
      type: CLOSE_REMOVE_ACTIVITY_DIALOG
    }
  }



  export const REMOVE_ACTIVITY='REMOVE_ACTIVITY'

  export function removeActivityAction(activities,currentActivityIndex) {
    return {
      type: REMOVE_ACTIVITY,
      activities:activities,
      currentActivityIndex:currentActivityIndex
    }
  }





  export const SELECT_ACTIVITY='SELECT_ACTIVITY'

  export function selectActivityAction(activities,currentActivityIndex,connectorPositions) {
    return {
      type: SELECT_ACTIVITY,
      activities:activities,
      currentActivityIndex:currentActivityIndex,
      connectorPositions:connectorPositions
    }
  }




  export const CHANGE_ACTIVITY_PROPERTY='CHANGE_ACTIVITY_PROPERTY'

  export function changeActivityPropertyAction(currentActivity,newName) {
    return {
      type: CHANGE_ACTIVITY_PROPERTY,
      currentActivity:currentActivity,
      name:newName
    }
  }


  export const ADD_SUBFLOW_ACTIVITY='ADD_SUBFLOW_ACTIVITY'

  export function addSubFlowActivityAction(activity,parentActivityId,parentNodeId) {
    return {
      type: ADD_SUBFLOW_ACTIVITY,
      activity:activity,
      parentActivityId:parentActivityId,
      parentNodeId:parentNodeId
    }
  }

  export const REMOVE_SUBFLOW_ACTIVITY='REMOVE_SUBFLOW_ACTIVITY'

  export function removeSubFlowActivityAction(activityId) {
    return {
      type: REMOVE_SUBFLOW_ACTIVITY,
      activityId:activityId,
    }
  }
