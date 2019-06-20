import { connect } from 'react-redux'
import ReduxRunningProcessesProperties from '../redux-components/ReduxRunningProcessesProperties'

const mapStateToProps = state => {
  // console.log("mapStateToProps");
  // console.log(state.aFlux);

  return {

    divStyle:{
      bottom:300-state.aFlux.footerYPosition+"px"
    },
    title:"Running Processes",
    runningProcesses:state.aFlux.runningProcesses
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  // console.log(ownProps);

  return {

    changeProperty:(event,id) =>{
      ownProps.changeProperty(id,event.target.value);
    }
  }
}

const ReduxRunningProcessesPropertiesContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxRunningProcessesProperties)

export default ReduxRunningProcessesPropertiesContainer
