import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class NodeElement extends Component {
	constructor(props) {
		super(props);
		this.state={
			id:this.props.identifier,
			pressedMouse:false
		}
	};

	onMouseDown(event) {
		this.setState({
			pressedMouse:true
		}
		);
		this.props.selectNode(event);
	}
	
	onMouseMove(event) {
		if (this.state.pressedMouse) {
			this.props.moveNodeElement(event)
		}
	}
	finishNodeMoving(event) {
		this.setState({
			pressedMouse:false
			}
		);
		if (this.props.isMovingNode) {
			this.props.finishNodeMoving(event);
		}
		
	}
	
	finishNodeCreation(event) {
		this.setState({
			pressedMouse:false
			}
		);
		this.props.finishNodeCreation(event)
	}

  render() {
		const translateY = this.props.height/2+this.props.textHeight/2;
		const translateString = "translate(0,"+translateY+")";
        var textClassName = "textFlowGItem";
		var className="flowGItem";
		if (this.props.selected) {
			className+=" selected-flow-element";
		}


		var contentRect = (
			<g className={!this.props.selected ? "flowGItem" : "selected-flow-element"} transform="translate(1.5,1.5)">
				<rect id={this.props.type+this.props.identifier} style={{
					cursor: 'move'}} x={this.props.x} y={this.props.y} rx="8" ry="8" width={this.props.width+"px"} 
					height={this.props.height+"px"} fill={this.props.color} stroke="#000000" pointerEvents="all">
				</rect>
			</g>
		)
		var contentText= (
			<g className={textClassName}>
				<g transform={translateString}>
					<foreignObject x={this.props.x} y={this.props.y-this.props.textHeight} pointerEvents="all" width={this.props.width+"px"} height={this.props.textHeight+"px"}>
							<div>
								<div xmlns="http://www.w3.org/1999/xhtml" x="50%" y="50%" alignmentBaseline="middle" 
									textAnchor="middle">{this.props.name}</div>
							</div>
					</foreignObject>
				</g>
			</g>
		);
		if (this.props.type==="newElement") {
	return <g style={{
			opacity: this.state.dragging ? 0.4 : 1,
			cursor: 'move'}}	
			onMouseUp={(event) => this.finishNodeCreation(event)}
		>{contentRect}
			{contentText}
		</g>;
		} else {
	return <g style={{
			opacity: this.state.dragging ? 0.4 : 1,
			cursor: 'move'}}
			onMouseDown={(event) => this.onMouseDown(event)}
			onMouseMove={(event) => this.onMouseMove(event)}
			onMouseUp={(event) => this.finishNodeMoving(event)}
		>{contentRect}
			{contentText}
		</g>;
		}
		

  }
}


export default NodeElement;

