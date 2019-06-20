

import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import { calculateCurveConnector } from '../redux-containers/ConnectorContainerFunctions';


class NodeConnectorElement extends Component {


  render() {
	  	const connectorStyle = {fill:'none',stroke:'black',cursor:"copy", strokeWidth:this.props.selected ? 3 : 1};
	return (
		<g onMouseDown={(event) => this.props.selectConnector(event,this.props.identifier)}
			pointerEvents="visible"
			key={this.props.identifier}
			id={this.props.identifier}>
		{/*
		<polyline points={this.calculateConnector()}
				style={connectorStyle} />
		*/}
		<circle cx={this.props.sourcePosition.x} cy={this.props.sourcePosition.y} r="3" fill="black" />
		<circle cx={this.props.targetPosition.x} cy={this.props.targetPosition.y} r="3" fill="black" />
		<path style={connectorStyle}  d={calculateCurveConnector(
						this.props.sourcePosition,
						this.props.targetPosition,
						"left-right",
						this.props.sourceElement,
						this.props.targetElement,
						this.props.sourceIndex,
						this.props.targetIndex)} />
		</g>
    );
  }
}

export default NodeConnectorElement;
