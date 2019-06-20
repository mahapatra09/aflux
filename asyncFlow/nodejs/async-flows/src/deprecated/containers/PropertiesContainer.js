// OBSOLETE Migrate to ReduxPropertiesPanel



import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import NodeProperties from '../components/NodeProperties';
import RunningProcessesProperties from '../components/RunningProcessesProperties';
import ActivityProperties from '../components/ActivityProperties';


class PropertiesContainer extends Component {
  changeNodeProperty(id,value) {
    this.props.changeNodeProperty(id,value);
  }
  changeActivityProperty(id,value) {
    this.props.changeActivityProperty(id,value);
  }
  render() {
	const divStyle = {
		bottom: this.props.bottom,
	};
  if (this.props.infoProperties==="RUNNING PROCESSES") {
    return (<RunningProcessesProperties
      divStyle={divStyle}
      title={"Running Processes"}
      runningProcesses={this.props.runningProcesses}
      />);
  } else if (this.props.nodeElement!=null) {
    return (<NodeProperties
        divStyle={divStyle}
        title={this.props.nodeElement.name}
        properties={this.props.nodeElement.properties}
        changeProperty={(id,value) => this.changeNodeProperty(id,value)}
        />);

  } else { // Show current activity properties
    return (<ActivityProperties
        divStyle={divStyle}
        title={this.props.activity.name}
        properties={this.props.activity.properties}
        changeProperty={(id,value) => this.changeActivityProperty(id,value)}
      />)
  }
}
}

export default PropertiesContainer;
