

import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import { calculateCurveConnector } from '../redux-containers/ConnectorContainerFunctions';


class NewConnectorElement extends Component {

  render() {
	  	const connectorStyle = {fill:'none',stroke:'black',strokeWidth:1};
	return (
		<g>
		{/*
		<polyline points={this.calculateConnector()}
				style={connectorStyle} />
		*/}
		<circle cx={this.props.sourcePosition.x} cy={this.props.sourcePosition.y} r="3" fill="black" />
		<circle cx={this.props.targetPosition.x} cy={this.props.targetPosition.y} r="3" fill="black" />
		<path style={connectorStyle}  id={this.props.identifier}  d={calculateCurveConnector(
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

export default NewConnectorElement;
