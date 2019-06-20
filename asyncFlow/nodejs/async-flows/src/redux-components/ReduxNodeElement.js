import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';

/**
ReduxNodeElement	Nodes in the canvas. These represent nodes that were previously saved.
*/
const ReduxNodeElement = ({height,textHeight,selected,type,identifier,x,y,width,color,name,dragging,
      pressedMouse,isMovingNode,isSubFlow,
  // functions
      // from parent
        onMouseDown,onMouseMove,finishNodeMoving,finishNodeCreation
      // from container


    }) => {
       if (isSubFlow) {
         var openBrackets="[";
         var closeBrackets="]";
       } else {
         var openBrackets="";
         var closeBrackets="";
       }
      const translateY = height/2+textHeight/2;
  		const translateString = "translate(0,"+translateY+")";
          var textClassName = "textFlowGItem";
  		var className="flowGItem";
  		if (selected) {
  			className+=" selected-flow-element";
  		}

  		var contentRect = (
  			<g className={!selected ? "flowGItem" : "selected-flow-element"} transform="translate(1.5,1.5)">
  				<rect id={type+identifier} style={{
  					cursor: 'move'}} x={x} y={y} rx="8" ry="8" width={width+"px"}
  					height={height+"px"} fill={color} stroke="#000000" pointerEvents="all">
  				</rect>
  			</g>
  		)
  		var contentText= (
  			<g className={textClassName}>
  				<g transform={translateString}>
  					<foreignObject x={x} y={y-textHeight} pointerEvents="all" width={width+"px"} height={textHeight+"px"}>
  							<div>
  								<div xmlns="http://www.w3.org/1999/xhtml" x="50%" y="50%" alignmentBaseline="middle"
  									textAnchor="middle">{openBrackets}{name}{closeBrackets}</div>
  							</div>
  					</foreignObject>
  				</g>
  			</g>
  		);
  		if (type==="newElement") {
  	return <g style={{
  			opacity: dragging ? 0.4 : 1,
  			cursor: 'move'}}
  			onMouseUp={(event) => finishNodeCreation(event)}
  		>{contentRect}
  			{contentText}
  		</g>;
  		} else {
  	return <g style={{
  			opacity: dragging ? 0.4 : 1,
  			cursor: 'move'}}
  			onMouseDown={(event) => onMouseDown(event)}
  			onMouseMove={(event) => onMouseMove(event,pressedMouse)}
  			onMouseUp={(event) => finishNodeMoving(event,isMovingNode)}
  		>{contentRect}
  			{contentText}
  		</g>;
  		}

  }




export default ReduxNodeElement;
