import { connect } from 'react-redux'
import ReduxNodePropertyInput from '../redux-components/ReduxNodePropertyInput'

import { changeNodePropertyAction } from '../redux-actions/propertiesActionTypes';
import { setHelpAction } from '../redux-actions/propertiesActionTypes';


const mapStateToProps = state => {
  // console.log(state);


  return {

    nodeElementId:state.aFlux.selectedNodeElement.id,
    activityId:state.aFlux.currentActivity.id,
    properties:state.aFlux.selectedNodeElement.properties,
    lastChangedValue:state.aFlux.lastChangedValue
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  // console.log(ownProps);

  return {

    changeProperty:(activityId,nodeId,propertyId,value) => {
      dispatch(changeNodePropertyAction(activityId,nodeId,propertyId,value));
    },
    setHelp:(html,hint) => {
      dispatch(setHelpAction(hint,html));
    }
  }
}

const ReduxNodePropertyInputContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxNodePropertyInput)

export default ReduxNodePropertyInputContainer
