import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import CanvasNodeElement from '../components/CanvasNodeElement';
import NodeConnectorElement from '../components/NodeConnectorElement';
import NewConnectorElement from '../components/NewConnectorElement';
import { findElement } from '../redux-containers/ElementContainerFunctions';

class CanvasContainer extends Component {
	deselectSourceConnector(event) {
		this.props.deselectSourceConnector(event);
	}


	onMouseDown(event) {
		this.props.deselectNode(event);
		this.props.deselectLink();
		this.props.showActivityProperties();
	}

	onMouseUp(event) {

		if (this.props.isCreatingLink) {
			this.props.cancelLinkCreation(event);
		}
		if (this.props.isMovingNode) {
			this.props.finishNodeMoving(event);
		}
		if (this.props.isCreatingNode) {
			this.props.finishNodeCreation(event);
		}
	}
	moveNodeElement(event){
			this.props.moveNodeElement(event);
			this.props.updateConnectors(this.props.selectedNodeId);
	}
	moveNode(event) {
		if (this.props.isMovingNode) {
			// moving flow Element
			this.moveNodeElement(event);
		}
		if (this.props.selectedSourceId>=0) {
			// creating link
			this.props.gotoOutputConnector(event);
		}
	};

  render() {
  var currentFlowList=this.props.flowElements.map((element)=>
			!element.deleted && <CanvasNodeElement
				key={element.id}
				identifier={element.id}
				selected={this.props.selectedNodeId===element.id}
				height={element.height}
				width={element.width}
				textHeight={this.props.textElementHeight}
				name={element.propertyValues[0]}
				type="element"
				x={element.x}
				y={element.y}
				color={element.propertyValues[2]}
				isMovingNode={this.props.isMovingNode}
				isCreatingNode={this.props.isCreatingNode}
				isCreatingLink={this.props.isCreatingLink}
				inputInterfaces={element.inputInterfaces}
				outputInterfaces={element.outputInterfaces}
				nodeElementSettings={this.props.settings.nodeElement}
				connectorsSides={this.props.connectorsSides}
				asyncInterface={element.asyncInterface}
				selectNode={(event,id) => this.props.selectNode(event,id)}
				finishNodeMoving={event => this.props.finishNodeMoving(event)}
				moveNodeElement={(event) => this.moveNodeElement(event)}
				selectSourceConnector={(event,flowElement,connectorId) =>
					this.props.selectSourceConnector(event,flowElement,connectorId)}
				createLink={(flowElement,connectorId) => this.props.createLink(flowElement,connectorId)}
				gotoOutputConnector={(event) => this.moveNode(event)}
				deselectSourceConnector={(event) => this.deselectSourceConnector(event)}
				/>
	);
	const connectorsList=this.props.flowConnectors.map((connector,i)=>
		!connector.deleted &&
				this.props.connectorPositions.length===this.props.flowConnectors.length &&
			<NodeConnectorElement
			key={connector.id}
			identifier={connector.id}
			selected={this.props.selectedConnectorId===connector.id}
			sourceElement={findElement(connector.sourceId,this.props.flowElements)}
			sourceIndex={connector.sourceIndex}
			targetElement={findElement(connector.targetId,this.props.flowElements)}
			targetIndex={connector.targetIndex}
			sourcePosition={this.props.connectorPositions[i].sourcePosition}
			targetPosition={this.props.connectorPositions[i].targetPosition}
			onMouseUp={(event) => this.props.selectConnector(event,connector.id)}
			selectConnector={(event,id) =>this.props.selectConnector(event,id)}
			/>
	);
		var isCreatingLink = this.props.selectedSourceId>=0;
    return (
      <div className="CanvasContainer" style={{
			left:this.props.left,
			bottom:this.props.bottom}} id="canvasContainer">
		<svg onMouseMove={(event) => this.moveNode(event)}
			onMouseUp={(event) => this.onMouseUp(event)}
		>
			<rect x="0" y="0" width="100%" height="100%" fill="none" pointerEvents="visible"
						onMouseDown={(event) => this.onMouseDown(event)}
			/>

			<g>
				{currentFlowList}
				{connectorsList}
	  {isCreatingLink &&
		<NewConnectorElement
			identifier={this.props.newConnectorId}
			sourceElement={findElement(this.props.selectedSourceId,this.props.flowElements)}
			sourceIndex={this.props.selectedSourceIndex}
			targetElement={null}
			targetIndex={-1}
			sourcePosition={this.props.newConnectorPosition.sourcePosition}
			targetPosition={this.props.newConnectorPosition.targetPosition}
			deselectLink={event => this.deselectLink(event)}
			moveLink={(event) =>this.moveLink(event)}
			/>
	  }
			</g>
		</svg>
      </div>
    );
  }
}

export default CanvasContainer;
