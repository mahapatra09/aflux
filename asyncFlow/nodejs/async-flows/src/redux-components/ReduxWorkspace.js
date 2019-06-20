import React from 'react'
import PropTypes from 'prop-types'


//components
import HeaderContainer from '../containers/HeaderContainer';
import ReduxSideBarContainer from '../redux-containers/ReduxSideBarContainer';
import ReduxPropertiesPanelContainer from '../redux-containers/ReduxPropertiesPanelContainer';

import ReduxCanvasContainer from '../redux-containers/ReduxCanvasContainer';
import ToolBarContainer from '../containers/ToolBarContainer';
import FooterContainer from '../containers/FooterContainer';
import AddToolButtonContainer from '../containers/AddToolButtonContainer';
import ReduxFlowTabsContainer from '../redux-containers/ReduxFlowTabsContainer';


// screens
import JobSelector from '../screens/JobSelector';
import AreYouSureDialog from '../screens/AreYouSureDialog';
import ChangeNameDialog from '../screens/ChangeNameDialog';
import UploadPluginDialog from '../screens/UploadPluginDialog';
import PluginManagerPopup from '../screens/PluginManagerPopup';

// model
import ReduxNewNodeElementContainer from '../redux-containers/ReduxNewNodeElementContainer';

import '../AsyncFlowsApp.css';
/**
The whole view. The only child of ReduxAsyncFlowsApp
*/
const ReduxWorkspace = ({sideBarWidth,elementBaseProperties,settings,host,footerYPosition,selectedToolId,
    jobName,toolSet,selectedNodeElement,runningProcesses,currentActivity,infoProperties,isMovingNode,
    isCreatingNode,isCreatingLink,flowElements,flowConnectors,selectedNodeId,selectedConnectorId,
    selectedSourceId,selectedSourceIndex,connectorPositions,
    activities,currentActivityIndex,stdOutput,newNode,jobSelectorOpened,jobList,newActivityDialogOpened,
    pluginList,removeActivityDialogOpened,pluginUploaderOpened,pluginManagerPopupOpened,
    isDraggingFooter,currentActivityName,textElementHeight,
    // functions
    setInitialActivityProperties,setActivityBaseProperties,
    openNewActivityDialog,closeNewActivityDialog,prepareOpenPluginManager,
    closePluginManager,openPluginUploaderDialog,closePluginUploaderDialog,uploadPlugin,
    activatePlugin,deactivatePlugin,removePlugin,finishToolSet,createNewActivity,
    openRemoveActivityDialog,closeRemoveActivityDialog,removeActivity,removeJob,prepareOpenJobSelector,
  	closeJobSelector,openJob,saveJob,saveAs,renameJob,newJob,
  	selectActivity,deleteElement,updateConnectors,
    refreshOutput,stopJob,stopActivity,runJob,runActivity,
    clearOutput,
  	selectSourceConnector,createLink,validateJob,cancelLinkCreation,
    gotoOutputConnector,createNode,beginDragFooter,finishDragFooter,dragFooter,
    selectNode,deselectNode,selectConnector,deselectLink,finishNodeMoving,finishNodeCreation,
    convertCoords,deselectTool,moveTool,moveNodeElement,
    showProcesses,showLastChart,showActivityProperties,deselectSourceConnector,mouseDeltaX,mouseDeltaY,onKeyPressEvent
    }) => {

  var bottomSplit=300-footerYPosition-14+"px";
	var elementWidth=settings.nodeElement.width ;
	var elementHeight=settings.nodeElement.height;
	var isCreatingElement=selectedToolId!=null;
	var styleSplitter={
		height: "12px",
		width:"100%",
		bottom: bottomSplit,
		zIndex:"13000",
		position:"absolute"
	};
  var currentActivityName=activities[currentActivityIndex].name;


    return (
      <div className="Workspace"
	  				onMouseMove={(event) => dragFooter(event)}
						onMouseUp={(event) => finishDragFooter(event)}
            onKeyPres={(event) => onKeyPressEvent(event)}

	  >
		<div className="WorkspaceMain" id="workspace"
		>
			<HeaderContainer
				jobName={jobName}
				newJob={(event)=>newJob()}
				openJob={(name)=>openJob(name)}
				host={host}
				saveJob={()=>saveJob()}
				saveAs={(name)=>saveAs(name)}
				renameJob={(name)=>renameJob(name)}
				prepareOpenJobSelector={() => prepareOpenJobSelector()}
				prepareOpenPluginManager={() => prepareOpenPluginManager()}
				/>
			<ReduxSideBarContainer width={sideBarWidth+"px"}
				elementTypeWidht={elementWidth}
				elementTypeHeight={elementHeight}
				textElementTypeHeight={textElementHeight}
				toolSet={toolSet}
				bottom={300-footerYPosition+36+"px"}
				createNode={(event,id,position) => createNode(event,id,position,flowElements,toolSet,settings,elementBaseProperties,activities)}
				deselectTool={(event) => deselectTool()}
				moveTool={(event) => moveTool(event,selectedToolId,mouseDeltaX,mouseDeltaY,flowElements,toolSet,settings,elementBaseProperties,activities)}
				/>
			<AddToolButtonContainer width={sideBarWidth+"px"}
				bottom={300-footerYPosition+"px"}
				openPluginUploaderDialog={()=>openPluginUploaderDialog()}
			/>
      <ReduxPropertiesPanelContainer />
			<ReduxCanvasContainer
        selectNode={(event,id) => selectNode(event,id)}
        finishNodeMoving={(event) =>finishNodeMoving(event)}
				moveNodeElement={(event) => moveNodeElement(event,flowElements,selectedNodeId,mouseDeltaX,mouseDeltaY)}
				deselectNode={(event) => deselectNode(event)}
				selectConnector={(event,id) => selectConnector(event,id)}
				deselectLink={(event) => deselectLink(event)}
				finishNodeCreation={(event) =>finishNodeCreation(event)}
				cancelLinkCreation={(event) => cancelLinkCreation(event) }
				selectSourceConnector={(event,flowElement,connectoriId) => selectSourceConnector(event,flowElement,connectoriId)}
				createLink={(flowElementId,connectorId) => createLink(flowElementId,connectorId)}
				validateJob={() => validateJob()}
				gotoOutputConnector={(event) => gotoOutputConnector(event)}
				deselectSourceConnector={(event) => deselectSourceConnector(event)}
				updateConnectors={(elementId) => updateConnectors(elementId)}
				showActivityProperties={() => showActivityProperties()}
				/>
			<ReduxFlowTabsContainer
				selectActivity={(activityIndex) => selectActivity(activityIndex)}
			/>
			<ToolBarContainer
				deleteElement={() => deleteElement()}
				runJob={() => runJob()}
				runActivity={() => runActivity()}
				stopJob={() => stopJob()}
				stopActivity={() => stopActivity()}
				saveJob={()=>saveJob()}
				clearOutput={()=>clearOutput()}
				refreshOutput={()=>refreshOutput()}
				openNewActivityDialog={()=>openNewActivityDialog()}
				openRemoveActivityDialog={()=>openRemoveActivityDialog()}
				prepareOpenJobSelector={() => prepareOpenJobSelector()}
				showProcesses={() => showProcesses()}
        showLastChart={() => showLastChart()}
				/>
		<div className="footerSplit"
			id="footerSplit"
			title="Collapse/Expand"
			style={styleSplitter}
			onMouseDown={(event)=>beginDragFooter(event)}
			onMouseMove={(event) => dragFooter(event)}
			onMouseUp={(event) => finishDragFooter(event)}
			></div>
	</div>
		<FooterContainer stdOutput={stdOutput}
			height={438+728-window.outerHeight-footerYPosition+"px"}
			top={window.innerHeight-165+60+34+12+footerYPosition-275+"px"}
		/>
	  {isCreatingElement &&
		  <ReduxNewNodeElementContainer
				height={newNode.height}
				identifier={newNode.id}
				width={newNode.width}
				textHeight={textElementHeight}
				name={newNode.name}
				color={newNode.color}
				x={newNode.x}
				y={newNode.y}
				deselectTool={event => deselectTool()}
				moveTool={(event) =>moveTool(event,selectedToolId,mouseDeltaX,mouseDeltaY,flowElements,toolSet,settings,elementBaseProperties,activities)} />
	  }
		{jobSelectorOpened &&
		<JobSelector openJob={(flowJobId)=>{openJob(flowJobId)}}
			closeJobSelector={()=>closeJobSelector()}
			jobs={jobList}
			removeJob={(id,name) => removeJob(id,name)}/>
		}
		{newActivityDialogOpened  &&
		<ChangeNameDialog label="New Activity Name:" changeName={(newName)=>createNewActivity(newName)} closeDialog={()=>closeNewActivityDialog()} oldName=""/>
		}
		{removeActivityDialogOpened  &&
		<AreYouSureDialog label={"Are you sure you want to remove Actvity:"+currentActivityName} answerYes={()=>removeActivity()} closeDialog={()=>closeRemoveActivityDialog()} />
		}
		{pluginUploaderOpened  &&
		<UploadPluginDialog label="Upload Plugin File:"
			closeDialog={()=>closePluginUploaderDialog()}
			accept={((fileName,file)=>uploadPlugin(fileName,file))}
			oldName="" />
		}
		{pluginManagerPopupOpened &&
			<PluginManagerPopup plugins={pluginList}
					closePopup={()=>closePluginManager()}
					activatePlugin={(id)=>activatePlugin(id)}
					deactivatePlugin={(id)=>deactivatePlugin(id)}
					removePlugin={(id)=>removePlugin(id)}
					>
			</PluginManagerPopup>
		}

      </div>

    );

}



export default ReduxWorkspace
