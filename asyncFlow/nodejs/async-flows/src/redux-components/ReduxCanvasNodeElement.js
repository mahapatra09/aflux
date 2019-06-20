import React from 'react'
import PropTypes from 'prop-types'

import '../AsyncFlowsApp.css';
import { calculateElementHeight } from '../redux-containers/ElementContainerFunctions';
import { getConnectorPosition } from '../redux-containers/ConnectorContainerFunctions';
import { calculateSide } from '../redux-containers/ConnectorContainerFunctions';
import ReduxNodeElementContainer from '../redux-containers/ReduxNodeElementContainer';
import ElementInputConnector from '../components/ElementInputConnector'
import ElementOutputConnector from '../components/ElementOutputConnector'

/**
ReduxCanvasNodeElement	Container to hold Nodes.
It holds ReduxNewNodeElement or ReduxNodeElement.
It is the node from the perspective of the canvas
(Canvas does not know if it is new or not… it only has a box in a position)
*/
const ReduxCanvasNodeElement = ({  height,connectorsSides,inputInterfaces,outputInterfaces,asyncInterface,
    nodeElementSettings,x,y,width,isCreatingLink,identifier,textHeight,name,color,selected,
    isMovingNode,isSubFlow,

  // functions
      // from parent
      createLink,validateJob,selectSourceConnector,gotoOutputConnector,selectNode,finishNodeMoving,moveNodeElement
      // from container

    }) => {

      var elementHeight=
				calculateElementHeight(height,
					connectorsSides,
					inputInterfaces,
					outputInterfaces,
					asyncInterface,
					nodeElementSettings);
      console.log("ReduxCanvasNodeElement.js");
      console.log(elementHeight);
			var inputInterfacesNewList=[];
			for (var i=0;i<inputInterfaces;i++) {
				inputInterfacesNewList.push({
					key:i+1,
					position:getConnectorPosition(
						x,
						y,
						width,
						height,
						calculateSide("input",connectorsSides),
						asyncInterface,
						i+1,
						nodeElementSettings,
						inputInterfaces
					),
					side:calculateSide("input",connectorsSides)
				});
			}
		var outputInterfacesNewList=[];
		if (asyncInterface) {
			outputInterfacesNewList.push({
				key:-1,
				position:getConnectorPosition(
						x,
						y,
						width,
						height,
						calculateSide("output",connectorsSides),
						asyncInterface,
						-1,   // is async connector
						nodeElementSettings,
						inputInterfaces
					),
				side:calculateSide("output",connectorsSides),
				isAsync:true
			})
		}
		for (var i=0;i<outputInterfaces;i++) {
			outputInterfacesNewList.push({
				key:i+1,
				position:getConnectorPosition(
						x,
						y,
						width,
						height,
						calculateSide("output",connectorsSides),
						asyncInterface,
						i+1,   // is async connector
						nodeElementSettings,
						inputInterfaces
					),
				side:calculateSide("output",connectorsSides),
				isAsync:false
		}) }
			var inputInterfacesList=inputInterfacesNewList.map((inputInterface) =>
				<ElementInputConnector
					isCreatingLink={isCreatingLink}
					side={inputInterface.side}
					key={inputInterface.key}
					identifier={inputInterface.key}
					x={inputInterface.position.x}
					y={inputInterface.position.y}
					createLink={(connectorId) => createLink(identifier,connectorId)}
					validateJob={() => validateJob() }
					/>  );
			var outputInterfacesList=outputInterfacesNewList.map((outputInterface) =>
				<ElementOutputConnector
					key={outputInterface.key}
					identifier={outputInterface.key}
					side={outputInterface.side}
					x={outputInterface.position.x}
					y={outputInterface.position.y}
					isAsync={outputInterface.isAsync}
					selectSourceConnector={(event,connectorId) =>
						selectSourceConnector(event,identifier,connectorId)}
					gotoOutputConnector={(event) => gotoOutputConnector(event)}
					deselectSourceConnector={(event) => this.deselectSourceConnector(event)}

					/>  );
		return (
			<g>
				{inputInterfacesList}
				{outputInterfacesList}

					<ReduxNodeElementContainer
						height={elementHeight}
						identifier={identifier}
						width={width}
						textHeight={textHeight}
						name={name}
						type="element"
            isSubFlow={isSubFlow}
						x={x}
						y={y}
						color={color}
						selected={selected}
						isMovingNode={isMovingNode}
						selectNode={(event) => selectNode(event,identifier)}
						finishNodeMoving={event => finishNodeMoving(event)}
						moveNodeElement={(event) => moveNodeElement(event)}/>

						}
					/>
			</g>
    );
  }




export default ReduxCanvasNodeElement;
