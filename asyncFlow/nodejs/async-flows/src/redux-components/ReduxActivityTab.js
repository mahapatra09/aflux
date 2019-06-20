import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';

/**
ReduxActivityTab	One only tab in the tab pane that contains the name of the activity and can be clicked to select the activity
*/


const ReduxActivityTab = ({selected,activityLocalIndex,name,isSubFlow,
      // functions
      selectActivity
    }) => {

      if (isSubFlow) {
        var openBrackets="[";
        var closeBrackets="]";
      } else {
        var openBrackets="";
        var closeBrackets="";
      }



      return (
  	<div title="{this.props.name}"  draggable="false"
  		className={selected?"FlowTabSelected":"FlowTab"}
  		onMouseDown={(event)=>{selectActivity(activityLocalIndex)}}>{openBrackets}{name}{closeBrackets}</div>
      );
}


export default ReduxActivityTab
