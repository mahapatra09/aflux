import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';


/**
Right panel where user can edit node properties
*/
const ReduxActivityProperties = ({divStyle,title,properties,currentActivity,
      // functions
      changeProperty
    }) => {
      var propertiesEditor=
      <div className="PropertiesContainer" style={divStyle}>
        <div className="PropertiesMainContent" style={divStyle}>
            <div className="PropertiesInnerContent">
                <div className="PropertiesMainTitle">{title}</div>
                <div className="PropertiesContent">
                    <div className="PropertiesSection">
                    {properties.map((element,i) =>
                    <div className="PropertiesField" key={i} >
                    <span className="PropertiesLabel">{element.name}</span>
                    <span className="PropertiesInput">
                        <input name={"property"+i}
                          type="text"
                          onChange={(event) => changeProperty(event,i,currentActivity) }
                          value={element.value}
                          readOnly={i<=2 && currentActivity.parentActivityId>=0}/>
                    </span>
                    </div>)}

                    </div>
                </div>
            </div>
        </div>
      </div>
      return propertiesEditor;
}


export default ReduxActivityProperties
