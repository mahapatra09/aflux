import { connect } from 'react-redux'
import ReduxNewNodeElement from '../redux-components/ReduxNewNodeElement'



const mapStateToProps = state => {
  // console.log("ReduxFlowTabsContainer.mapStateToProps");
  // console.log(state);
  // console.log(state.aFlux);
  return {
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
  }
}

const ReduxNewNodeElementContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxNewNodeElement)

export default ReduxNewNodeElementContainer
