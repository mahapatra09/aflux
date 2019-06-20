import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import ActivityTab from '../components/ActivityTab';


class FlowTabsContainer extends Component {
  render() {

    return (
      <div className="FlowTabsContainer" style={{left:this.props.left,bottom:this.props.bottom}}>
		<div className="FlowTabSet">
		{this.props.activities.map((activity,i) => <ActivityTab key={i}
			selectActivity={(activityIndex) =>this.props.selectActivity(activityIndex)} name={activity.name}
			selected={i===this.props.currentActivityIndex}
			activityLocalIndex={i}/>)};
		</div>
      </div>
    );
  }
}

export default FlowTabsContainer;
