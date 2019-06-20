import { DESELECT_NODE } from '../redux-actions/workspaceActionTypes';

import { selectNodeAction } from '../redux-actions/workspaceActionTypes';
import { createNodeAction } from '../redux-actions/workspaceActionTypes';
import { moveNodeElementAction } from '../redux-actions/workspaceActionTypes';
import { finishMoveNodeElementAction } from '../redux-actions/workspaceActionTypes';


import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';
import { buildSubFlowName } from '../redux-containers/ActivityContainerFunctions';
import { removeSubFlowActivity } from '../redux-containers/ActivityContainerFunctions';
import { getNewActivityId } from '../redux-containers/ActivityContainerFunctions';



// functions
import { getMousePosition } from '../functions/WorkspaceManager';
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';
import { getCanvasContainerElement } from '../functions/WorkspaceManager';
import { getSideBarScrollPosition } from '../functions/WorkspaceManager';
import { deepCloneObject } from '../functions/WorkspaceManager';



// model
import FlowElement from '../model/FlowElement';



export function findElement(id,flowElements) {
		var result=null;
		for (var i=0;i<flowElements.length;i++) {
			if (flowElements[i].id===id) {
				result=flowElements[i];
			}
		}
		return result;
	}


	export function findElementIndex(id,flowElements) {
			var result=-1;
			for (var i=0;i<flowElements.length;i++) {
				if (flowElements[i].id===id) {
					result=i;
				}
			}
			return result;
		}



  export function calculateElementHeight(height,
      connectorSides,
      inputInterfaces,
      outputInterfaces,
      asyncInterface,
      settings) {
    if (connectorSides==="top-down") {
      var elementHeight=height;
      var inputMargin = settings.leftConnectorMargin;
      var outputMargin = settings.rightConnectorMargin;
    } else {
      var inputMargin = settings.topConnectorMargin;
      var outputMargin = settings.bottomConnectorMargin;
      var outputInterfacesCount=outputInterfaces;
      if (asyncInterface) {
        outputInterfacesCount++;
      }
      var inputInterfacesCount=inputInterfaces;
      var elementHeight=Math.max(
          height,
          outputMargin+outputInterfacesCount*settings.connectorDistance,
          inputMargin+inputInterfacesCount*settings.connectorDistance);
    }
    return elementHeight;

  }


export function deselectNode() {
  window.reduxStore.dispatch({type:DESELECT_NODE});
};


export function deleteFlowElement(elementId,flowElements,flowConnectors,mouseDeltaX,mouseDeltaY,settings,currentActivityName) {
  var newflowElements=[];
  for (var i=0;i<flowElements.length;i++) {
    var flowElement=flowElements[i];
    if (elementId!==flowElement.id) {
      newflowElements.push(flowElement);
    } else {
			if (flowElement.subFlow!=null) {
				removeSubFlowActivity(flowElement.id,currentActivityName);
			}
 		}
  }
  var newflowConnectors=[];
  for (var i=0;i<flowConnectors.length;i++) {
    var flowConnector=flowConnectors[i];
    if (flowConnector.sourceId!==elementId && flowConnector.targetId!==elementId) {
      newflowConnectors.push(flowConnector);
    }
  }
  var connectorPositions=
      generateConnectorPositions(
        newflowConnectors,
        newflowElements,
        mouseDeltaX,
        mouseDeltaY,
        settings.nodeElement,
        "left-right");
  deselectNode();
  window.reduxStore.dispatch(applyGeneralActionAction({
    flowElements:newflowElements,
    flowConnectors:newflowConnectors,
    connectorPositions:connectorPositions
  }),"deleteFlowElement");
};


function getNewElementId(flowElements) {
  var elements=flowElements;
  var maxId=0;
  for (var i=0;i<elements.length;i++) {
    if (maxId<elements[i].id) {
      maxId=elements[i].id;
    }
  }
  return maxId+1;
};

export function generateNewNode(typeId,
    position,
    scrollPosition,
    flowElements,
    toolSet,
    settings,
    elementBaseProperties,
		activities) {
  var newId=getNewElementId(flowElements);

  var toolElement=findElement(typeId,toolSet);

	if (toolElement.subFlow!=null) {
		var idActivity=getNewActivityId(activities);
		// console.log("generateNewNode");
		// console.log(idActivity);
		// console.log(activities);
		toolElement.subFlow.activity.id=idActivity;
		toolElement.subFlow.activity.index=idActivity;
		// console.log(toolElement.subFlow.activity.id);

	}


  const flowElement=
    new FlowElement(
      newId,
      toolElement.name,
      toolElement,
      position.x,
      position.y,
      settings.nodeElement.width,
      settings.nodeElement.height,
      toolElement.inputInterfaces,
      toolElement.outputInterfaces,
      "",
      "",
      toolElement.asyncInterface,
      toolElement.color,
      elementBaseProperties,
      deepCloneObject(toolElement.subFlow));
  return flowElement;
};

export function createNode(event,typeId,position,flowElements,toolSet,settings,elementBaseProperties,activities) {
  var p=getMousePosition(event,null);
  var newNode=
    generateNewNode(
      typeId,
      position.absolute,
      getSideBarScrollPosition(),
      flowElements,
      toolSet,
      settings,
      elementBaseProperties,
			activities);

      window.reduxStore.dispatch(createNodeAction(p,position.absolute,typeId,newNode));

};



export function selectNode(event,id,flowElements) {
  var p=getMousePosition(event,getCanvasContainerElement());
  var flowElement=findElement(id,flowElements);
  window.reduxStore.dispatch(selectNodeAction(id,p,flowElement));
};



export function finishNodeMoving() {
  window.reduxStore.dispatch(finishMoveNodeElementAction());
};

export function finishNodeCreation()  {
  window.reduxStore.dispatch(applyGeneralActionAction({isCreatingNode:false},"finishNodeCreation"));
};

export function moveNodeElement(event,flowElements,selectedNodeId,mouseDeltaX,mouseDeltaY){
  var p=getMousePosition(event,getCanvasContainerElement());

    var newFlowElements = flowElements.slice(0);
    var flowElement=findElement(selectedNodeId,newFlowElements);
    flowElement.x=p.x-mouseDeltaX;
    flowElement.y=p.y-mouseDeltaY;
    window.reduxStore.dispatch(moveNodeElementAction(newFlowElements));
};
