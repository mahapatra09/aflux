import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import ReduxActivityTabContainer from '../redux-containers/ReduxActivityTabContainer';

/**
Tab Panel where Activity tabs are shown
*/

const ReduxFlowTabs = ({left,bottom,activities,currentActivityIndex,
      // functions
      selectActivity
    }) => {
        // console.log("ReduxFlowTabs");
        // console.log(activities);
      return (
        <div className="FlowTabsContainer" style={{left:left,bottom:bottom}}>
  		<div className="FlowTabSet">
  		{activities.map((activity,i) => <ReduxActivityTabContainer
            key={i}
  			    selectActivity={(activityIndex) =>selectActivity(activityIndex)}
            name={activity.name}
            isSubFlow={activity.parentActivityId>=0}
  			    selected={i===currentActivityIndex}
  			    activityLocalIndex={i}/>)};
  		</div>
        </div>
      );
}


export default ReduxFlowTabs
