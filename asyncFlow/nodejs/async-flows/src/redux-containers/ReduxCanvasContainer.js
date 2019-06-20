import { connect } from 'react-redux'
import ReduxCanvas from '../redux-components/ReduxCanvas'



const mapStateToProps = state => {
  console.log("ReduxCanvasContainer:mapStateToProps");
  console.log("flowElements:");
  console.log(state.aFlux.flowElements);


  return {
    isMovingNode:state.aFlux.isMovingNode,
    isCreatingNode:state.aFlux.isCreatingNode,
    isCreatingLink:state.aFlux.isCreatingLink,
    left:parseInt(state.aFlux.sideBarWidth)+12+"px",
    bottom:300-state.aFlux.footerYPosition+30+"px",
    flowElements:state.aFlux.flowElements,
    flowConnectors:state.aFlux.flowConnectors,
    selectedNodeId:state.aFlux.selectedNodeId,
    selectedConnectorId:state.aFlux.selectedConnectorId,
    selectedSourceId:state.aFlux.selectedSourceId,
    selectedSourceIndex:state.aFlux.selectedSourceIndex,
    newConnectorId:state.aFlux.newConnectorId,
    newConnectorPosition:state.aFlux.newConnectorPosition,
    connectorPositions:state.aFlux.connectorPositions,
    settings:state.aFlux.settings,
    connectorsSides:"left-right"
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("ReduxCanvasContainer:mapDispatchToProps");
  // console.log(ownProps);
  return {

    deselectSourceConnector:(event) => {
  		ownProps.deselectSourceConnector(event);
  	},


  	onMouseDown:(event) => {
  		ownProps.deselectNode(event);
  		ownProps.deselectLink();
  		ownProps.showActivityProperties();
  	},

  	onMouseUp:(event,isCreatingLink,isMovingNode,isCreatingNode) =>{

  		if (isCreatingLink) {
  			ownProps.cancelLinkCreation(event);
  		}
  		if (isMovingNode) {
  			ownProps.finishNodeMoving(event);
  		}
  		if (isCreatingNode) {
  			ownProps.finishNodeCreation(event);
  		}
  	},
  	moveNodeElementInCanvas:(event,selectedNodeId) => {
  			ownProps.moveNodeElement(event);
  			ownProps.updateConnectors(selectedNodeId);
  	},
  	moveNode: (event,isMovingNode,selectedSourceId,selectedNodeId)  => {

  		if (isMovingNode) {
  			// moving flow Element
        ownProps.moveNodeElement(event);
  			ownProps.updateConnectors(selectedNodeId);
  		}
  		if (selectedSourceId>=0) {
  			// creating link
  			ownProps.gotoOutputConnector(event);
  		}
  	}

  }
}

const ReduxCanvasContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxCanvas)

export default ReduxCanvasContainer
