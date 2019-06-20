// DEPRECTAED - MIGRATED TO REDUX
// Moved to ReduxWorkspace ....



import React, { Component } from 'react';

//components
import HeaderContainer from './HeaderContainer';
import SideBarContainer from './SideBarContainer';
import PropertiesContainer from './PropertiesContainer';
import CanvasContainer from './CanvasContainer';
import ToolBarContainer from './ToolBarContainer';
import FooterContainer from './FooterContainer';
import AddToolButtonContainer from './AddToolButtonContainer';
import FlowTabsContainer from './FlowTabsContainer';


// screens
import JobSelector from '../screens/JobSelector';
import AreYouSureDialog from '../screens/AreYouSureDialog';
import ChangeNameDialog from '../screens/ChangeNameDialog';
import UploadPluginDialog from '../screens/UploadPluginDialog';
import PluginManagerPopup from '../screens/PluginManagerPopup';


// model
import FlowJob from '../model/FlowJob';
import FlowActivity from '../model/FlowActivity';
import FlowElement from '../model/FlowElement';
import FlowConnector from '../model/FlowConnector';
import NewNodeElement from '../components/NewNodeElement';

// functions
import { getMousePosition } from '../functions/WorkspaceManager';
import { getAbsolutePosition } from '../functions/WorkspaceManager';
import { findElement } from '../redux-containers/ElementContainerFunctions';
import { findTool } from '../redux-containers/ToolContainerFunctions';
import { findActivityByName } from '../redux-containers/ActivityContainerFunctions';
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';
import { generateConnectorPositions } from '../redux-containers/ConnectorContainerFunctions';

import { generateConnectorPosition } from '../redux-containers/ConnectorContainerFunctions';
import { getConnectorSourcePointPosition } from '../redux-containers/ConnectorContainerFunctions';
import { gotoBottom } from '../functions/WorkspaceManager';
import { getCanvasContainerElement } from '../functions/WorkspaceManager';
import { getWorkspaceContainerElement } from '../functions/WorkspaceManager';
import { getSideBarContainerElement } from '../functions/WorkspaceManager';
import { getSideBarScrollPosition } from '../functions/WorkspaceManager';
import { refreshActivities } from '../redux-containers/ActivityContainerFunctions';
import { isInBottom } from '../functions/WorkspaceManager';



var client = require('../functions/client');

import '../AsyncFlowsApp.css';


class Workspace extends Component {


	constructor(props) {
		super(props);
		var currentFlowJob=new FlowJob(-1,"_NewJob",[new FlowActivity(1,"activity-1",1,[],[],[])]);
		this.state= {
			flowElements:[],
			flowConnectors:[],
			connectorPositions:
				generateConnectorPositions([],[],
					0,0,
				this.props.settings.nodeElement,
				"left-right"),
			isMovingNode:false,
			selectedNodeId:-1,
			selectedNodeElement:null,
			isCreatingNode:false,
			selectedToolId:null,
			isCreatingLink:false,
			selectedSourceId:-1,
			selectedSourceIndex:-1,
			endLinkPosition:null,
			selectedConnectorId:-1,
			mouseDeltaX:0,
			mouseDeltaY:0,
			currentJob:currentFlowJob,
			jobName:currentFlowJob.name,
			activities:currentFlowJob.activities,
			currentActivityIndex:0,
			currentActivity:currentFlowJob.activities[0],
			currentPropertyValues:[],
			stdOutput:[],
			runningProcesses:[],
			newActivityDialogOpened:false,
			jobSelectorOpened:false,
			pluginManagerPopupOpened:false,
			pluginUploaderOpened:false,
			removeActivityDialogOpened:false,
			toolSet:[],
			isDraggingFooter:false,
			footerYPosition:254,
			infoProperties:"RUNNING PROCESSES"
		}
	}
	componentDidMount() {
			this.setInitialActivityProperties();
			this.refreshTools();
  		this.refresh();

	}

	componentWillUnmount() {
  	clearTimeout(this.state.lastTimeout);
	}

	setInitialActivityProperties() {
		client({method: 'GET',
						mode:'no-cors',
						path: this.props.host+'/activityBaseProperties'}).
						done(response => {this.setActivityBaseProperties(response.entity)});
	}
	setActivityBaseProperties(basePropertiesArray) {
		// store.dispatch(setActivityBasePropertiesAction(basePropertiesArray));
		// return;

		// clone currentActivity
		var ca=this.state.currentActivity;
		var currentActivity=new FlowActivity(
				ca.id,ca.name,ca.index,ca.elements,ca.connectors,ca.properties);
		// var currentActivity = Object.assign({}, this.state.currentActivity);
		currentActivity.properties=currentActivity.generateProperties(basePropertiesArray);
		var activities=this.state.activities;
		activities[this.state.currentActivityIndex]=currentActivity;
		this.setState({
			activityBaseProperties:basePropertiesArray,
			currentActivity:currentActivity,
			activities:activities
		});
	}
	finishSaveJob(job) {
		if (job=="") { // it couldnt be saved
			alert("error saving job "+job);
		} else {
			this.refreshTools();
			this.updateCurrentJob(job);
		}
	}
	finishSaveAsJob(job) {
		if (job=="") { // it couldnt be saved
			alert("error saving job "+job);
		} else {
			this.refreshTools();
			this.updateCurrentJob(job);
		}
	}
	updateCurrentJob(job) {
		this.setState({
			currentJob:job,
			jobName:job.name,
			activities:job.activities
		});
	}

	openNewActivityDialog() {
		this.setState({
			newActivityDialogOpened:true,
		});
	}

	closeNewActivityDialog() {
		this.setState({
			newActivityDialogOpened:false
		});
	}
	redirectToShowPluginManagerPopup() {
		this.closePluginUploaderDialog();
		this.prepareOpenPluginManager();
	}
	prepareOpenPluginManager() {
			client({method: 'GET',
				mode:'no-cors',
				path: this.props.host+'/plugins'}).
					done(response => {this.openPluginManager(response.entity)});
	}

	openPluginManager(pluginList) {
		var newPluginList=[];
		for (var i=0;i<pluginList.length;i++) {
			newPluginList.push(pluginList[i]);
		}
		this.setState({
			pluginList:newPluginList,
			pluginManagerPopupOpened:true
		})
	}

	closePluginManager() {
		this.setState({
			pluginManagerPopupOpened:false
		});
	}

	openPluginUploaderDialog() {
		this.setState({
			pluginUploaderOpened:true,
		});
	}

	closePluginUploaderDialog() {
		this.setState({
			pluginUploaderOpened:false,
		});
	}

	uploadPlugin(fileName,file) {

		let data = new FormData();
		data.append('file', file);
		data.append('name', fileName);

		client({
			method: 'POST',
			path: this.props.host+'/plugin/upload',
			entity: data,
			headers: {'Content-Type': 'multipart/form-data'}
		}).then(response => this.redirectToShowPluginManagerPopup());
	}
	activatePlugin(id) {
		client({method: 'POST',
			mode:'no-cors',
			path: this.props.host+'/plugin/activate/'+id}).
				done(response => {this.refreshTools()});
		this.closePluginManager();
	}
	deactivatePlugin(id) {
		client({method: 'POST',
			mode:'no-cors',
			path: this.props.host+'/plugin/deactivate/'+id}).
				done(response => {this.refreshTools()});
		this.closePluginManager();
	}
	removePlugin(id) {
		client({method: 'POST',
			mode:'no-cors',
			path: this.props.host+'/plugin/remove/'+id}).
				done(response => {this.refreshTools()});
		this.closePluginManager();
	}
	refreshTools() {
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/elementTypes'}).
				done(response => {this.finishToolSet(response.entity)});

	}
	finishToolSet(toolSetArray) {
		this.setState({
			toolSet:toolSetArray
		});
	}

	createNewActivity(name) {
		// search by name in current job
		var updatedActivities=
				refreshActivities(
					this.state.activities,
					this.state.currentActivityIndex,
					this.state.flowElements,
					this.state.flowConnectors);
		var currentActivityIndex=this.state.currentActivityIndex;
		if (name=="") {
			alert("Error: Empty name");
		} else {
			var sameActivity=findActivityByName(name,this.state.activities);
			if (sameActivity!=null) {
				alert("Error: Activity already exists:"+name);
			} else {
				var idActivity = this.getNewActivityId();
				var newActivity=new FlowActivity(idActivity,name,idActivity,[],[],this.state.activityBaseProperties);
				updatedActivities.push(newActivity);
				currentActivityIndex=updatedActivities.length-1;
				this.setState({
					currentActivityIndex:currentActivityIndex,
					activities:updatedActivities,
					currentActivity:updatedActivities[currentActivityIndex],
					flowConnectors:[],
					flowElements:[]}
				);
			}
		}
 		// check if ther is no activity with the same name
		this.setState({
			newActivityDialogOpened:false
		});
	}
	openRemoveActivityDialog() {
		if (this.state.activities.length>1) {
			this.currentActivityName=this.state.activities[this.state.currentActivityIndex].name;
			this.setState({
				removeActivityDialogOpened:true,
			});
		} else {
			alert("Cannot remove the unique activity");
		}
	}
	closeRemoveActivityDialog() {
		this.setState({
			removeActivityDialogOpened:false
		});

	}
	removeActivity() {
		var updatedActivities=[];
		var currentActivityIndex=this.state.currentActivityIndex;
		for (var i=0;i<this.state.activities.length;i++) {
			if (i!=this.state.currentActivityIndex) {
				updatedActivities.push(this.state.activities[i]);
			}
		}
		if (this.state.currentActivityIndex==updatedActivities.length) {
			currentActivityIndex--;
		}
		// check if ther is no activity with the same name
		this.setState({
			removeActivityDialogOpened:false,
			activities:updatedActivities,
			currentActivityIndex:currentActivityIndex,
			flowElements:updatedActivities[currentActivityIndex].elements,
			flowConnectors:updatedActivities[currentActivityIndex].connectors,
			currentActivity:updatedActivities[currentActivityIndex]
		});
	}
	removeJob(jobId,jobName) {
		if (this.state.currentJob.id==jobId) {
			this.newJob();
		}
		client({method: 'POST',
			mode:'no-cors',
			path: this.props.host+'/job/remove/'+jobId}).
				done(response => {alert(jobName+" removed")});
		this.setState({
			jobSelectorOpened:false
		});
	}
	prepareOpenJobSelector() {
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/jobs'}).
				done(response => {this.openJobSelector(response.entity)});
	}
	openJobSelector(jobList) {
		var newJobList=[];
		for (var i=0;i<jobList.length;i++) {
			newJobList.push(jobList[i]);
		}
		this.setState({
			jobList:newJobList,
			jobSelectorOpened:true
		})
	}
	closeJobSelector() {
		this.setState({
			jobSelectorOpened:false
		})
	}

	refresh() {
			this.refreshOutput();
			// this.refreshProcesses();
			if (this.state.runningProcesses.length>0) {
				this.setState({
						lastTimeout:setTimeout(()=>this.refresh(),1200)
						}
				);
			}
	}
	openJob(flowJobId) {
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/jobs/get/'+flowJobId}).
				done(response => {this.setFlowJob(response.entity)});
		this.closeJobSelector();

	}
	saveJob() {
		var activities=
				refreshActivities(
					this.state.activities,
					this.state.currentActivityIndex,
					this.state.flowElements,
					this.state.flowConnectors);
		var currentJob=this.state.currentJob;
		currentJob.activities=activities;
		this.setState({
			activities:activities,
			currentJob:currentJob
		});
		client({
			method: 'POST',
			path: this.props.host+'/jobs/save',
			entity: currentJob,
			headers: {'Content-Type': 'application/json'}
		}).then(response => this.finishSaveJob(response.entity));
	}
	saveAs(name) {
		var activities=
				refreshActivities(
					this.state.activities,
					this.state.currentActivityIndex,
					this.state.flowElements,
					this.state.flowConnectors);
		var currentJob=this.state.currentJob;
		currentJob.name=name;
		currentJob.activities=activities;
		client({
			method: 'POST',
			path: this.props.host+'/jobs/saveAs',
			entity: currentJob,
			headers: {'Content-Type': 'application/json'}
		}).then(response => this.finishSaveAsJob(response.entity));

	}
	renameJob(name) {

		var activities=
				refreshActivities(
					this.state.activities,
					this.state.currentActivityIndex,
					this.state.flowElements,
					this.state.flowConnectors);
		var currentJob=this.state.currentJob;
		currentJob.name=name;
		currentJob.activities=activities;

		this.setState({
			jobName:name,
			currentJob:currentJob,
			activities:activities
		});

	}
	newJob() {
		var activityName="activity-1";
		var newActivity=new FlowActivity(1,activityName,1,[],[],this.state.activityBaseProperties);
		var activities=[newActivity];
		var newJob=new FlowJob(null,"_NewJob",activities);
		var jobName=newJob.name;
		this.setState({
			currentJob:newJob,
			jobName:jobName,
			activities:activities,
			currentActivityIndex:0,
			flowElements:[],
			flowConnectors:[],
			currentActivity:activities[0]
		}
		)
	}
	setFlowJob(job) {
		this.setFlowActivities(job.activities);
		this.setState({
			currentJob:job,
			jobName:job.name
		})
	}
	setFlowActivities(activities) {
		var currentActivityIndex=0;
		if (activities.length!=0) {
			var flowConnectors=activities[0].connectors;
			var flowElements=activities[0].elements;
			var connectorPositions=generateConnectorPositions(
							activities[0].connectors,
							activities[0].elements,
							0,
							0,
							this.props.settings.nodeElement,
							"left-right");
		} else {
			flowConnectors=[];
			flowElements=[];
			connectorPositions=[];
		}
		this.setState({
			activities:activities,
			currentActivityIndex:currentActivityIndex,
			flowElements:flowElements,
			flowConnectors:flowConnectors,
			connectorPositions:connectorPositions,
			currentActivity:activities[currentActivityIndex]
		});
	}
	selectActivity(activityIndex) {
		if (activityIndex!=this.state.currentActivityIndex) {
			// get data for previous activity
			var activities=
				refreshActivities(
					this.state.activities,
					this.state.currentActivityIndex,
					this.state.flowElements,
					this.state.flowConnectors);
			var connectorPositions=
				generateConnectorPositions(
					activities[activityIndex].connectors,
					activities[activityIndex].elements,
					this.state.mouseDeltaX,
					this.state.mouseDeltaY,
					this.props.settings.nodeElement,
					"left-right");
			this.deselectNode();
			this.setState({
				activities:activities,
				currentActivityIndex:activityIndex,
				currentActivity:activities[activityIndex],
				flowElements:this.state.activities[activityIndex].elements,
				flowConnectors:this.state.activities[activityIndex].connectors,
				connectorPositions:connectorPositions,
				selectedConnectorId:-1,
				selectedSourceId:-1
			});
		}
	}

	deleteFlowElement(elementId) {
		var flowElements=[];
		for (var i=0;i<this.state.flowElements.length;i++) {
			var flowElement=this.state.flowElements[i];
			if (elementId!=flowElement.id) {
				flowElements.push(flowElement);
			}
		}
		var flowConnectors=[];
		for (var i=0;i<this.state.flowConnectors.length;i++) {
			var flowConnector=this.state.flowConnectors[i];
			if (flowConnector.sourceId!=elementId && flowConnector.targetId!=elementId) {
				flowConnectors.push(flowConnector);
			}
		}
		var connectorPositions=
				generateConnectorPositions(
					flowConnectors,
					flowElements,
					this.state.mouseDeltaX,
					this.state.mouseDeltaY,
					this.props.settings.nodeElement,
					"left-right");
		this.deselectNode();
		this.setState({
			flowElements:flowElements,
			flowConnectors:flowConnectors,
			connectorPositions:connectorPositions
		});
	}
	// methods called by toolbar
	deleteElement() {
		if (!this.state.isMovingNode && !this.state.isCreatingNode && !this.state.isCreatingLink) {
			if (this.state.selectedNodeId!=-1) {
				this.deleteFlowElement(this.state.selectedNodeId);
			}
			if (this.state.selectedConnectorId!=-1) {
				this.deleteConnector(this.state.selectedConnectorId);
			}
		}
	}


	deleteConnector(connectorId) {
		var flowConnectors=[];
		for (var i=0;i<this.state.flowConnectors.length;i++) {
			var flowConnector=this.state.flowConnectors[i];
			if (connectorId!=flowConnector.id) {
				flowConnectors.push(flowConnector);
			}
		}
		var connectorPositions=
				generateConnectorPositions(
					flowConnectors,
					this.state.flowElements,
					this.state.mouseDeltaX,
					this.state.mouseDeltaY,
					this.props.settings.nodeElement,
					"left-right");
		this.setState({
			flowConnectors:flowConnectors,
			connectorPositions:connectorPositions,
			selectedConnectorId:-1,
		});
	}


	updateConnectors(id) {
		var connectors = this.state.flowConnectors;
		// clone the array
		var newPositions = this.state.connectorPositions.slice(0);
		var newPositions=generateConnectorPositions(
						this.state.flowConnectors,
						this.state.flowElements,
						0,
						0,
						this.props.settings.nodeElement,
						"left-right");
		this.setState({
			connectorPositions:newPositions
		});
	};


	refreshOutput() {
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/showOutput'}).
				done(response => {this.addToStandardOutput(response.entity)});

		client({method: 'GET',
				mode:'no-cors',
				path: this.props.host+'/running'}).
						done(response => {this.showRunningProcesses(response.entity)});
	}

	showRunningProcesses(processes) {
			this.setState({
				runningProcesses:processes
			});
	}

	stopJob() {

		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/stopAll/'+this.state.jobName}).
				done(response => {this.showRunningProcesses(response.entity)});


		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/showOutput'}).
				done(response => {this.addToStandardOutput(response.entity)});

	}

	stopActivity() {

		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/stop/'+this.state.jobName+"/"+this.state.activities[this.state.currentActivityIndex].name}).
				done(response => {this.showRunningProcesses(response.entity)});


		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/showOutput'}).
				done(response => {this.addToStandardOutput(response.entity)});

	}


	runJob() {

		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/runAll/'+this.state.jobName}).
				done(response => {this.showRunningProcesses(response.entity)});


		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/showOutput'}).
				done(response => {this.addToStandardOutputCycle(response.entity)});

	}

	runActivity() {
		// by now get output only one time

		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/run/'+this.state.jobName+"/"+this.state.activities[this.state.currentActivityIndex].id}).
				done(response => {this.showRunningProcesses(response.entity)});

		/*
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/showOutput'}).
				done(response => {this.addToStandardOutputCycle(response.entity)});
				*/
		this.refresh();
		/*
		var outputSection=document.getElementById("stdoutput");
		outputSection.innerHTML=outputSection.innerHTML+"This is another line"+Math.random()+"\n";
		gotoBottom("stdoutput");
		*/
	}

	checkRunFinished(runningProcesses) {
		this.showRunningProcesses(runningProcesses);
		if (runningProcesses.length=0) {
				this.setState();
		}
	}

	addToStandardOutputCycle(stringList) {
		this.addToStandardOutput(stringList);
		client({method: 'GET',
			mode:'no-cors',
			path: this.props.host+'/running'}).
				done(response => {this.checkRunFinished(response.entity)});
	}


	addToStandardOutput(stringList) {
		var initialSituationAtBottom=isInBottom("stdoutput");
		var newArray=this.state.stdOutput.concat(stringList);
		this.setState({
			stdOutput:newArray
		});
		/*
		if (initialSituationAtBottom) {
				gotoBottom("stdoutput");
		}
		*/
	}

	clearOutput() {
		this.setState({
			stdOutput:[]
		});
	}



	getNewConnectorId() {
		var connectors=this.state.flowConnectors;
		var maxId=0;
		for (var i=0;i<connectors.length;i++) {
			if (maxId<connectors[i].id) {
				maxId=connectors[i].id;
			}
		}
		return maxId+1;
	}

	getNewElementId() {
		var elements=this.state.flowElements;
		var maxId=0;
		for (var i=0;i<elements.length;i++) {
			if (maxId<elements[i].id) {
				maxId=elements[i].id;
			}
		}
		return maxId+1;
	}

	getNewActivityId() {
		var elements=this.state.activities;
		var maxId=0;
		for (var i=0;i<elements.length;i++) {
			if (maxId<elements[i].id) {
				maxId=elements[i].id;
			}
		}
		return maxId+1;
	}

	generateNewLinkConnector(flowElementId,sourceIndex) {
		var flowElement=findElement(flowElementId,this.state.flowElements)
		const flowConnector =
			new FlowConnector (
				this.getNewConnectorId(),
				flowElement,
				sourceIndex,
				null,
				-1)
		return flowConnector;
	}

	selectSourceConnector(event,flowElementId,sourceIndex) {
		var p=getMousePosition(event,getCanvasContainerElement());
		var flowElement=findElement(flowElementId,this.state.flowElements);
		var connectorSourcePosition=
			getConnectorSourcePointPosition(
				flowElement,
				sourceIndex,
				"left-right",
				flowElement.asyncInterface,
				this.props.settings.nodeElement,
				flowElement.outputInterfaces);

		var newLinkConnector=this.generateNewLinkConnector(flowElementId,sourceIndex);
		this.setState({
			selectedSourceId:flowElementId,
			selectedSourceIndex:sourceIndex,
			newLinkConnector:newLinkConnector,
			newConnectorId:this.state.flowConnectors.length,
			mouseDeltaX:p.x-connectorSourcePosition.x,
			mouseDeltaY:p.y-connectorSourcePosition.y,
			newConnectorPosition:generateConnectorPosition(
				newLinkConnector,
				this.state.flowElements,
				p,
				this.state.mouseDeltaX,
				this.state.mouseDeltaY,
				this.props.settings.nodeElement,
				"left-right"),
			isCreatingLink:true
		});
	}

	createLink(targetElementId,targetIndexId) {
		var targetElement=findElement(targetElementId,this.state.flowElements);
		var selectedSourceElement=findElement(this.state.selectedSourceId,this.state.flowElements);
		const flowConnector =
			new FlowConnector (
				this.getNewConnectorId(),
				selectedSourceElement,
				this.state.selectedSourceIndex,
				targetElement,
				targetIndexId);
		this.cancelLinkCreation(null);
		var connectorList=this.state.flowConnectors.slice(0);
		connectorList.push(flowConnector);
		const connectorPositions=
			generateConnectorPositions(
				connectorList,
				this.state.flowElements,
				this.state.mouseDeltaX,
				this.state.mouseDeltaY,
				this.props.settings.nodeElement,
				"left-right");

		this.setState({
			flowConnectors:connectorList,
			connectorPositions:connectorPositions
		}
		);
	}

	deselectLink(event) {
		this.setState({
			selectedConnectorId:-1,
		});
	}
	cancelLinkCreation(event) {
		this.setState({
			selectedSourceId:-1,
			selectedSourceIndex:-1,
			newLinkConnector:null,
			isCreatingLink:false,
		});

	}


	gotoOutputConnector(event) {
		var p=getMousePosition(event,getCanvasContainerElement());
		var newConnectorPosition=
			generateConnectorPosition(
				this.state.newLinkConnector,
				this.state.flowElements,
				p,
				this.state.mouseDeltaX,
				this.state.mouseDeltaY,
				this.props.settings.nodeElement,
				"left-right");
		this.setState({
			newConnectorPosition:newConnectorPosition
		});



	}

	generateNewNode(typeId,position,scrollPosition) {
		var newId=this.getNewElementId();

		var toolElement=findElement(typeId,this.state.toolSet);
		const flowElement=
			new FlowElement(
				newId,
				toolElement.name,
				toolElement,
				position.x,
				position.y,
				this.props.settings.nodeElement.width,
				this.props.settings.nodeElement.height,
				toolElement.inputInterfaces,
				toolElement.outputInterfaces,
				"",
				"",
				toolElement.asyncInterface,
				toolElement.color,
				this.props.elementBaseProperties,
				toolElement.subFlow);
		return flowElement;
	}

	createNode(event,typeId,position) {
		var p=getMousePosition(event,null);
		var newNode=this.generateNewNode(typeId,position.absolute,getSideBarScrollPosition());
		this.setState({
			mouseDeltaX:p.x-position.absolute.x,
			mouseDeltaY:p.y-position.absolute.y,
			selectedToolId:typeId,
			newNode:newNode,
			absolutePosition:position.absolute
		});
	}
	beginDragFooter(event) {
		this.setState({
			isDraggingFooter:true,

		})

	}
	finishDragFooter(event) {
		this.setState({
			isDraggingFooter:false
		});

	}
	dragFooter(event) {
		if (this.state.isDraggingFooter) {
			var p=getMousePosition(event,getWorkspaceContainerElement());
			//398
			// 638
			// outerHeight 728
			var newYPosition=p.y+463-window.outerHeight;
			this.setState({
				footerYPosition:newYPosition
			});
		}
	}
	selectNode(event,id) {
		var p=getMousePosition(event,getCanvasContainerElement());
		var flowElement=findElement(id,this.state.flowElements);
		this.setState({
			mouseDeltaX:p.x-flowElement.x,
			mouseDeltaY:p.y-flowElement.y,
			selectedNodeId:id,
			selectedConnectorId:-1,
			selectedNodeElement:flowElement,
			currentPropertyValues:flowElement.propertyValues,
			infoProperties:"NODE"
		});
	};
	deselectNode() {
		this.setState({
			selectedNodeId:-1,
			selectedNodeElement:null});
	};
	selectConnector(event,id) {
		this.deselectNode();
		this.setState({
			selectedConnectorId:id,
		});
	};





	deselectLink() {
		this.setState({selectedConnectorId:-1});
	};


	finishNodeMoving() {
		this.setState({isMovingNode:false});
	};

	finishNodeCreation() {
		this.setState({isCreatingNode:false});
	};


	convertCoords(svgDoc,elem,x,y) {
		var offset = svgDoc.getBoundingClientRect();
		var matrix = elem.getScreenCTM();
		return {
			x: (matrix.a * x) + (matrix.c * y) + matrix.e - offset.left,
			y: (matrix.b * x) + (matrix.d * y) + matrix.f - offset.top
		};
	}

	deselectTool() {
		// add element to canvas
		// add the element to canvas if it is over canvas
		var canvasElement=getCanvasContainerElement();
		var canvasAbsolutePosition=getAbsolutePosition(canvasElement)
		var rect = canvasElement.getBoundingClientRect();
		var x=this.state.newNode.x;
		var y=this.state.newNode.y;
		var flowList=this.state.flowElements.slice(0);
		if (x>=rect.left && x<=rect.left+rect.width && y>=rect.top && y<=rect.top+rect.height) {
			var position={
				x:this.state.newNode.x-rect.left+canvasElement.scrollLeft,
				y:this.state.newNode.y-rect.top+canvasElement.scrollTop,
			}
			var svgElement=canvasElement.firstChild;
			var svgPoint=svgElement.createSVGPoint();
			var svgPosition=svgPoint.matrixTransform(svgElement.getScreenCTM().inverse());
			var sideBarElement=getSideBarContainerElement();
			const newNode=this.generateNewNode(this.state.selectedToolId,position,getSideBarScrollPosition());
			flowList.push(newNode);
		}
		this.setState({
			//selectedNodeId:this.flowElements.length-1,
			selectedToolId:null,
			flowElements:flowList});
	};



	moveTool(event) {
		var scrollPosition=getSideBarScrollPosition();

		var p=getMousePosition(event,getWorkspaceContainerElement());
		if (this.state.selectedToolId!=null) {
			var absolutePosition={
				x:p.x-this.state.mouseDeltaX-scrollPosition.left,
				y:p.y-this.state.mouseDeltaY-scrollPosition.top
			}

			const newNode=this.generateNewNode(this.state.selectedToolId,absolutePosition,getSideBarScrollPosition());
			this.setState({
				newNode:newNode,
				absolutePosition:absolutePosition
			});
		}
	};

	moveNodeElement(event){
		var p=getMousePosition(event,getCanvasContainerElement());

			var flowElements = this.state.flowElements.slice(0);
			var flowElement=findElement(this.state.selectedNodeId,flowElements);
			flowElement.x=p.x-this.state.mouseDeltaX;
			flowElement.y=p.y-this.state.mouseDeltaY;
			this.setState({
				flowElements:flowElements,
				isMovingNode:true
			});
	}
	changeNodeProperty(id,value) {
			if (this.state.currentPropertyValues!=null) {
				var newPropertyValues=this.state.currentPropertyValues.slice(0);
				newPropertyValues[id]=value;
				var cloneElement = Object.assign({}, this.state.selectedNodeElement);
				cloneElement.properties[id].value=value;
				this.setState({
					currentPropertyValues:newPropertyValues,
					selectedNodeElement:cloneElement
				});
			}
	}
	changeActivityProperty(id,value) {
		if (this.state.currentActivity!=null) {
			if (this.state.currentActivity.properties!=null) {
				var newPropertyValues=this.state.currentActivity.properties.slice(0);
				newPropertyValues[id]=value;
				var clonedActivity = Object.assign({}, this.state.currentActivity);
				clonedActivity.properties=this.state.currentActivity.properties.slice(0);
				clonedActivity.properties[id].value=value;
				this.setState({
//					currentPropertyValues:newPropertyValues,
					currentActivity:clonedActivity
				});
			}

		}
	}
	showProcesses() {
		// deselect activity
		// deselect nodeElement
		this.deselectNode();
		this.setState({
			infoProperties:"RUNNING PROCESSES"
		});

	}
	showActivityProperties() {
		// deselect activity
		// deselect nodeElement
		this.deselectNode();
		this.setState({
			infoProperties:"ACTIVITY"
		});

	}

render() {
	var bottomSplit=300-this.state.footerYPosition-14+"px";
	var elementWidth=this.props.settings.nodeElement.width ;
	var elementHeight=this.props.settings.nodeElement.height;
	var textElementHeight="12";
	var isCreatingElement=this.state.selectedToolId!=null;
	var styleSplitter={
		height: "12px",
		width:"100%",
		bottom: bottomSplit,
		zIndex:"13000",
		position:"absolute"
	};
    return (
      <div className="Workspace"
	  					onMouseMove={(event) => this.dragFooter(event)}
						onMouseUp={(event) => this.finishDragFooter(event)}
	  >
		<div className="WorkspaceMain" id="workspace"
		>
			<HeaderContainer
				jobName={this.state.jobName}
				newJob={(event)=>this.newJob()}
				openJob={(name)=>this.openJob(name)}
				host={this.props.host}
				saveJob={()=>this.saveJob()}
				saveAs={(name)=>this.saveAs(name)}
				renameJob={(name)=>this.renameJob(name)}
				prepareOpenJobSelector={() => this.prepareOpenJobSelector()}
				prepareOpenPluginManager={() => this.prepareOpenPluginManager()}
				/>
			<SideBarContainer width={this.props.sideBarWidth+"px"}
				elementTypeWidht={elementWidth}
				elementTypeHeight={elementHeight}
				textElementTypeHeight={textElementHeight}
				toolSet={this.state.toolSet}
				bottom={300-this.state.footerYPosition+36+"px"}
				createNode={(event,id,position) => this.createNode(event,id,position)}
				deselectTool={(event) => this.deselectTool()}
				moveTool={(event) => this.moveTool(event)}
				/>
			<AddToolButtonContainer width={this.props.sideBarWidth+"px"}
				bottom={300-this.state.footerYPosition+"px"}
				openPluginUploaderDialog={()=>this.openPluginUploaderDialog()}

			/>
			<PropertiesContainer
				nodeElement={this.state.selectedNodeElement}
				runningProcesses={this.state.runningProcesses}
				activity={this.state.currentActivity}
				bottom={300-this.state.footerYPosition+"px"}
				infoProperties={this.state.infoProperties}
				changeNodeProperty={(id,value) => this.changeNodeProperty(id,value)}
				changeActivityProperty={(id,value) => this.changeActivityProperty(id,value)}
				/>
			<CanvasContainer
				isMovingNode={this.state.isMovingNode}
				isCreatingNode={this.state.isCreatingNode}
				isCreatingLink={this.state.isCreatingLink}
				left={parseInt(this.props.sideBarWidth)+12+"px"}
				bottom={300-this.state.footerYPosition+30+"px"}
				flowElements={this.state.flowElements}
				flowConnectors={this.state.flowConnectors}
				textElementHeight={textElementHeight}
				selectedNodeId={this.state.selectedNodeId}
				selectedConnectorId={this.state.selectedConnectorId}
				selectedSourceId={this.state.selectedSourceId}
				selectedSourceIndex={this.state.selectedSourceIndex}
				newConnectorId={this.state.newConnectorId}
				newConnectorPosition={this.state.newConnectorPosition}
				settings={this.props.settings}
				connectorPositions={this.state.connectorPositions}
				connectorsSides="left-right"
				moveNodeElement={(event) => this.moveNodeElement(event)}
				selectNode={(event,id) => this.selectNode(event,id)}
				deselectNode={(event) => this.deselectNode(event)}
				selectConnector={(event,id) => this.selectConnector(event,id)}
				deselectLink={(event) => this.deselectLink(event)}
				finishNodeMoving={(event) =>this.finishNodeMoving(event)}
				finishNodeCreation={(event) =>this.finishNodeCreation(event)}
				cancelLinkCreation={(event) => this.cancelLinkCreation(event)}
				selectSourceConnector={(event,flowElement,connectoriId) => this.selectSourceConnector(event,flowElement,connectoriId)}
				createLink={(flowElementId,connectorId) => this.createLink(flowElementId,connectorId)}
				gotoOutputConnector={(event) => this.gotoOutputConnector(event)}
				deselectSourceConnector={(event) => this.deselectSourceConnector(event)}
				updateConnectors={(elementId) => this.updateConnectors(elementId)}
				showActivityProperties={() => this.showActivityProperties()}
				/>
			<FlowTabsContainer
				left={parseInt(this.props.sideBarWidth)+12+"px"}
				activities={this.state.activities}
				bottom={300-this.state.footerYPosition+"px"}
				currentActivityIndex={this.state.currentActivityIndex}
				selectActivity={(activityIndex) => this.selectActivity(activityIndex)}
			/>
			<ToolBarContainer
				deleteElement={() => this.deleteElement()}
				runJob={() => this.runJob()}
				runActivity={() => this.runActivity()}
				stopJob={() => this.stopJob()}
				stopActivity={() => this.stopActivity()}
				saveJob={()=>this.saveJob()}
				clearOutput={()=>this.clearOutput()}
				refreshOutput={()=>this.refreshOutput()}
				openNewActivityDialog={()=>this.openNewActivityDialog()}
				openRemoveActivityDialog={()=>this.openRemoveActivityDialog()}
				prepareOpenJobSelector={() => this.prepareOpenJobSelector()}
				showProcesses={() => this.showProcesses()}
				/>
		<div className="footerSplit"
			id="footerSplit"
			title="Collapse/Expand"
			style={styleSplitter}
			onMouseDown={(event)=>this.beginDragFooter(event)}
			onMouseMove={(event) => this.dragFooter(event)}
			onMouseUp={(event) => this.finishDragFooter(event)}


			></div>
	</div>


		<FooterContainer stdOutput={this.state.stdOutput}
			height={438+728-window.outerHeight-this.state.footerYPosition+"px"}
			top={window.innerHeight-165+60+34+12+this.state.footerYPosition-275+"px"}
		/>
	  {isCreatingElement &&
		  <NewNodeElement
				height={this.state.newNode.height}
				identifier={this.state.newNode.id}
				width={this.state.newNode.width}
				textHeight={textElementHeight}
				name={this.state.newNode.name}
				color={this.state.newNode.color}
				x={this.state.newNode.x}
				y={this.state.newNode.y}
				deselectTool={event => this.deselectTool()}
				moveTool={(event) =>this.moveTool(event)} />
	  }
		{this.state.jobSelectorOpened &&
		<JobSelector openJob={(flowJobId)=>{this.openJob(flowJobId)}}
			closeJobSelector={()=>this.closeJobSelector()}
			jobs={this.state.jobList}
			removeJob={(id,name) => this.removeJob(id,name)}/>
		}
		{this.state.newActivityDialogOpened  &&
		<ChangeNameDialog label="New Activity Name:" changeName={(newName)=>this.createNewActivity(newName)} closeDialog={()=>this.closeNewActivityDialog()} oldName=""/>
		}
		{this.state.removeActivityDialogOpened  &&
		<AreYouSureDialog label={"Are you sure you want to remove Actvity:"+this.currentActivityName} answerYes={()=>this.removeActivity()} closeDialog={()=>this.closeRemoveActivityDialog()} />
		}
		{this.state.pluginUploaderOpened  &&
		<UploadPluginDialog label="Upload Plugin File:"
			closeDialog={()=>this.closePluginUploaderDialog()}
			accept = {((fileName,file)=>this.uploadPlugin(fileName,file))}
			oldName="" />
		}
		{this.state.pluginManagerPopupOpened &&
			<PluginManagerPopup plugins={this.state.pluginList}
					closePopup={()=>this.closePluginManager()}
					activatePlugin={(id)=>this.activatePlugin(id)}
					deactivatePlugin={(id)=>this.deactivatePlugin(id)}
					removePlugin={(id)=>this.removePlugin(id)}
					>
			</PluginManagerPopup>
		}

      </div>

    );
  }
}

export default Workspace;
