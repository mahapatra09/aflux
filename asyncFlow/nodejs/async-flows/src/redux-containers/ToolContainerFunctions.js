var client = require('../functions/client');

import { setToolsetAction } from '../redux-actions/workspaceActionTypes';
import { deselectToolAction } from '../redux-actions/workspaceActionTypes';
import { moveToolAction } from '../redux-actions/workspaceActionTypes';

// functions
import { getAbsolutePosition } from '../functions/WorkspaceManager';
import { getCanvasContainerElement } from '../functions/WorkspaceManager';
import { getSideBarContainerElement } from '../functions/WorkspaceManager';
import { generateNewNode } from '../redux-containers/ElementContainerFunctions';
import { getSideBarScrollPosition } from '../functions/WorkspaceManager';
import { getMousePosition } from '../functions/WorkspaceManager';
import { getWorkspaceContainerElement } from '../functions/WorkspaceManager';

import { addSubFlowActivity } from '../redux-containers/ActivityContainerFunctions';



export function findTool(id,flowTools) {
		var result=null;
		for (var i=0;i<flowTools.length;i++) {
			if (flowTools[i].id===id) {
				result=flowTools[i];
			}
		}
		return result;
	}



export function refreshTools(host) {
  client({method: 'GET',
    mode:'no-cors',
    path: host+'/elementTypes'}).done(
      response => {window.reduxStore.dispatch(setToolsetAction(response.entity))});
}


export function deselectTool(
		selectedToolId,newNode,flowElements,toolSet,settings,elementBaseProperties,currentActivityId,activities) {
  // add element to canvas
  // add the element to canvas if it is over canvas
  var canvasElement=getCanvasContainerElement();
  var canvasAbsolutePosition=getAbsolutePosition(canvasElement)
  var rect = canvasElement.getBoundingClientRect();
  var x=newNode.x;
  var y=newNode.y;
  var flowList=flowElements.slice(0);
  if (x>=rect.left && x<=rect.left+rect.width && y>=rect.top && y<=rect.top+rect.height) {
    var position={
      x:newNode.x-rect.left+canvasElement.scrollLeft,
      y:newNode.y-rect.top+canvasElement.scrollTop,
    }
    var svgElement=canvasElement.firstChild;
    var svgPoint=svgElement.createSVGPoint();
    var svgPosition=svgPoint.matrixTransform(svgElement.getScreenCTM().inverse());
    var sideBarElement=getSideBarContainerElement();
    const newGeneratedNode=
      generateNewNode(
          selectedToolId,
            position,
            getSideBarScrollPosition(),
            flowElements,
            toolSet,
            settings,
            elementBaseProperties,
						activities);
    flowList.push(newGeneratedNode);
		if (newGeneratedNode.subFlow!=null) {
				addSubFlowActivity(newGeneratedNode,currentActivityId,activities);
		}
  }
  window.reduxStore.dispatch(deselectToolAction(flowList));
};



export function moveTool(event,selectedToolId,mouseDeltaX,mouseDeltaY,flowElements,toolSet,settings,elementBaseProperties,activities) {
  var scrollPosition=getSideBarScrollPosition();
  var p=getMousePosition(event,getWorkspaceContainerElement());
  if (selectedToolId!=null) {
    var absolutePosition={
      x:p.x-mouseDeltaX-scrollPosition.left,
      y:p.y-mouseDeltaY-scrollPosition.top
    }

    const newNode=
      generateNewNode(
          selectedToolId,
          absolutePosition,
          getSideBarScrollPosition(),
          flowElements,
          toolSet,
          settings,
          elementBaseProperties,
					activities);
    window.reduxStore.dispatch(moveToolAction(newNode,absolutePosition));
  }
};
