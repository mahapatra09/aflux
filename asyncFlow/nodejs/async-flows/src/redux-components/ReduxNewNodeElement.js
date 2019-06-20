import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import ReduxNodeElementContainer from '../redux-containers/ReduxNodeElementContainer';

/**
Nodes that have not been saved (added recently)
Where they are saved they are converted to ReduxCanvasNodeElement
*/
const ReduxNewNodeElement = ({x,y,height,identifier,width,textHeight,name,color,

  // functions
      // from parent
      moveTool,deselectTool
      // from container

    }) => {

      var styleNewElement={
  			top:y,
  			left:x
  		}
  		return (
  			<svg className="newNodeElement" id="newNodeElement" style={styleNewElement}
  			onMouseMove={(event) => moveTool(event)}
  			>
  				<g>
  					<g></g>
  					<ReduxNodeElementContainer
  						height={height}
  						identifier={identifier}
  						width={width}
  						textHeight={textHeight}
  						name={name}
  						color={color}
  						type="newElement"
  						x="0"
  						y="0"
  						moveNodeElement={(event) => moveTool(event)}
  						finishNodeCreation={(event) => deselectTool(event)}
  						/>
  						}
  					/>
  					<g></g>
  					<g></g>
  				</g>
  			</svg>
      );
    }

export default ReduxNewNodeElement;
