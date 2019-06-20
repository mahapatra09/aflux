import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import NodeElement from './NodeElement';
import ElementInputConnector from './ElementInputConnector'
import ElementOutputConnector from './ElementOutputConnector'
import { calculateElementHeight } from '../redux-containers/ElementContainerFunctions';
import { getConnectorPosition } from '../redux-containers/ConnectorContainerFunctions';
import { calculateSide } from '../redux-containers/ConnectorContainerFunctions';

class CanvasNodeElement extends Component {
		deselectSourceConnector(event) {
			this.props.deselectSourceConnector(event);
		}
		render() {
			var elementHeight=
				calculateElementHeight(this.props.height,
					this.props.connectorsSides,
					this.props.inputInterfaces,
					this.props.outputInterfaces,
					this.props.asyncInterface,
					this.props.nodeElementSettings);
			var inputInterfaces=[];
			for (var i=0;i<this.props.inputInterfaces;i++) {
				inputInterfaces.push({
					key:i+1,
					position:getConnectorPosition(
						this.props.x,
						this.props.y,
						this.props.width,
						this.props.height,
						calculateSide("input",this.props.connectorsSides),
						this.props.asyncInterface,
						i+1,
						this.props.nodeElementSettings,
						this.props.inputInterfaces
					),
					side:calculateSide("input",this.props.connectorsSides)
				});
			}
		var outputInterfaces=[];
		if (this.props.asyncInterface) {
			outputInterfaces.push({
				key:-1,
				position:getConnectorPosition(
						this.props.x,
						this.props.y,
						this.props.width,
						this.props.height,
						calculateSide("output",this.props.connectorsSides),
						this.props.asyncInterface,
						-1,   // is async connector
						this.props.nodeElementSettings,
						this.props.inputInterfaces
					),
				side:calculateSide("output",this.props.connectorsSides),
				isAsync:true
			})
		}
		for (var i=0;i<this.props.outputInterfaces;i++) {
			outputInterfaces.push({
				key:i+1,
				position:getConnectorPosition(
						this.props.x,
						this.props.y,
						this.props.width,
						this.props.height,
						calculateSide("output",this.props.connectorsSides),
						this.props.asyncInterface,
						i+1,   // is async connector
						this.props.nodeElementSettings,
						this.props.inputInterfaces
					),
				side:calculateSide("output",this.props.connectorsSides),
				isAsync:false
		}) }
			var inputInterfacesList=inputInterfaces.map((inputInterface) =>
				<ElementInputConnector
					isCreatingLink={this.props.isCreatingLink}
					side={inputInterface.side}
					key={inputInterface.key}
					identifier={inputInterface.key}
					x={inputInterface.position.x}
					y={inputInterface.position.y}
					createLink={(connectorId) => this.props.createLink(this.props.identifier,connectorId)}
					/>  );
			var outputInterfacesList=outputInterfaces.map((outputInterface) =>
				<ElementOutputConnector
					key={outputInterface.key}
					identifier={outputInterface.key}
					side={outputInterface.side}
					x={outputInterface.position.x}
					y={outputInterface.position.y}
					isAsync={outputInterface.isAsync}
					selectSourceConnector={(event,connectorId) =>
						this.props.selectSourceConnector(event,this.props.identifier,connectorId)}
					gotoOutputConnector={(event) => this.props.gotoOutputConnector(event)}
					deselectSourceConnector={(event) => this.deselectSourceConnector(event)}

					/>  );
		return (
			<g>
				{inputInterfacesList}
				{outputInterfacesList}

					<NodeElement
						height={elementHeight}
						identifier={this.props.identifier}
						width={this.props.width}
						textHeight={this.props.textHeight}
						name={this.props.name}
						type="element"
						x={this.props.x}
						y={this.props.y}
						color={this.props.color}
						selected={this.props.selected}
						isMovingNode={this.props.isMovingNode}
						selectNode={(event) => this.props.selectNode(event,this.props.identifier)}
						finishNodeMoving={event => this.props.finishNodeMoving(event)}
						moveNodeElement={(event) => this.props.moveNodeElement(event)}/>

						}
					/>
			</g>
    );
  }
}

export default CanvasNodeElement;
