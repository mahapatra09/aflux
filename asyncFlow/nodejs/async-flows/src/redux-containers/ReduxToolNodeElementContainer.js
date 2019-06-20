import { connect } from 'react-redux'
import ReduxToolNodeElement from '../redux-components/ReduxToolNodeElement'
import { getAbsolutePosition } from '../functions/WorkspaceManager';



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
  	moveTool:(event) =>{
  		ownProps.moveTool(event);
  	},
  	createNode:(event,identifier) =>{
  			var toolElement=event.currentTarget.parentElement.parentElement.parentElement;
  			var position={
  				x:toolElement.offsetLeft,
  				y:toolElement.offsetTop,
  				absolute:getAbsolutePosition(toolElement)
  			}
  			ownProps.createNode(event,identifier,position);

  	}





  }
}

const ReduxToolNodeElementContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxToolNodeElement)

export default ReduxToolNodeElementContainer
