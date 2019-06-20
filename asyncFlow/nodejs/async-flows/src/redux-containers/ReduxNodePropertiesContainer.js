import { connect } from 'react-redux'
import ReduxNodeProperties from '../redux-components/ReduxNodeProperties'

import { changeNodePropertyAction } from '../redux-actions/propertiesActionTypes';
import { setHelpAction } from '../redux-actions/propertiesActionTypes';


const mapStateToProps = state => {
  // console.log(state);




  return {
    hint:state.aFlux.currentHint,
    help:state.aFlux.currentHelp,
    divStyle:{
      bottom: 300-state.aFlux.footerYPosition+"px"
    },
    title:state.aFlux.selectedNodeElement.name,
    errors:state.aFlux.selectedNodeElement.errors,
    properties:state.aFlux.selectedNodeElement.properties,
    nodeElement:state.aFlux.selectedNodeElement,
    currentPropertyValues:state.aFlux.currentPropertyValues,
    activityId:state.aFlux.currentActivity.id

  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  // console.log(ownProps);

  return {

    setHelp:(html,hint) => {
      dispatch(setHelpAction(hint,html));
    }
  }
}

const ReduxNodePropertiesContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxNodeProperties)

export default ReduxNodePropertiesContainer
