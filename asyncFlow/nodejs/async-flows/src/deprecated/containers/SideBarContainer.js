import React, { Component } from 'react';
import ToolNodeElement from '../components/ToolNodeElement';
import '../AsyncFlowsApp.css';

class SideBarContainer extends Component {

	constructor(props) {
		super(props);
		this.state={
		};
	}

	deselectTool(event) {
		this.props.deselectTool(event);
	}
	moveTool(event) {
		this.props.moveTool(event);
	}

  render() {


	const toolSet = this.props.toolSet.map((element)=>
	<ToolNodeElement key={element.id}
				identifier={element.id}
				width={this.props.elementTypeWidht}
				height={this.props.elementTypeHeight}
				textHeight={this.props.textElementTypeHeight}
				name={element.name}
				color={element.color}
				createNode={(event,id,position) => this.props.createNode(event,id,position)}
				deselectTool={(event) => this.deselectTool(event)}
				moveTool={(event) => this.moveTool(event)}
				/>
	)

    return (
      <div className="SideBarContainer" id="sideBarContainer"
			style={{width: this.props.width,
			bottom:this.props.bottom}}>
		<div className="div-block">
		<div className="FlowToolBar">
		{toolSet}
		</div>
		</div>
      </div>
    );
  }
}

export default SideBarContainer;
