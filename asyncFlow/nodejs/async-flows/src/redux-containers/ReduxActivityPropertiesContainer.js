import { connect } from 'react-redux'
import ReduxActivityProperties from '../redux-components/ReduxActivityProperties'
import { changeActivityProperty } from '../redux-containers/ActivityContainerFunctions';




const mapStateToProps = state => {
  // console.log("mapStateToProps");
  // console.log(state.aFlux);

  return {
    divStyle:{
      bottom: 300-state.aFlux.footerYPosition+"px"
    },
    title:state.aFlux.currentActivity.name,
    properties:state.aFlux.currentActivity.properties,
    currentActivity:state.aFlux.currentActivity

  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  // console.log(ownProps);

  return {

    changeProperty:(event,id,currentActivity) =>{
      changeActivityProperty(id,event.target.value,currentActivity);
      // console.log("changeProperty");
      // console.log(id);
    }

  }
}

const ReduxActivityPropertiesContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxActivityProperties)

export default ReduxActivityPropertiesContainer
