import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import NodeElement from './NodeElement';
import { getAbsolutePosition } from '../functions/WorkspaceManager';

class ToolNodeElement extends Component {
	deselectTool(event) {
		this.props.deselectTool(event);
	}
	moveTool(event) {
		this.props.moveTool(event);
	}
	createNode(event) {
			var toolElement=event.currentTarget.parentElement.parentElement.parentElement;
			var position={
				x:toolElement.offsetLeft,
				y:toolElement.offsetTop,
				absolute:getAbsolutePosition(toolElement)
			}
			this.props.createNode(event,this.props.identifier,position);

	}
	render() {
		return (
		<a href="javascript:void(0);" className="flowItem" width={parseInt(this.props.width)+9+"px"}>
			<svg>
				<g>
					<g></g>
					<NodeElement
						height={this.props.height}
						identifier={this.props.identifier}
						width={this.props.width}
						textHeight={this.props.textHeight}
						name={this.props.name}
						type="tool"
						x="0"
						y="0"
						color={this.props.color}
						selectNode={(event) => this.createNode(event)}
						deselectNode={event => this.deselectTool(event)}
						moveNodeElement={(event) => this.moveTool(event)}/>

						}
					/>
					<g></g>
					<g></g>
				</g>
			</svg>
		</a>
    );
  }
}

export default ToolNodeElement;
