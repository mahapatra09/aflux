import React from 'react'
import PropTypes from 'prop-types'





import '../AsyncFlowsApp.css';

import ReduxNodePropertiesContainer from '../redux-containers/ReduxNodePropertiesContainer';
import ReduxActivityPropertiesContainer from '../redux-containers/ReduxActivityPropertiesContainer';
import ReduxRunningProcessesPropertiesContainer from '../redux-containers/ReduxRunningProcessesPropertiesContainer';

/**
Right Panel to hold properties
*/

const ReduxPropertiesPanel = ({infoProperties,nodeElement,
      // functions
    }) => {

      if (infoProperties==="RUNNING PROCESSES") {
        return (<ReduxRunningProcessesPropertiesContainer />);
      } else if (nodeElement!=null) {
        return (<ReduxNodePropertiesContainer  />);

      } else { // Show current activity properties
        return (<ReduxActivityPropertiesContainer />)
      }
}


export default ReduxPropertiesPanel
