import { connect } from 'react-redux'
import ReduxCanvasNodeElement from '../redux-components/ReduxCanvasNodeElement'



const mapStateToProps = state => {
  return {
    textHeight:state.aFlux.textElementHeight,
    type:"element",
    isMovingNode:state.aFlux.isMovingNode,
    isCreatingNode:state.aFlux.isCreatingNode,
    isCreatingLink:state.aFlux.isCreatingLink,
    settings:state.aFlux.settings,
    connectorsSides:"left-right",
    nodeElementSettings:state.aFlux.settings.nodeElement


  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("ReduxCanvasContainer:mapDispatchToProps");
  // console.log(ownProps);
  return {

    deselectSourceConnector:(event)  =>{
			ownProps.deselectSourceConnector(event);
		}


  }
}

const ReduxCanvasNodeElementContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxCanvasNodeElement)

export default ReduxCanvasNodeElementContainer
