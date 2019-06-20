

import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class ElementOutputConnector extends Component {

	selectConnector(event) {
		this.props.selectSourceConnector(event,this.props.identifier);
	}

	mouseMove(event) {
		this.props.gotoOutputConnector(event);
	}
	deselectConnector(event) {
		this.props.deselectSourceConnector(event);
	}
  render() {
			if (this.props.isAsync===true) {
				var colorStroke='blue';
				var circleClass='asyncOutputConnector';
			} else {
				var colorStroke='green';
				var circleClass='outputConnector';
			}
	  	var connectorStyle = {stroke:colorStroke,strokeWidth:1,cursor:'crosshair'};
			var drawCircle = "M"+(this.props.x-5)+","+(this.props.y+15)+" a1,1 0 0,0 14,0";
			if (this.props.side==="top") {
				var rotateTransform ="rotate(0 "+this.props.x+" "+this.props.y+")";
			} else {
				var rotateTransform ="rotate(270 "+this.props.x+" "+this.props.y+")";
			}
 			/*
			console.log("ElementOutputConnector");
			console.log(this.props);
			console.log(colorStroke);
			console.log(circleClass);
			console.log(connectorStyle);
			console.log(rotateTransform);
			*/

	return (
		<g style={{cursor:"crosshair"}}
			onMouseDown={(event) => this.selectConnector(event)}
			onMouseMove={(event) => this.mouseMove(event)}
			onMouseUp={(event) => this.deselectConnector(event)}
		>
		<g transform={rotateTransform}>
		<line x1={this.props.x} y1={this.props.y} x2={this.props.x} y2={this.props.y+10} style={connectorStyle} />
		<circle className={circleClass} cx={this.props.x} cy={this.props.y+15} r="7" style={connectorStyle} />
		</g>
		</g>
		);
  }
}

export default ElementOutputConnector;
