import { connect } from 'react-redux'
import ReduxFlowTabs from '../redux-components/ReduxFlowTabs'



const mapStateToProps = state => {
  // console.log("ReduxFlowTabsContainer.mapStateToProps");
  // console.log(state);
  // console.log(state.aFlux);
  return {
    activities:state.aFlux.activities,
    currentActivityIndex:state.aFlux.currentActivityIndex,
    left:parseInt(state.aFlux.sideBarWidth)+12+"px",
    bottom:300-state.aFlux.footerYPosition+"px"
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
  }
}

const ReduxFlowTabsContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxFlowTabs)

export default ReduxFlowTabsContainer
