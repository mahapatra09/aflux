import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import ReduxNodeElementContainer from '../redux-containers/ReduxNodeElementContainer';

/**
Each one of the tools present in the left tool panel
*/
const ReduxToolNodeElement = ({width,height,identifier,textHeight,name,color,

  // functions
      // from parent

      // from container
        createNode,deselectTool,moveTool
    }) => {

      return (
  		<a href="javascript:void(0);" className="flowItem" width={parseInt(width)+9+"px"}>
  			<svg>
  				<g>
  					<g></g>
  					<ReduxNodeElementContainer
  						height={height}
  						identifier={identifier}
  						width={width}
  						textHeight={textHeight}
  						name={name}
  						type="tool"
  						x="0"
  						y="0"
  						color={color}
  						selectNode={(event) => createNode(event,identifier)}
  						deselectNode={event => deselectTool(event)}
  						moveNodeElement={(event) => moveTool(event)}/>

  						}
  					/>
  					<g></g>
  					<g></g>
  				</g>
  			</svg>
  		</a>
      );

  }




export default ReduxToolNodeElement;
