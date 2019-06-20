import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import ReduxToolNodeElementContainer from '../redux-containers/ReduxToolNodeElementContainer';

/**
Left toolbar container
*/
const ReduxNodeElement = ({elementTypeWidht,elementTypeHeight,textElementTypeHeight,width,bottom,
    toolSet,

  // functions
      // from parent
        createNode,
      // from container
        deselectTool,moveTool
    }) => {
      const toolSetView = toolSet.map((element)=>
    	<ReduxToolNodeElementContainer key={element.id}
    				identifier={element.id}
    				width={elementTypeWidht}
    				height={elementTypeHeight}
    				textHeight={textElementTypeHeight}
    				name={element.name}
    				color={element.color}
    				createNode={(event,id,position) => createNode(event,id,position)}
    				deselectTool={(event) => deselectTool(event)}
    				moveTool={(event) => moveTool(event)}
    				/>
    	)

        return (
          <div className="SideBarContainer" id="sideBarContainer"
    			style={{width:width,
    			bottom:bottom}}>
    		<div className="div-block">
    		<div className="FlowToolBar">
    		{toolSetView}
    		</div>
    		</div>
          </div>
        );


  }




export default ReduxNodeElement;
