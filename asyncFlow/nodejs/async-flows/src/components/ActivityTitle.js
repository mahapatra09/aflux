import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class ActivityTitle extends Component {

  render() {
    return (
		<div className="FluxActivityTitle">{this.props.jobName}</div>
    );
  }
}

export default ActivityTitle;
