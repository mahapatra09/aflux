import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';

import ReduxNodePropertyInputContainer from '../redux-containers/ReduxNodePropertyInputContainer';

/**
Properties of a node
*/
const ReduxNodeProperties = ({divStyle,title,properties,hint,help,errors,currentPropertyValues,nodeElement,
      // functions
      changeProperty,setHelp,activityId,
    }) => {

      console.log("properties--");
      console.log(properties);
      var propertiesEditor=
      <div className="PropertiesContainer" style={divStyle} >
        <div className="PropertiesMainContent" style={divStyle}>
            <div className="PropertiesInnerContent">
                <div className="PropertiesMainTitle">{title}</div>
                <div className="PropertiesContent">
                    <div className="PropertiesSection">
                    {properties.map((element,i) =>
                      <ReduxNodePropertyInputContainer key={i} elementIndex={i} />
                  )}
                    </div>
                    <div className="HelpSection">
                        <div><textarea value={help} readOnly="true" rows="2" height="100%"/></div>
                        <div><textarea value={hint} readOnly="true" rows="6" height="100%"/></div>
                    </div>
                    <div className="ErrorsSection">
                        <div><textarea value={errors} readOnly="true" rows="6" height="100%"/></div>
                    </div>
                </div>
            </div>
        </div>
      </div>
      return propertiesEditor;
}


export default ReduxNodeProperties
