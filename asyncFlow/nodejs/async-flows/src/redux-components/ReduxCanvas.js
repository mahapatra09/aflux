import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import ReduxActivityTabContainer from '../redux-containers/ReduxActivityTabContainer';
import ReduxCanvasNodeElementContainer from '../redux-containers/ReduxCanvasNodeElementContainer';
import NodeConnectorElement from '../components/NodeConnectorElement';
import NewConnectorElement from '../components/NewConnectorElement';
import { findElement } from '../redux-containers/ElementContainerFunctions';


/**
WhiteBoard . White part of the screen where nodes and links are drawn
Canvas contains the Flows that are drawn

*/
const ReduxCanvas = ({flowElements,selectedNodeId,textElementHeight,isMovingNode,isCreatingNode,
  isCreatingLink,flowConnectors,connectorPositions,selectedConnectorId,
  selectedSourceId,left,bottom,newConnectorId,selectedSourceIndex,newConnectorPosition,
      // functions
      // from parent
      selectNode,finishNodeMoving,selectSourceConnector,createLink,validateJob,selectConnector,moveLink,deselectLink,
      // from container
      deselectSourceConnector,onMouseDown,onMouseUp,moveNodeElement,moveNode,moveNodeElementInCanvas

    }) => {

      var currentFlowList=flowElements.map((element)=>
    			!element.deleted && <ReduxCanvasNodeElementContainer
    				key={element.id}
    				identifier={element.id}
    				selected={selectedNodeId===element.id}
    				height={element.height}
    				width={element.width}
    				name={element.propertyValues[0]}
    				x={element.x}
    				y={element.y}
    				color={element.propertyValues[2]}
            isSubFlow={element.subFlow!=null}
    				inputInterfaces={element.inputInterfaces}
    				outputInterfaces={element.outputInterfaces}
    				asyncInterface={element.asyncInterface}
            selectNode={(event,id) => this.props.selectNode(event,id)}
    				finishNodeMoving={event => this.props.finishNodeMoving(event)}
    				moveNodeElement={(event) => this.moveNodeElementInCanvas(event,selectedNodeId)}
    				selectSourceConnector={(event,flowElement,connectorId) =>
    					this.props.selectSourceConnector(event,flowElement,connectorId)}
    				createLink={(flowElement,connectorId) => this.props.createLink(flowElement,connectorId)}
    				gotoOutputConnector={(event) => this.moveNode(event,isMovingNode,selectedSourceId,selectedNodeId)}
    				deselectSourceConnector={(event) => this.deselectSourceConnector(event)}
    				selectNode={(event,id) => selectNode(event,id)}
    				finishNodeMoving={event => finishNodeMoving(event)}
    				moveNodeElement={(event) => moveNodeElement(event)}
    				selectSourceConnector={(event,flowElement,connectorId) =>
    					selectSourceConnector(event,flowElement,connectorId)}
    				createLink={(flowElement,connectorId) => createLink(flowElement,connectorId)}
                    validateJob={() => validateJob()}
    				gotoOutputConnector={(event) => moveNode(event,isMovingNode,selectedSourceId,selectedNodeId)}
    				deselectSourceConnector={(event) => deselectSourceConnector(event)}
    				/>
    	);
    	const connectorsList=flowConnectors.map((connector,i)=>
    		!connector.deleted &&
    				connectorPositions.length===flowConnectors.length &&
    			<NodeConnectorElement
    			key={connector.id}
    			identifier={connector.id}
    			selected={selectedConnectorId===connector.id}
    			sourceElement={findElement(connector.sourceId,flowElements)}
    			sourceIndex={connector.sourceIndex}
    			targetElement={findElement(connector.targetId,flowElements)}
    			targetIndex={connector.targetIndex}
    			sourcePosition={connectorPositions[i].sourcePosition}
    			targetPosition={connectorPositions[i].targetPosition}
    			onMouseUp={(event) => selectConnector(event,connector.id)}
    			selectConnector={(event,id) =>selectConnector(event,id)}
    			/>
    	);
    		var isCreatingLink = selectedSourceId>=0;
        return (
          <div className="CanvasContainer" style={{
    			left:left,
    			bottom:bottom}} id="canvasContainer">
    		<svg onMouseMove={(event) => moveNode(event,isMovingNode,selectedSourceId,selectedNodeId)}
    			onMouseUp={(event) => onMouseUp(event,isCreatingLink,isMovingNode,isCreatingNode)}
    		>
    			<rect x="0" y="0" width="100%" height="100%" fill="none" pointerEvents="visible"
    						onMouseDown={(event) => onMouseDown(event)}
    			/>

    			<g>
    				{currentFlowList}
    				{connectorsList}
    	  {isCreatingLink &&
    		<NewConnectorElement
    			identifier={newConnectorId}
    			sourceElement={findElement(selectedSourceId,flowElements)}
    			sourceIndex={selectedSourceIndex}
    			targetElement={null}
    			targetIndex={-1}
    			sourcePosition={newConnectorPosition.sourcePosition}
    			targetPosition={newConnectorPosition.targetPosition}
    			deselectLink={event => deselectLink()}
    			moveLink={(event) =>moveNode(event,isMovingNode,selectedSourceId,selectedNodeId)}
    			/>
    	  }
    			</g>
    		</svg>
          </div>
        );

}


export default ReduxCanvas;
