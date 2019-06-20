

import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class ElementInputConnector extends Component {
	constructor(props) {
		super(props);
		this.state={
			isSelected:false,
			className:"input-connector"
		}

	}


	onMouseEnter() {
		if (this.props.isCreatingLink) {
			this.setState({
				isSelected:true,
				className:"input-connector-hover"
			});
		}
	}

	onMouseUp() {
		if (this.props.isCreatingLink && this.state.isSelected) {
			this.props.createLink(this.props.identifier);
			//this.props.validateJob();
		} else {
		}
		this.setState({
			isSelected:false,
			className:"input-connector"
		});
	}
	onMouseLeave() {
		if (this.props.isCreatingLink) {
			this.setState({
				isSelected:false,
				className:"input-connector"
			});
		}
	}
  render() {
		var drawCircle;
		var rotateTransform;
		if (this.props.side==="top") {
			drawCircle = "M"+(this.props.x-5)+","+(this.props.y-15)+" a1,1 0 0,0 10,0";
			rotateTransform ="rotate(0 "+this.props.x+" "+this.props.y+")";
		} else {
			drawCircle = "M"+(this.props.x-5)+","+(this.props.y-15)+" a1,1 0 0,0 10,0";
			rotateTransform ="rotate(270 "+this.props.x+" "+this.props.y+")";
		}
	return (
		<g className={this.state.className}
		onMouseUp={(event) => this.onMouseUp()}
		onMouseEnter={(event) => this.onMouseEnter()}
		onMouseLeave={(event) => this.onMouseLeave()}

		>
		<g transform={rotateTransform}>
		<rect x={this.props.x-7} y={this.props.y-25} width="14" height="25"
			// onMouseUp={(event) => alert("mouseUp")}
			// onMouseEnter={(event) => alert("mouseEnter")}
			// onMouseLeave={(event) => alert("mouseLeave")}
			//onMouseUp={(event) => alert("mouseUp");this.onMouseUp(event)}
			//onMouseEnter={(event) => alert("mouseEnter");this.onMouseEnter(event)}
			//onMouseLeave={(event) => alert("mouseLeave");this.onMouseLeave(event)}
			/>
		<line x1={this.props.x} y1={this.props.y} x2={this.props.x} y2={this.props.y-10}  />
		<path d={drawCircle}  />
		</g>
		</g>
		);
  }
}

export default ElementInputConnector;



/*


*/
