import { connect } from 'react-redux'
import ReduxActivityTab from '../redux-components/ReduxActivityTab'



const mapStateToProps = state => {
  return {
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
  }
}

const ReduxActivityTabContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxActivityTab)

export default ReduxActivityTabContainer
