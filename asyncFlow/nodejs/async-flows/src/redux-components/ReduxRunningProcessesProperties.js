import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';

/**
Properties Panel content while it is showing the number of processes that are running
*/
const ReduxRunningProcessesProperties = ({divStyle,title,runningProcesses
      // functions
    }) => {
      var propertiesEditor=
      <div className="PropertiesContainer" style={divStyle}>
        <div className="PropertiesMainContent" style={divStyle}>
            <div className="PropertiesInnerContent">
                <div className="PropertiesMainTitle">{title}</div>
                {runningProcesses.map((jobProcess,i)=>
                  <div key={i}>
                    <span>{jobProcess.jobName}</span><span>--:--</span>
                    <span>{jobProcess.activityName}</span>
                  </div>)}
            </div>
        </div>
      </div>
      return propertiesEditor;
}


export default ReduxRunningProcessesProperties
