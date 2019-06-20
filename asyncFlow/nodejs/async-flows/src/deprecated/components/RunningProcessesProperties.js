import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class RunningProcessesProperties extends Component {
  render() {
    var propertiesEditor=
    <div className="PropertiesContainer" style={this.props.divStyle}>
      <div className="PropertiesMainContent" style={this.props.divStyle}>
          <div className="PropertiesInnerContent">
              <div className="PropertiesMainTitle">{this.props.title}</div>
              {this.props.runningProcesses.map((jobProcess,i)=>
                <div key={i}>
                  <span>{jobProcess.jobName}</span><span>--:--</span>
                  <span>{jobProcess.activityName}</span>
                </div>)}
          </div>
      </div>
    </div>
    return propertiesEditor;

  }
}


export default RunningProcessesProperties;
