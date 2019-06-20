import { connect } from 'react-redux'
import ReduxNodeElement from '../redux-components/ReduxNodeElement'
import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';



const mapStateToProps = state => {
  // console.log("ReduxFlowTabsContainer.mapStateToProps");
  // console.log(state);
  // console.log(state.aFlux);
  return {
    dragging:false,
    pressedMouse:state.aFlux.pressedMouse,
    isMovingNode:state.aFlux.isMovingNode
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {

      onMouseDown:(event) =>{

        dispatch(applyGeneralActionAction({
    			pressedMouse:true,
    		},"pressMouse"));
    		ownProps.selectNode(event);
    	},

    	onMouseMove:(event,pressedMouse) =>{
    		if (pressedMouse) {
    			ownProps.moveNodeElement(event)
    		}
    	},
    	finishNodeMoving:(event,isMovingNode) =>{
        dispatch(applyGeneralActionAction({
    			pressedMouse:false,
    		},"unPressMouse"));
    		if (isMovingNode) {
    			ownProps.finishNodeMoving(event);
    		}

    	},
    	finishNodeCreation:(event) =>{
        dispatch(applyGeneralActionAction({
    			pressedMouse:false,
    		},"unPressMouse"));
    		ownProps.finishNodeCreation(event)
    	}


  }
}

const ReduxNodeElementContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxNodeElement)

export default ReduxNodeElementContainer
