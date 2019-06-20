import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class ActivityTab extends Component {
  render() {
    return (
	<div title="{this.props.name}"  draggable="false"
		className={this.props.selected?"FlowTabSelected":"FlowTab"}
		onMouseDown={(event)=>{this.props.selectActivity(this.props.activityLocalIndex)}}>{this.props.name}</div>
    );
  }
}

export default ActivityTab;
