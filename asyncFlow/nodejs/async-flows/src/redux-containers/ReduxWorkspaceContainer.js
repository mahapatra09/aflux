import { connect } from 'react-redux'


// actions

import { DESELECT_LINK } from '../redux-actions/workspaceActionTypes';
import { BEGIN_DRAG_FOOTER } from '../redux-actions/workspaceActionTypes';
import { FINISH_DRAG_FOOTER } from '../redux-actions/workspaceActionTypes';

import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';

import { changeFooterYPositionAction } from '../redux-actions/workspaceActionTypes';
import { setInfoPropertiesAction } from '../redux-actions/workspaceActionTypes';

// own functions

import { prepareOpenPluginManager } from '../redux-containers/PluginContainerFunctions';
import { closePluginManager } from '../redux-containers/PluginContainerFunctions';
import { closePluginUploaderDialog } from '../redux-containers/PluginContainerFunctions';
import { redirectToShowPluginManagerPopup } from '../redux-containers/PluginContainerFunctions';
import { refreshTools } from '../redux-containers/ToolContainerFunctions';
import { deselectTool } from '../redux-containers/ToolContainerFunctions';
import { moveTool } from '../redux-containers/ToolContainerFunctions';
import { closeNewActivityDialog } from '../redux-containers/ActivityContainerFunctions';
import { openNewActivityDialog } from '../redux-containers/ActivityContainerFunctions';
import { createNewActivity } from '../redux-containers/ActivityContainerFunctions';
import { openRemoveActivityDialog } from '../redux-containers/ActivityContainerFunctions';
import { closeRemoveActivityDialog } from '../redux-containers/ActivityContainerFunctions';
import { removeActivity } from '../redux-containers/ActivityContainerFunctions';
import { selectActivity } from '../redux-containers/ActivityContainerFunctions';
import { showActivityProperties } from '../redux-containers/ActivityContainerFunctions';





import { newJob } from '../redux-containers/JobContainerFunctions';
import { openJobSelector } from '../redux-containers/JobContainerFunctions';
import { closeJobSelector } from '../redux-containers/JobContainerFunctions';
import { setFlowJob } from '../redux-containers/JobContainerFunctions';
import { finishValidateJob } from '../redux-containers/JobContainerFunctions';
import { finishSaveJob } from '../redux-containers/JobContainerFunctions';
import { finishSaveAsJob } from '../redux-containers/JobContainerFunctions';
import { deselectNode } from '../redux-containers/ElementContainerFunctions';
import { deleteFlowElement } from '../redux-containers/ElementContainerFunctions';
import { createNode } from '../redux-containers/ElementContainerFunctions';
import { selectNode } from '../redux-containers/ElementContainerFunctions';
import { finishNodeMoving } from '../redux-containers/ElementContainerFunctions';
import { finishNodeCreation } from '../redux-containers/ElementContainerFunctions';
import { moveNodeElement } from '../redux-containers/ElementContainerFunctions';
import { deleteConnector } from '../redux-containers/ConnectorContainerFunctions';
import { generateNewLinkConnector } from '../redux-containers/ConnectorContainerFunctions';
import { getNewConnectorId } from '../redux-containers/ConnectorContainerFunctions';
import { cancelLinkCreation } from '../redux-containers/ConnectorContainerFunctions';
import { showRunningProcesses } from '../redux-containers/ReduxWorkspaceContainerFunctions';
import { addToStandardOutput } from '../redux-containers/ReduxWorkspaceContainerFunctions';
import { addToStandardOutputCycle } from '../redux-containers/ReduxWorkspaceContainerFunctions';
import { refresh } from '../redux-containers/ReduxWorkspaceContainerFunctions';
import { refreshOutput } from '../redux-containers/ReduxWorkspaceContainerFunctions';

// functions
import { getMousePosition } from '../functions/WorkspaceManager';
import { findElement } from '../redux-containers/ElementContainerFunctions';
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';
import { generateConnectorPosition } from '../redux-containers/ConnectorContainerFunctions';
import { getConnectorSourcePointPosition } from '../redux-containers/ConnectorContainerFunctions';
import { getCanvasContainerElement } from '../functions/WorkspaceManager';
import { refreshActivities } from '../redux-containers/ActivityContainerFunctions';
import { getWorkspaceContainerElement } from '../functions/WorkspaceManager';
import { updateActivitiesOnJob } from '../redux-containers/JobContainerFunctions';
import { onKeyPressEvent } from '../redux-containers/ReduxWorkspaceContainerFunctions';



// model
import FlowConnector from '../model/FlowConnector';
import ReduxWorkspace from '../redux-components/ReduxWorkspace'

var client = require('../functions/client');

const mapStateToProps = state => {
  // console.log("mapStateToProps");
  // console.log(state.aFlux);

  return {
    sideBarWidth:state.aFlux.sideBarWidth,
    elementBaseProperties:state.aFlux.elementBaseProperties,
    settings:state.aFlux.settings,
    host:state.aFlux.host,
    footerYPosition:state.aFlux.footerYPosition,
    selectedToolId:state.aFlux.selectedToolId,
    jobName:state.aFlux.jobName,
    toolSet:state.aFlux.toolSet,
    selectedNodeElement:state.aFlux.selectedNodeElement,
    runningProcesses:state.aFlux.runningProcesses,
    currentActivity:state.aFlux.currentActivity,
    infoProperties:state.aFlux.infoProperties,
    isMovingNode:state.aFlux.isMovingNode,
    isCreatingNode:state.aFlux.isCreatingNode,
    isCreatingLink:state.aFlux.isCreatingLink,
    flowElements:state.aFlux.flowElements,
    flowConnectors:state.aFlux.flowConnectors,
    selectedNodeId:state.aFlux.selectedNodeId,
    selectedConnectorId:state.aFlux.selectedConnectorId,
    selectedSourceId:state.aFlux.selectedSourceId,
    selectedSourceIndex:state.aFlux.selectedSourceIndex,
    connectorPositions:state.aFlux.connectorPositions,
    activities:state.aFlux.activities,
    currentActivityIndex:state.aFlux.currentActivityIndex,
    stdOutput:state.aFlux.stdOutput,
    newNode:state.aFlux.newNode,
    jobSelectorOpened:state.aFlux.jobSelectorOpened,
    jobList:state.aFlux.jobList,
    newActivityDialogOpened:state.aFlux.newActivityDialogOpened,
    pluginList:state.aFlux.pluginList,
    removeActivityDialogOpened:state.aFlux.removeActivityDialogOpened,
    pluginUploaderOpened:state.aFlux.pluginUploaderOpened,
    pluginManagerPopupOpened:state.aFlux.pluginManagerPopupOpened,
    isDraggingFooter:state.aFlux.isDraggingFooter
  }
}



const mapDispatchToProps = (dispatch, ownProps) => {
  // console.log("mapDispatchToProps");
  // console.log(ownProps);

  console.log("ReduxWorkspaceContainer:mapDispatchToProps");
  console.log(ownProps);
  return {
  	openNewActivityDialog:() => { openNewActivityDialog(); },
  	closeNewActivityDialog:() => { closeNewActivityDialog();},
  	prepareOpenPluginManager:() => {prepareOpenPluginManager(ownProps.host);},
  	closePluginManager:() => {closePluginManager();},
    closePluginUploaderDialog:() => {closePluginUploaderDialog();	},


  	openPluginUploaderDialog:() => {
  		dispatch(applyGeneralActionAction({
  			pluginUploaderOpened:true,
  		},"openPluginUploaderDialog"));
  	},


  	uploadPlugin:(fileName,file) => {

  		let data = new FormData();
  		data.append('file', file);
  		data.append('name', fileName);

  		client({
  			method: 'POST',
  			path: ownProps.host+'/plugin/upload',
  			entity: data,
  			headers: {'Content-Type': 'multipart/form-data'}
  		}).then(response => redirectToShowPluginManagerPopup(ownProps.host));
  	},
  	activatePlugin:(id) => {
  		client({method: 'POST',
  			mode:'no-cors',
  			path: ownProps.host+'/plugin/activate/'+id}).done(
          response => {refreshTools(ownProps.host)});
  		closePluginManager();
  	},
  	deactivatePlugin:(id) => {
  		client({method: 'POST',
  			mode:'no-cors',
  			path: ownProps.host+'/plugin/deactivate/'+id}).done(
          response => {refreshTools(ownProps.host)});
  		closePluginManager();
  	},
  	removePlugin:(id) => {
  		client({method: 'POST',
  			mode:'no-cors',
  			path: ownProps.host+'/plugin/remove/'+id}).done(
          response => {refreshTools(ownProps.host)});
  		closePluginManager();
  	},
  	createNewActivity:(name) => {
      createNewActivity(name,
        ownProps.activities,
        ownProps.currentActivityIndex,
        ownProps.flowElements,
        ownProps.flowConnectors,
        ownProps.activityBaseProperties);
  	},
  	openRemoveActivityDialog:() => {
      openRemoveActivityDialog(ownProps.activities);
  	},
  	closeRemoveActivityDialog:() => {
      closeRemoveActivityDialog();
  	},
  	removeActivity:() => {
      removeActivity(ownProps.currentActivityIndex,ownProps.activities);
  	},
  	removeJob:(jobId,jobName) => {
  		if (ownProps.currentJob.id===jobId) {
  			newJob(ownProps.activityBaseProperties);
  		}
  		client({method: 'POST',
  			mode:'no-cors',
  			path: ownProps.host+'/job/remove/'+jobId}).done(
          response => {alert(jobName+" removed")});
  		dispatch(applyGeneralActionAction({
  			jobSelectorOpened:false
  		},"removeJob"));
  	},
  	prepareOpenJobSelector:() => {
  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/jobs'}).done(
          response => {openJobSelector(response.entity)});
  	},
  	closeJobSelector:() => {
      closeJobSelector();
  	},
  	openJob:(flowJobId) => {
  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/jobs/get/'+flowJobId}).done(
          response => {setFlowJob(response.entity,ownProps.settings.nodeElement)});
  		closeJobSelector();

  	},
  	saveJob:() => {
      console.log("ReduxWorkspaceContainer:saveJob()");
  		var activities=
  				refreshActivities(
  					ownProps.activities,
  					ownProps.currentActivityIndex,
  					ownProps.flowElements,
  					ownProps.flowConnectors);
      console.log("activities");
      console.log(activities);
      var currentJob=updateActivitiesOnJob(ownProps.currentJob,activities);
      console.log("currentJob");
      console.log(currentJob);
  		dispatch(applyGeneralActionAction({
  			activities:currentJob.activities,
  			currentJob:currentJob
  		},"saveJob"));
  		client({
  			method: 'POST',
  			path: ownProps.host+'/jobs/save',
  			entity: currentJob,
  			headers: {'Content-Type': 'application/json'}
  		}).then(response => finishSaveJob(response.entity,ownProps.host));
  	},
  	saveAs:(name) => {
  		var activities=
  				refreshActivities(
  					ownProps.activities,
  					ownProps.currentActivityIndex,
  					ownProps.flowElements,
  					ownProps.flowConnectors);
  		var currentJob=ownProps.currentJob;
  		currentJob.name=name;
  		currentJob.activities=activities;
  		client({
  			method: 'POST',
  			path: ownProps.host+'/jobs/saveAs',
  			entity: currentJob,
  			headers: {'Content-Type': 'application/json'}
  		}).then(response => finishSaveAsJob(response.entity,ownProps.host));

  	},
  	renameJob:(name) => {

  		var activities=
  				refreshActivities(
  					ownProps.activities,
  					ownProps.currentActivityIndex,
  					ownProps.flowElements,
  					ownProps.flowConnectors);
  		var currentJob=ownProps.currentJob;
  		currentJob.name=name;
  		currentJob.activities=activities;

  		dispatch(applyGeneralActionAction({
  			jobName:name,
  			currentJob:currentJob,
  			activities:activities
  		},"renameJob"));

  	},
  	newJob:() => {
      newJob(ownProps.activityBaseProperties);
  	},
  	selectActivity:(activityIndex) => {

      selectActivity(
          activityIndex,
          ownProps.currentActivityIndex,
          ownProps.activities,
          ownProps.flowElements,
          ownProps.flowConnectors,
          ownProps.mouseDeltaX,
          ownProps.mouseDeltaY,
          ownProps.settings);
  	},

  	// methods called by toolbar
  	deleteElement:() => {
  		if (!ownProps.isMovingNode && !ownProps.isCreatingNode && !ownProps.isCreatingLink) {
  			if (ownProps.selectedNodeId!==-1) {
  				deleteFlowElement(
            ownProps.selectedNodeId,
            ownProps.flowElements,
            ownProps.flowConnectors,
            ownProps.mouseDeltaX,
            ownProps.mouseDeltaY,
            ownProps.settings,
            ownProps.currentActivity.name);
  			}
  			if (ownProps.selectedConnectorId!==-1) {
  				deleteConnector(
              ownProps.selectedConnectorId,
              ownProps.flowElements,
              ownProps.flowConnectors,
              ownProps.mouseDeltaX,
              ownProps.mouseDeltaY,
              ownProps.settings);
  			}
  		}
  	},



  	updateConnectors:(id) => {
  		var connectors = ownProps.flowConnectors;
  		// clone the array
  		var newPositions = ownProps.connectorPositions.slice(0);
  		newPositions=generateConnectorPositions(
  						ownProps.flowConnectors,
  						ownProps.flowElements,
  						0,
  						0,
  						ownProps.settings.nodeElement,
  						"left-right");
  		dispatch(applyGeneralActionAction({
  			connectorPositions:newPositions
  		},"updateConnectors"));
  	},


    refreshOutput:() => {
      refreshOutput(ownProps.host,ownProps.stdOutput);
    },

  	stopJob:() => {

      client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/stopAll/'+ownProps.jobName}).done(
          response => {showRunningProcesses(response.entity,ownProps.host)});

      this.refreshOutput();

  	},

  	stopActivity:() => {

  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/stop/'+ownProps.jobName+"/"+ownProps.activities[ownProps.currentActivityIndex].id}).done(
          response => {showRunningProcesses(response.entity,ownProps.host)});


      this.refreshOutput();

  	},


  	runJob:() => {

  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/runAll/'+ownProps.jobName}).done(
          response => {showRunningProcesses(response.entity,ownProps.host)});

      this.refreshOutput();

  	},

  	runActivity:() => {
  		// by now get output only one time

  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/run/'+ownProps.jobName+"/"+ownProps.activities[ownProps.currentActivityIndex].id}).done(
          response => {showRunningProcesses(response.entity,ownProps.host)});

  		/*
  		client({method: 'GET',
  			mode:'no-cors',
  			path: ownProps.host+'/showOutput'}).
  				done(response => {this.addToStandardOutputCycle(response.entity)});
  				*/
  		//refresh(ownProps.host);
  		/*
  		var outputSection=document.getElementById("stdoutput");
  		outputSection.innerHTML=outputSection.innerHTML+"This is another line"+Math.random()+"\n";
  		gotoBottom("stdoutput");
  		*/
  	},

  	clearOutput:() => {
  		dispatch(applyGeneralActionAction({
  			stdOutput:[]
  		},"clearOutput"));
  	},



  	selectSourceConnector:(event,flowElementId,sourceIndex) => {
  		var p=getMousePosition(event,getCanvasContainerElement());
  		var flowElement=findElement(flowElementId,ownProps.flowElements);
  		var connectorSourcePosition=
  			getConnectorSourcePointPosition(
  				flowElement,
  				sourceIndex,
  				"left-right",
  				flowElement.asyncInterface,
  				ownProps.settings.nodeElement,
  				flowElement.outputInterfaces);

  		var newLinkConnector=generateNewLinkConnector(flowElementId,sourceIndex,ownProps.flowElements,ownProps.flowConnectors);
  		dispatch(applyGeneralActionAction({
  			selectedSourceId:flowElementId,
  			selectedSourceIndex:sourceIndex,
  			newLinkConnector:newLinkConnector,
  			newConnectorId:ownProps.flowConnectors.length,
  			mouseDeltaX:p.x-connectorSourcePosition.x,
  			mouseDeltaY:p.y-connectorSourcePosition.y,
  			newConnectorPosition:generateConnectorPosition(
  				newLinkConnector,
  				ownProps.flowElements,
  				p,
  				ownProps.mouseDeltaX,
  				ownProps.mouseDeltaY,
  				ownProps.settings.nodeElement,
  				"left-right"),
  			isCreatingLink:true
  		},"selectSourceConnector"));
  	},

  	createLink:(targetElementId,targetIndexId) => {
  		var targetElement=findElement(targetElementId,ownProps.flowElements);
  		var selectedSourceElement=findElement(ownProps.selectedSourceId,ownProps.flowElements);
  		const flowConnector =
  			new FlowConnector (
  				getNewConnectorId(ownProps.flowConnectors),
  				selectedSourceElement,
  				ownProps.selectedSourceIndex,
  				targetElement,
  				targetIndexId);
  		cancelLinkCreation(null);
  		var connectorList=ownProps.flowConnectors.slice(0);
  		connectorList.push(flowConnector);
  		const connectorPositions=
  			generateConnectorPositions(
  				connectorList,
  				ownProps.flowElements,
  				ownProps.mouseDeltaX,
  				ownProps.mouseDeltaY,
  				ownProps.settings.nodeElement,
  				"left-right");
  		dispatch(applyGeneralActionAction({
  			flowConnectors:connectorList,
  			connectorPositions:connectorPositions
  		},"createLink"
    ));
      console.log("sending flow for validation");
      var currentJob=updateActivitiesOnJob(ownProps.currentJob,refreshActivities(
            ownProps.activities,
            ownProps.currentActivityIndex,
            ownProps.flowElements,
            connectorList));
      client({
        method: 'POST',
        path: ownProps.host+'/jobs/validate',
        entity: currentJob,
        headers: {'Content-Type': 'application/json'}
      }).then(response => finishValidateJob(response.entity,ownProps.host, ownProps.settings.nodeElement));
  	},
    
    validateJob: () => {
      console.log("validateJob");
      console.log(ownProps.flowElements);
      console.log(ownProps.flowConnectors);
      var activities=
          refreshActivities(
            ownProps.activities,
            ownProps.currentActivityIndex,
            ownProps.flowElements,
            ownProps.flowConnectors);
      console.log(activities);
      var currentJob=updateActivitiesOnJob(ownProps.currentJob,activities);
      dispatch(applyGeneralActionAction({
        activities:currentJob.activities,
        currentJob:currentJob
      },"validateJob"));
      console.log(currentJob);
      client({
        method: 'POST',
        path: ownProps.host+'/jobs/validate',
        entity: currentJob,
        headers: {'Content-Type': 'application/json'}
      }).then(response => finishValidateJob(response.entity,ownProps.host, ownProps.settings.nodeElement));
    },

  	cancelLinkCreation:(event) => {
      cancelLinkCreation();
  	},

  	gotoOutputConnector:(event) => {
      // console.log("gotoOutputConnector");
  		var p=getMousePosition(event,getCanvasContainerElement());
      // console.log(p);
  		var newConnectorPosition=
  			generateConnectorPosition(
  				ownProps.newLinkConnector,
  				ownProps.flowElements,
  				p,
  				ownProps.mouseDeltaX,
  				ownProps.mouseDeltaY,
  				ownProps.settings.nodeElement,
  				"left-right");
      // console.log("gotoOutputConnector:newConnectorPosition");
      // console.log(newConnectorPosition);
  		dispatch(applyGeneralActionAction({
  			newConnectorPosition:newConnectorPosition
  		},"gotoOutputConnector"));



  	},


  	createNode:(event,typeId,position,flowElements,toolSet,settings,elementBaseProperties,activities) => {
        createNode(event,typeId,position,flowElements,toolSet,settings,elementBaseProperties,activities);
  	},
  	beginDragFooter:(event) => {
  		dispatch({type:BEGIN_DRAG_FOOTER})
  	},
  	finishDragFooter:(event) => {
      dispatch({type:FINISH_DRAG_FOOTER})
  	},
    dragFooter: (event) => {
      if (ownProps.isDraggingFooter) {
        var p=getMousePosition(event,getWorkspaceContainerElement());
        //398
        // 638
        // outerHeight 728
        var newYPosition=p.y+463-window.outerHeight;
        dispatch(changeFooterYPositionAction(newYPosition));
      }
    },
  	selectNode:(event,id) => {
      selectNode(event,id,ownProps.flowElements);
  	},
    deselectNode: (event) => {deselectNode()},
  	selectConnector:(event,id) => {
  		deselectNode();
  		dispatch(applyGeneralActionAction({
  			selectedConnectorId:id,
  		},"selectConnector"));
  	},
    deselectLink: (event) => {
      dispatch({type:DESELECT_LINK});
  	},

  	finishNodeMoving:(event) => {
      finishNodeMoving();
  	},

  	finishNodeCreation:() => {
      finishNodeCreation();
  	},


  	convertCoords:(svgDoc,elem,x,y) => {
  		var offset = svgDoc.getBoundingClientRect();
  		var matrix = elem.getScreenCTM();
  		return {
  			x: (matrix.a * x) + (matrix.c * y) + matrix.e - offset.left,
  			y: (matrix.b * x) + (matrix.d * y) + matrix.f - offset.top
  		};
  	},

  	deselectTool:() => {
      deselectTool(ownProps.selectedToolId,
          ownProps.newNode,
          ownProps.flowElements,
          ownProps.toolSet,
          ownProps.settings,
          ownProps.elementBaseProperties,
          ownProps.currentActivity.id,
          ownProps.activities);
  	},
  	moveTool:
      (event,selectedToolId,mouseDeltaX,mouseDeltaY,flowElements,toolSet,settings,elementBaseProperties,activities) => {
        moveTool(event,selectedToolId,mouseDeltaX,mouseDeltaY,flowElements,toolSet,settings,elementBaseProperties,activities);
  	},

  	moveNodeElement:(event,flowElements,selectedNodeId,mouseDeltaX,mouseDeltaY) =>{
        moveNodeElement(event,flowElements,selectedNodeId,mouseDeltaX,mouseDeltaY);
  	},
  	showProcesses:() => {
  		// deselect activity
  		// deselect nodeElement
  		deselectNode();
      dispatch(setInfoPropertiesAction("RUNNING PROCESSES"));
  	},
    showLastChart:() => {
      var chartUrl=ownProps.host+"showLastChart/"+ownProps.jobName+"/"+ownProps.activities[ownProps.currentActivityIndex].id;
      window.open(chartUrl);
  	},
  	showActivityProperties:() => {
      showActivityProperties();
  	},
    onKeyPressEvent:(event) => {
      onKeyPressEvent(event);
    }

  }
}

const ReduxWorkspaceContainer  = connect(
  mapStateToProps,
  mapDispatchToProps
)(ReduxWorkspace)

export default ReduxWorkspaceContainer
