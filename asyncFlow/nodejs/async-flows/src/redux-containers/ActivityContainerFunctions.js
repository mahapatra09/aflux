
var client = require('../functions/client');


import FlowActivity from '../model/FlowActivity';
import { setActivityBasePropertiesAction } from '../redux-actions/workspaceActionTypes';


// functions
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';
import { deselectNode } from '../redux-containers/ElementContainerFunctions';
import { setInfoPropertiesAction } from '../redux-actions/workspaceActionTypes';

import { setFlowActivitiesAction } from '../redux-actions/workspaceActionTypes';
import { openNewActivityDialogAction } from '../redux-actions/workspaceActionTypes';
import { closeNewActivityDialogAction } from '../redux-actions/workspaceActionTypes';
import { createNewActivityAction } from '../redux-actions/workspaceActionTypes';
import { openRemoveActivityDialogAction } from '../redux-actions/workspaceActionTypes';
import { closeRemoveActivityDialogAction } from '../redux-actions/workspaceActionTypes';
import { removeActivityAction } from '../redux-actions/workspaceActionTypes';
import { selectActivityAction } from '../redux-actions/workspaceActionTypes';
import { changeActivityPropertyAction } from '../redux-actions/workspaceActionTypes';
import { addSubFlowActivityAction } from '../redux-actions/workspaceActionTypes';
import { removeSubFlowActivityAction } from '../redux-actions/workspaceActionTypes';

/**
	Adds the activity present in the nodeElement.subFlow

*/

export function buildSubFlowName(activityName,nodeId) {
		return activityName+":"+nodeId;
}


export function addSubFlowActivity(nodeElement,parentActivityId,activities) {
		nodeElement.subFlow.activity.id=getNewActivityId(activities);
		nodeElement.subFlow.activity.name=nodeElement.name;
		nodeElement.subFlow.activity.properties[0].value=nodeElement.name;
		nodeElement.subFlow.activity.properties[1].value="true";
		var activity=nodeElement.subFlow.activity;
		activity.parentActivityId=parentActivityId;
		activity.parentNodeId=nodeElement.id;
		window.reduxStore.dispatch(addSubFlowActivityAction(activity,parentActivityId,nodeElement.id));
}


export function removeSubFlowActivity(nodeElementId,parentActivityName) {
	window.reduxStore.dispatch(
		removeSubFlowActivityAction(
			buildSubFlowName(parentActivityName,nodeElementId)));

}


export function refreshActivities(activities,activityIndex,elements,connectors) {
		activities[activityIndex].connectors=connectors;
		activities[activityIndex].elements=elements;
		return activities;
	}

  export function findActivityByName(name,activities) {
    var result=null;
    for (var i=0;i<activities.length;i++) {
      if (activities[i].name===name) {
        result=activities[i];
      }
    }
    return result;

  }


	export function findActivityById(id,activities) {
    var result=null;
    for (var i=0;i<activities.length;i++) {
      if (activities[i].id===id) {
        result=activities[i];
      }
    }
    return result;

  }

	export function findActivityIndexById(id,activities) {
    var result=-1;
    for (var i=0;i<activities.length;i++) {
      if (activities[i].id===id) {
        result=i;
      }
    }
    return result;

  }


	export function findSubFlowActivity(activityId,nodeId,activities) {
			var result=-1;
			for (var i=0;i<activities.length;i++) {
	      if (activities[i].parentActivityId===activityId && activities[i].parentNodeId===nodeId) {
	        result=i;
	      }
	    }
	    return result;



	}





export function setInitialActivityProperties(store,host,currentActivity,activities,currentActivityIndex) {
  client({method: 'GET',
          mode:'no-cors',
          path: host+'/activityBaseProperties'}).done(
            response => {
            setActivityBaseProperties(
              store,
              response.entity,
              currentActivity,
              activities,
              currentActivityIndex)});
}


function setActivityBaseProperties(store,basePropertiesArray,currentActivity,activities,currentActivityIndex) {
  // window.reduxStore.dispatch(setActivityBasePropertiesAction(basePropertiesArray));
  // return;

  // clone currentActivity
  var ca=currentActivity;
  var currentActivityNew=new FlowActivity(
      ca.id,ca.name,ca.index,ca.elements,ca.connectors,ca.properties,ca.parentActivityId,ca.parentNodeId);
  // var currentActivity = Object.assign({}, ownProps.currentActivity);
  currentActivityNew.properties=currentActivityNew.generateProperties(basePropertiesArray);
  var activitiesNew=activities;
  activitiesNew[currentActivityIndex]=currentActivityNew;
  window.reduxStore.dispatch(setActivityBasePropertiesAction(basePropertiesArray,currentActivityNew,activitiesNew));
}


export function getNewActivityId(activities){
  var elements=activities;
  var maxId=0;
  for (var i=0;i<elements.length;i++) {
    if (maxId<elements[i].id) {
      maxId=elements[i].id;
    }
  }
  return maxId+1;
};



export const SET_FLOW_ACTIVITIES='SET_FLOW_ACTIVITIES'

/**
			sets the list of activities and stablish the first as the selected one
	*/
export function setFlowActivities(activities,nodeElementSettings) {
  var currentActivityIndex=0;
  if (activities.length!==0) {
    var flowConnectors=activities[0].connectors;
    var flowElements=activities[0].elements;
    var connectorPositions=generateConnectorPositions(
            activities[0].connectors,
            activities[0].elements,
            0,
            0,
            nodeElementSettings,
            "left-right");
  } else {
    flowConnectors=[];
    flowElements=[];
    connectorPositions=[];
  }

  window.reduxStore.dispatch(
    setFlowActivitiesAction(
      activities,
      currentActivityIndex,
      flowElements,
      flowConnectors,
      connectorPositions));
}





export function openNewActivityDialog() {
  window.reduxStore.dispatch(openNewActivityDialogAction());
};

export function closeNewActivityDialog() {
  window.reduxStore.dispatch(closeNewActivityDialogAction());
};


/*
Creates a new activity with the given name
*/
export function createNewActivity(name,
    activities,
    currentActivityIndex,
    flowElements,
    flowConnectors,
    activityBaseProperties) {
  // search by name in current job
  var updatedActivities=
      refreshActivities(
        activities,
        currentActivityIndex,
        flowElements,
        flowConnectors);
  var newCurrentActivityIndex=currentActivityIndex;
  if (name==="") {
    alert("Error: Empty name");
  } else {
    var sameActivity=findActivityByName(name,activities);
    if (sameActivity!=null) {
      alert("Error: Activity already exists:"+name);
    } else {
      var idActivity = getNewActivityId(activities);
      var newActivity=new FlowActivity(
					idActivity,
					name,
					idActivity,[],[],
					activityBaseProperties,
					-1,-1);
      updatedActivities.push(newActivity);
      newCurrentActivityIndex=updatedActivities.length-1;
      window.reduxStore.dispatch(
        createNewActivityAction(newCurrentActivityIndex,updatedActivities));
    }
  }
  // check if ther is no activity with the same name
  window.reduxStore.dispatch(closeNewActivityDialogAction());
};





export function openRemoveActivityDialog(activities) {
  if (activities.length>1) {
    window.reduxStore.dispatch(openRemoveActivityDialogAction());
  } else {
    alert("Cannot remove the unique activity");
  }
};

export function closeRemoveActivityDialog() {
  window.reduxStore.dispatch(closeRemoveActivityDialogAction());
};
/**

Removes the currentActivity
*/
export function removeActivity(currentActivityIndex,activities) {
  var updatedActivities=[];
  var currentActivityIndexNew=currentActivityIndex;
  for (var i=0;i<activities.length;i++) {
    if (i!==currentActivityIndex) {
      updatedActivities.push(activities[i]);
    }
  }
  if (currentActivityIndex===updatedActivities.length) {
    currentActivityIndexNew--;
  }
  // check if ther is no activity with the same name
  window.reduxStore.dispatch(removeActivityAction(updatedActivities,currentActivityIndexNew));
};


export function selectActivity(activityIndex,currentActivityIndex,activities,flowElements,flowConnectors,mouseDeltaX,mouseDeltaY,settings) {
  if (activityIndex!==currentActivityIndex) {
    // get data for previous activity
    var refreshedActivities=
      refreshActivities(
        activities,
        currentActivityIndex,
        flowElements,
        flowConnectors);
    var connectorPositions=
      generateConnectorPositions(
        refreshedActivities[activityIndex].connectors,
        refreshedActivities[activityIndex].elements,
        mouseDeltaX,
        mouseDeltaY,
        settings.nodeElement,
        "left-right");
    deselectNode();
    window.reduxStore.dispatch(
      selectActivityAction(refreshedActivities,activityIndex,connectorPositions));
  }
};


export function changeActivityProperty(id,value,currentActivity) {
  if (currentActivity!=null) {
    if (currentActivity.properties!=null) {
			//console.log("changeActivityProperty");
			//console.log(id);
			//console.log(currentActivity);
			//console.log(currentActivity.parentActivityId);
			if (id==0 && currentActivity.parentActivityId>=0) {
				// cant't modify activity name of subflow
			} else {
				var newPropertyValues=currentActivity.properties.slice(0);
	      newPropertyValues[id]=value;
	      var clonedActivity = Object.assign({}, currentActivity);
	      clonedActivity.properties=currentActivity.properties.slice(0);
	      clonedActivity.properties[id].value=value;
				var newName=currentActivity.name;
				if (id==0) { // name property
					newName=value;
					clonedActivity.name=newName;
					// console.log("changeActivityProperty");
					// console.log(newName);
				}
	      window.reduxStore.dispatch(changeActivityPropertyAction(clonedActivity));

			}
    }

  }
};



export function showActivityProperties() {
  // deselect activity
  // deselect nodeElement
  deselectNode();
  window.reduxStore.dispatch(setInfoPropertiesAction("ACTIVITY"));
}
