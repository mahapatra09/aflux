

import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';


// functions
import { findElement } from '../redux-containers/ElementContainerFunctions';


// model
import FlowConnector from '../model/FlowConnector';


export function calculateSide(connectorType,sides) {
  switch (connectorType) {
    case 'input':
      if (sides==='top-down') {
        var side="top";
      } else {
        var side="left";
      }
      break;
    case 'output':
      if (sides==='top-down') {
        var side="bottom";
      } else {
        var side="right";
      }
      break;
    default:
      var side=null;
  }
    return side;
}



export function	getConnectorSourcePointPosition(
    sourceElement,
    sourceIndex,
    sides,
    asyncInterface,
    settings,
    totalConnectors) {

      var side=calculateSide("output",sides);
      var centerPosition=calculateCenterConnector(side);
      var baseConnectorPosition=getConnectorPosition(
            sourceElement.x,
            sourceElement.y,
            sourceElement.width,
            sourceElement.height,
            side,
            asyncInterface,
            sourceIndex,
            settings,
            totalConnectors);
    return {
      x:baseConnectorPosition.x+centerPosition.x,
      y:baseConnectorPosition.y+centerPosition.y
    }
}


function calculateCenterConnector(side) {
  switch (side) {
    case 'left':
      return {
        x:-15,
        y:0
      }
    case 'right':
      return {
        x:15,
        y:0
      }
    case 'top':
      return {
        x:0,
        y:-15
      }
    case 'bottom':
      return {
        x:0,
        y:15
      }
    default:
      return null;
  }
}

export function	getConnectorTargetPointPosition(
    element,
    index,
    sides,
    settings) {

      var side=calculateSide("input",sides);
      var centerPosition=calculateCenterConnector(side);
      var baseConnectorPosition=getConnectorPosition(
            element.x,
            element.y,
            element.width,
            element.height,
            side,
            false,   // input does not have async connectors
            index,
            settings,
            1);     // input has only one connector
    return {
      x:baseConnectorPosition.x+centerPosition.x,
      y:baseConnectorPosition.y+centerPosition.y
    }
}

export function	generateConnectorPosition(
    connector,
    flowElements,
    mousePosition,
    mouseDeltaX,
    mouseDeltaY,
    settings,
    sides) {
  var sourceElement=findElement(connector.sourceId,flowElements);
  var totalOutputConnectors=sourceElement.outputInterfaces;
  if (sourceElement.asyncInterface===true) {
    totalOutputConnectors++;
  }
  var targetX=null;
  var targetY=null;
  if (connector.targetId!==0) {
    var targetElement=findElement(connector.targetId,flowElements);
    var targetPosition=getConnectorTargetPointPosition(
      targetElement,
      connector.targetIndex,
      sides,
      settings)
      targetX=targetPosition.x;
      targetY=targetPosition.y;
  } else {
    targetX=mousePosition.x-mouseDeltaX+5;
    targetY=mousePosition.y-mouseDeltaY+5;
  }
  var result={
    sourcePosition:getConnectorSourcePointPosition(
      sourceElement,
      connector.sourceIndex,
      sides,
      sourceElement.asyncInterface,
      settings,
      totalOutputConnectors),
    targetPosition: {
      x:targetX,
      y:targetY
    },
  };
  return result;
}


export function generateConnectorPositions(
    flowConnectors,
    flowElements,
    mouseDeltaX,
    mouseDeltaY,
    settings,
    sides) {
  var result=[];

  if (flowElements.length>0) {
    for (var i=0;i<flowConnectors.length;i++) {
      result.push(
        generateConnectorPosition(
          flowConnectors[i],
          flowElements,
          null,
          mouseDeltaX,
          mouseDeltaY,
          settings,
          sides));
    }
  }
  return result;
}




export function getConnectorPosition(x,y,
      width,height,
      side,
      asyncInterface,
      index,
      settings,
      totalConnectors) {
  switch (side) {
    case 'left': // input
      var margin=settings.topConnectorMargin;
      var xResult=x;
      var yResult=y+margin+(index-1)*settings.connectorDistance;
      break;
    case 'top': // input
      var margin=settings.leftConnectorMargin;
      var yResult=y;
      var xResult=x+margin+(index-1)*settings.connectorDistance;
      break;
    case 'right': // output
      var xResult=x+width;
      var margin=settings.topConnectorMargin;
      if (index===-1) { // async
        var yResult=y+margin;
      } else {
        var asyncMargin=0;
        if (asyncInterface) {
          asyncMargin=settings.connectorDistance;
        }
        var yResult=y+margin+asyncMargin+(index-1)*settings.connectorDistance;
      }
      break;
    case 'bottom': // output
      var margin=0;
      var yResult=y+height;
      if (index===-1) {
        var xResult=x+width-margin-totalConnectors*settings.connectorDistance;
      } else {
        var xResult=x+width-margin-(totalConnectors-index-1)*settings.connectorDistance;
      }
  }
  return {
    x:xResult,
    y:yResult
  }};




export function deleteConnector(connectorId,flowElements,flowConnectors,mouseDeltaX,mouseDeltaY,settings) {
  var newflowConnectors=[];
  for (var i=0;i<flowConnectors.length;i++) {
    var flowConnector=flowConnectors[i];
    if (connectorId!==flowConnector.id) {
      newflowConnectors.push(flowConnector);
    }
  }
  var connectorPositions=
      generateConnectorPositions(
        newflowConnectors,
        flowElements,
        mouseDeltaX,
        mouseDeltaY,
        settings.nodeElement,
        "left-right");
  window.reduxStore.dispatch(applyGeneralActionAction({
    flowConnectors:newflowConnectors,
    connectorPositions:connectorPositions,
    selectedConnectorId:-1,
  },"deleteConnector"));
};

export function getNewConnectorId(flowConnectors) {
  var connectors=flowConnectors;
  var maxId=0;
  for (var i=0;i<connectors.length;i++) {
    if (maxId<connectors[i].id) {
      maxId=connectors[i].id;
    }
  }
  return maxId+1;
};

export function generateNewLinkConnector(flowElementId,sourceIndex,flowElements,flowConnectors) {
  var flowElement=findElement(flowElementId,flowElements)
  const flowConnector =
    new FlowConnector (
      getNewConnectorId(flowConnectors),
      flowElement,
      sourceIndex,
      null,
      -1)
  return flowConnector;
};

export function cancelLinkCreation() {
    window.reduxStore.dispatch(applyGeneralActionAction({
      selectedSourceId:-1,
      selectedSourceIndex:-1,
      newLinkConnector:null,
      isCreatingLink:false,
    },"cancelLinkCreation"));
};


export function  calculateCurveConnector(
        sourcePosition,
        targetPosition,
        side,
        sourceElement,
        targetElement,
        sourceIndex,
        targetIndex) {
		if (side==="top-down") {
			var middle1X=sourcePosition.x;
			var middle1Y=sourcePosition.y;
			var middleLastX=targetPosition.x;
			var middleLastY=sourcePosition.y;
			var middle2X=sourcePosition.x;
			var middle3X=targetPosition.x;
			if (sourcePosition.y<targetElement.y) {
				middle1Y=parseInt((targetPosition.y-sourcePosition.y)/2)+sourcePosition.y;
				middleLastY=middle1Y;
			} else {
				middle1Y=sourcePosition.y+28;
				middleLastY=targetPosition.y-28;
				if (targetPosition.x>=sourcePosition.x) {
					middle2X=sourceElement.x+sourceElement.width+28;
					middle3X=targetElement.x-28;
				} else {
					middle2X=sourceElement.x-28;
					middle3X=targetElement.x+targetElement.width+28;
				}
			}
			var result=
				"M"+sourcePosition.x+","+sourcePosition.y+" "+
				"C"+middle1X+","+middle1Y+" "+
				middleLastX+","+middleLastY+" "+
				targetPosition.x+","+targetPosition.y;

		} else {
			var middle1X=sourcePosition.x+60;
			var middle1Y=sourcePosition.y;
			var middleLastX=targetPosition.x-60;
			var middleLastY=targetPosition.y;
			var middle2X=sourcePosition.x;
			var middle3X=targetPosition.x;
			var result=
				"M "+sourcePosition.x+","+sourcePosition.y+" "+
				"C "+(middle1X)+","+(middle1Y)+" "+
				""+(middleLastX)+","+(middleLastY)+" "+
				""+targetPosition.x+","+targetPosition.y;
		}
		return result;
  };


/**
  calculates the path to draw the connector from source to target using
  lines () now is bein used calculateCurveConnector inside
*/
  export function calculateConnector() {
		var sourcePosition=this.props.sourcePosition;
		var targetPosition=this.props.targetPosition;
		var middle1Y=sourcePosition.y;
		var middleLastY=sourcePosition.y;
		var middle2X=sourcePosition.x;
		var middle3X=targetPosition.x;
		if (sourcePosition.y<this.props.targetElement.y) {
			middle1Y=parseInt((this.props.targetElement.y-sourcePosition.y)/2)+sourcePosition.y;
			middleLastY=middle1Y;
		} else {
			middle1Y=sourcePosition.y+28;
			middleLastY=this.props.targetElement.y-28;
			if (targetPosition.x>=sourcePosition.x) {
				middle2X=this.props.sourceElement.x+this.props.sourceElement.width+28;
				middle3X=this.props.targetElement.x-28;
			} else {
				middle2X=this.props.sourceElement.x-28;
				middle3X=this.props.targetElement.x+this.props.targetElement.width+28;
			}
		}


		var result=
			sourcePosition.x+","+sourcePosition.y+" "+
			sourcePosition.x+","+middle1Y+" "+
			middle2X+","+middle1Y+" "+
			middle3X+","+middleLastY+" "+
			targetPosition.x+","+middleLastY+" "+
			targetPosition.x+","+targetPosition.y;
		return result;
  };
