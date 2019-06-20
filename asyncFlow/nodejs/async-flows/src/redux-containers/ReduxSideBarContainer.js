import { connect } from 'react-redux'
import ReduxSideBar from '../redux-components/ReduxSideBar'



const mapStateToProps = state => {
  // console.log("ReduxFlowTabsContainer.mapStateToProps");
  // console.log(state);
  // console.log(state.aFlux);
  return {
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {

    deselectTool:(event)  =>{
  		ownProps.deselectTool(event);
  	},
  	moveTool:(event)  =>{
  		ownProps.moveTool(event);
  	}


  }
}

const ReduxSideBarContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxSideBar)

export default ReduxSideBarContainer
