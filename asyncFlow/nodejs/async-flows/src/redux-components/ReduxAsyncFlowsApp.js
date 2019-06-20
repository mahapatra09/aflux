import React from 'react'
import PropTypes from 'prop-types'
import ReduxWorkspaceContainer from '../redux-containers/ReduxWorkspaceContainer';

/**
The whole view
*/
const ReduxAsyncFlowsApp = ({host,settings,elementBaseProperties,aFlux}) => {
	return (
		<div className="AsyncFlowApp">
			<ReduxWorkspaceContainer {...aFlux}/>
		</div>
	)
}

/*
ReduxAsyncFlowsApp.propTypes = {
	host:PropTypes.string.isRequired,
	settings:PropTypes.object.isRequired,
	elementBaseProperties:PropTypes.array.isRequired,
	aFlux:PropTypes.object.isRequired
}
*/
export default ReduxAsyncFlowsApp
