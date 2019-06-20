import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import NodeElement from './NodeElement';

class NewNodeElement extends Component {
	render() {
		var styleNewElement={
			top:this.props.y,
			left:this.props.x
		}
		return (
			<svg className="newNodeElement" id="newNodeElement" style={styleNewElement}
			onMouseMove={(event) => this.props.moveTool(event)}
			>
				<g>
					<g></g>
					<NodeElement
						height={this.props.height}
						identifier={this.props.identifier}
						width={this.props.width}
						textHeight={this.props.textHeight}
						name={this.props.name}
						color={this.props.color}
						type="newElement"
						x="0"
						y="0"
						moveNodeElement={(event) => this.props.moveTool(event)}
						finishNodeCreation={(event) => this.props.deselectTool(event)}
						/>
						}
					/>
					<g></g>
					<g></g>
				</g>
			</svg>
    );
  }
}

export default NewNodeElement;
