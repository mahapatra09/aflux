import { connect } from 'react-redux'
import ReduxPropertiesPanel from '../redux-components/ReduxPropertiesPanel'




const mapStateToProps = state => {
  console.log("ReduxPropertiesPanel:mapStateToProps");
  console.log(state.aFlux.currentPropertyValues);

  return {
    infoProperties:state.aFlux.infoProperties,
    nodeElement:state.aFlux.selectedNodeElement,

  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  //console.log(ownProps);


  //console.log("ReduxPropertiesPanelContainer:mapDispatchToProps");
  //console.log(ownProps);

  return {

  }
}

const ReduxPropertiesPanelContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxPropertiesPanel)

export default ReduxPropertiesPanelContainer
