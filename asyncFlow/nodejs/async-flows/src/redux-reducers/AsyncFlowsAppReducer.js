
// actions
import { SET_ELEMENT_BASE_PROPERTIES } from '../redux-actions/aFluxActionTypes';
import { SET_SETTINGS } from '../redux-actions/aFluxActionTypes';
import { SET_HOST } from '../redux-actions/aFluxActionTypes';
import { APPLY_GENERAL_ACTION } from '../redux-actions/aFluxActionTypes';


import { DESELECT_NODE } from '../redux-actions/workspaceActionTypes';
import { DESELECT_LINK } from '../redux-actions/workspaceActionTypes';
import { CHANGE_FOOTER_Y_POSITION } from '../redux-actions/workspaceActionTypes';
import { SET_INFO_PROPERTIES } from '../redux-actions/workspaceActionTypes';
import { SET_TIMEOUT_HANDLE } from '../redux-actions/workspaceActionTypes';
import { SET_OUTPUT } from '../redux-actions/workspaceActionTypes';
import { SET_ACTIVITY_BASE_PROPERTIES } from '../redux-actions/workspaceActionTypes';
import { SET_RUNNING_PROCESSES } from '../redux-actions/workspaceActionTypes';
import { SET_UPDATE_CURRENT_JOB } from '../redux-actions/workspaceActionTypes';
import { CLOSE_PLUGIN_UPLOADER_DIALOG } from '../redux-actions/workspaceActionTypes';
import { OPEN_PLUGIN_MANAGER } from '../redux-actions/workspaceActionTypes';
import { SET_TOOLSET } from '../redux-actions/workspaceActionTypes';
import { SELECT_NODE_ACTION } from '../redux-actions/workspaceActionTypes';
import { CREATE_NODE_ACTION } from '../redux-actions/workspaceActionTypes';
import { MOVE_NODE_ELEMENT } from '../redux-actions/workspaceActionTypes';
import { FINISH_MOVE_NODE_ELEMENT } from '../redux-actions/workspaceActionTypes';
import { BEGIN_DRAG_FOOTER } from '../redux-actions/workspaceActionTypes';
import { FINISH_DRAG_FOOTER } from '../redux-actions/workspaceActionTypes';
import { DESELECT_TOOL } from '../redux-actions/workspaceActionTypes';
import { MOVE_TOOL } from '../redux-actions/workspaceActionTypes';
import { REMOVE_SUBFLOW_ACTIVITY } from '../redux-actions/workspaceActionTypes';


import { SET_FLOW_ACTIVITIES } from '../redux-actions/workspaceActionTypes';
import { OPEN_NEW_ACTIVITY_DIALOG } from '../redux-actions/workspaceActionTypes';
import { CLOSE_NEW_ACTIVITY_DIALOG } from '../redux-actions/workspaceActionTypes';
import { CREATE_NEW_ACTIVITY } from '../redux-actions/workspaceActionTypes';
import { OPEN_REMOVE_ACTIVITY_DIALOG } from '../redux-actions/workspaceActionTypes';
import { CLOSE_REMOVE_ACTIVITY_DIALOG } from '../redux-actions/workspaceActionTypes';
import { REMOVE_ACTIVITY } from '../redux-actions/workspaceActionTypes';
import { SELECT_ACTIVITY } from '../redux-actions/workspaceActionTypes';
import { CHANGE_ACTIVITY_PROPERTY } from '../redux-actions/workspaceActionTypes';
import { ADD_SUBFLOW_ACTIVITY } from '../redux-actions/workspaceActionTypes';


import { SET_HELP } from '../redux-actions/propertiesActionTypes';
import { CHANGE_NODE_PROPERTY } from '../redux-actions/propertiesActionTypes';



// model
import FlowJob from '../model/FlowJob';
import FlowActivity from '../model/FlowActivity';


// functions
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';
import { changeNodeProperty } from '../redux-containers/ElementContainerFunctions';
import { findElementIndex } from '../redux-containers/ElementContainerFunctions';
import { findSubFlowActivity } from '../redux-containers/ActivityContainerFunctions';



function getInitialNodeElementSettings() {
  return {
    width:120,
    height:28,
    topConnectorMargin:10,
    bottomConnectorMargin:10,
    leftConnectorMargin:10,
    connectorDistance:15,
    rightConnectorMargin:0,
    textHeight:12
  }
}

function generateInitialFlowJob() {
  return new FlowJob(-1,"_NewJob",[new FlowActivity(1,"activity-1",1,[],[],[],-1,-1)]);
}


export function AsyncFlowsAppReducer(
  state = {
    flowElements:[],
    flowConnectors:[],
    settings:{
      nodeElement:getInitialNodeElementSettings()
    },

// workspace properties
    sideBarWidth:"148",
    textElementHeight:"12",
    connectorPositions:
      generateConnectorPositions([],[],
        0,0,
      getInitialNodeElementSettings(),
      "left-right"),
    isMovingNode:false,
    selectedNodeId:-1,
    selectedNodeElement:null,
    isCreatingNode:false,
    selectedToolId:null,
    isCreatingLink:false,
    selectedSourceId:-1,
    selectedSourceIndex:-1,
    endLinkPosition:null,
    selectedConnectorId:-1,
    mouseDeltaX:0,
    mouseDeltaY:0,
    currentJob:generateInitialFlowJob(),
    jobName:generateInitialFlowJob().name,
    activities:generateInitialFlowJob().activities,
    currentActivityIndex:0,
    currentActivity:generateInitialFlowJob().activities[0],
    currentPropertyValues:[],
    stdOutput:[],
    runningProcesses:[],
    newActivityDialogOpened:false,
    jobSelectorOpened:false,
    pluginManagerPopupOpened:false,
    pluginUploaderOpened:false,
    removeActivityDialogOpened:false,
    toolSet:[],
    isDraggingFooter:false,
    footerYPosition:254,
    infoProperties:"RUNNING PROCESSES",
    // list of available subflows they point to activity name (of the component),parent activity, parent node
    componentLinks:[]
  },
  action) {
    console.log("reducer");
    console.log(action.type);
    console.log(action);
  switch (action.type) {


  case SET_ELEMENT_BASE_PROPERTIES:
    return Object.assign({},state,{
      elementBaseProperties:action.basePropertiesArray
    });
  case SET_SETTINGS:
      return Object.assign({},state,
          {settings:{...state.settings,nodeElement:action.nodeSettings}});
  case SET_HOST:
          return Object.assign({},state,{
            host:action.host
          });
  // workspace actions
  case DESELECT_NODE:
        return Object.assign({},state,{
          selectedNodeId:-1,
    			selectedNodeElement:null
        });
  case DESELECT_LINK:
      return Object.assign({},state,{
        selectedConnectorId:-1,
      });
  case CHANGE_FOOTER_Y_POSITION:
      return Object.assign({},state,{
          footerYPosition:action.footerYPosition,
      });
  case SET_INFO_PROPERTIES:
          return Object.assign({},state,{
              infoProperties:action.infoProperties,
          });
  case SET_ACTIVITY_BASE_PROPERTIES:
    return Object.assign({},state,{
      activityBaseProperties:action.activityBaseProperties,
      currentActivity:action.currentActivity,
      activities:action.activities
    });
  case SET_TOOLSET:
      return Object.assign({},state,{
        toolSet:action.toolSet
      });
  case SET_TIMEOUT_HANDLE:
    return Object.assign({},state,{
      lastTimeout:action.handle
    });

  case SET_OUTPUT:
      return Object.assign({},state,{
        stdOutput:action.messages
      });
  case SET_RUNNING_PROCESSES:
          return Object.assign({},state,{
              runningProcesses:action.processes
          });
  case SET_UPDATE_CURRENT_JOB:
    return Object.assign({},state,{
      currentJob:action.job,
      jobName:action.job.name,
      activities:action.job.activities
    });

  // DIRECT SETTINGS with no parameters
  // used for no argument actions or while migration to redux is not finished
  case APPLY_GENERAL_ACTION:
      return Object.assign({},state,action.value);
  case CLOSE_PLUGIN_UPLOADER_DIALOG:
      return Object.assign({},state,{
        pluginUploaderOpened:false,
      });
  case OPEN_PLUGIN_MANAGER:
    return Object.assign({},state,{
      pluginList:action.newPluginList,
      pluginManagerPopupOpened:true
    });
   case SELECT_NODE_ACTION:
   return Object.assign({},state,{
     mouseDeltaX:action.position.x-action.flowElement.x,
     mouseDeltaY:action.position.y-action.flowElement.y,
     selectedNodeId:action.id,
     selectedConnectorId:-1,
     selectedNodeElement:action.flowElement,
     currentPropertyValues:action.flowElement.propertyValues,
     infoProperties:"NODE"
   });

   case CREATE_NODE_ACTION:
   return Object.assign({},state,{
     mouseDeltaX:action.p.x-action.absolute.x,
     mouseDeltaY:action.p.y-action.absolute.y,
     selectedToolId:action.typeId,
     newNode:action.newNode,
     absolutePosition:action.absolute
   });

   case MOVE_NODE_ELEMENT:
   return Object.assign({},state,{
     flowElements:action.flowElements,
     isMovingNode:true
   });

   case FINISH_MOVE_NODE_ELEMENT:
   return Object.assign({},state,{
     isMovingNode:false
   });

   case BEGIN_DRAG_FOOTER:
   return Object.assign({},state,{
     isDraggingFooter:true
   });

   case FINISH_DRAG_FOOTER:
   return Object.assign({},state,{
     isDraggingFooter:false
   });


   case DESELECT_TOOL:
   return Object.assign({},state,{
     flowElements:action.flowElements,
     selectedToolId:null
   });

   case MOVE_TOOL:
   return Object.assign({},state,{
     newNode:action.newNode,
     absolutePosition:action.absolutePosition
   });


   case SET_FLOW_ACTIVITIES:
   return Object.assign({},state,{
     activities:action.activities,
     currentActivityIndex:action.currentActivityIndex,
     flowElements:action.flowElements,
     flowConnectors:action.flowConnectors,
     connectorPositions:action.connectorPositions,
     currentActivity:action.activities[action.currentActivityIndex]
   });

   case OPEN_NEW_ACTIVITY_DIALOG:
   return Object.assign({},state,{
     newActivityDialogOpened:true
   });


   case CLOSE_NEW_ACTIVITY_DIALOG:
   return Object.assign({},state,{
     newActivityDialogOpened:false
   });

   case CREATE_NEW_ACTIVITY:
   return Object.assign({},state,{
     currentActivityIndex:action.currentActivityIndex,
     activities:action.activities,
     currentActivity:action.activities[action.currentActivityIndex],
     flowConnectors:[],
     flowElements:[]
   });


   case OPEN_REMOVE_ACTIVITY_DIALOG:
   return Object.assign({},state,{
     removeActivityDialogOpened:true,
   });

   case CLOSE_REMOVE_ACTIVITY_DIALOG:
   return Object.assign({},state,{
     removeActivityDialogOpened:false
   });


   case REMOVE_ACTIVITY:
   return Object.assign({},state,{
     removeActivityDialogOpened:false,
     activities:action.activities,
     currentActivityIndex:action.currentActivityIndex,
     flowElements:action.activities[action.currentActivityIndex].elements,
     flowConnectors:action.activities[action.currentActivityIndex].connectors,
     currentActivity:action.activities[action.currentActivityIndex]
   });


   case SELECT_ACTIVITY:
   return Object.assign({},state,{
     activities:action.activities,
     currentActivityIndex:action.currentActivityIndex,
     currentActivity:action.activities[action.currentActivityIndex],
     flowElements:action.activities[action.currentActivityIndex].elements,
     flowConnectors:action.activities[action.currentActivityIndex].connectors,
     connectorPositions:action.connectorPositions,
     selectedConnectorId:-1,
     selectedSourceId:-1
   });

   case CHANGE_ACTIVITY_PROPERTY:
    var updatedActivities=[];
    for (var i=0;i<state.activities.length;i++) {
        if (i==state.currentActivityIndex) {
          updatedActivities.push(action.currentActivity);
        } else {
          updatedActivities.push(state.activities[i]);
        }
    }

   return Object.assign({},state,{
     currentActivity:action.currentActivity,
     activities:updatedActivities
   });

   case ADD_SUBFLOW_ACTIVITY:
      // console.log(state);
   return Object.assign({},state,{
     activities:[
       ...state.activities,
       action.activity
     ],
     componentLinks:[
        ...state.componentLinks,
        {component:action.activity.id,
          parentActivity:action.parentActivityId,
          parentNode:action.parentNodeId}
     ]
   });

   case REMOVE_SUBFLOW_ACTIVITY:
   var updatedActivities=[];
   var deletedIndex=99999;
   var updatedComponentLinks=[];
   var isSubFlow=false;
   var currentActivityIndex=state.currentActivityIndex;
   for (var i=0;i<state.componentLinks.length;i++) {
      if (state.componentLinks[i].component!==action.activityName) {
        updatedComponentLinks.push(state.componentLinks[i]);
      } else {
        isSubFlow=true;
      }
   }
   if (isSubFlow) {
     for (var i=0;i<state.activities.length;i++) {
        var activity=state.activities[i];
        if (activity.name!==action.activityName) {
          updatedActivities.push(activity);
        } else {
          deletedIndex=i;
        }
     }
     if (deletedIndex<=currentActivityIndex) {
       currentActivityIndex--;
     }
   } else {
     updatedActivities=state.activities;
     updatedComponentLinks=state.componentLinks;
   }
   return Object.assign({},state,{
     activities:updatedActivities,
     componentLinks:updatedComponentLinks,
     currentActivityIndex:currentActivityIndex
   });

   case SET_HELP:
      return Object.assign({},state,{
        currentHint:action.hint,
        currentHelp:action.help
      });
    case CHANGE_NODE_PROPERTY:
        // export function changeNodeProperty(id,value,currentPropertyValues,selectedNodeElement) {
          console.log("CHANGE NODE PROPERTY");
          console.log(action);
          var newActivities=state.activities.slice(0);
          if (state.currentPropertyValues!=null) {
            var newPropertyValues=state.currentPropertyValues.slice(0);
            var newFlowElements=state.flowElements.slice(0);
            newPropertyValues[action.propertyId]=action.value;
            var cloneElement = Object.assign({}, state.selectedNodeElement);
            cloneElement.properties[action.propertyId].value=action.value;
            cloneElement.propertyValues[action.propertyId]=action.value;


      			if (action.propertyId==0) { // name
              console.log("changing name");
      				cloneElement.name=action.value;
              // change in flowElements
              var index=findElementIndex(action.nodeId,newFlowElements);
              if (index>=0) {
                console.log("found node element index="+index);
                newFlowElements[index]=cloneElement;
                console.log(newFlowElements);
              }
              if (cloneElement.subFlow!=null) {
                index=findSubFlowActivity(action.activityId,action.nodeId,state.activities);
                if (index>=0) {
                  var cloneActivity=Object.assign({},state.activities[index]);
                  cloneActivity.name=cloneElement.name;
                  newActivities[index]=cloneActivity;
                }
              }



      			}
      			if (action.propertyId==1) { // width
      				if (parseInt(action.value)>=120 && parseInt(action.value)<=1500) {
      					cloneElement.width=action.value;
      				}
      			}
      			if (action.propertyId==2) { // color

      			}
            console.log(cloneElement.name);

      			//console.log(newPropertyValues);
      			// console.log(cloneElement);
          }

         return Object.assign({},state,{
           currentPropertyValues:newPropertyValues,
           selectedNodeElement:cloneElement,
           flowElements:newFlowElements,
           activities:newActivities,
           lastChangedValue:action.value
         });
  default:
    return state
  }
}

export default AsyncFlowsAppReducer
