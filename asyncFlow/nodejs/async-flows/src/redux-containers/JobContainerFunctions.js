
import { setUpdateCurrentJobAction } from '../redux-actions/workspaceActionTypes';
import { applyGeneralActionAction } from '../redux-actions/aFluxActionTypes';
import { setFlowActivities } from '../redux-containers/ActivityContainerFunctions';
import { refreshTools } from './ToolContainerFunctions';




// model
import FlowJob from '../model/FlowJob';
import FlowActivity from '../model/FlowActivity';


function updateCurrentJob(job) {
  window.reduxStore.dispatch(setUpdateCurrentJobAction(job));
}

export function openJobSelector(jobList) {
  var newJobList=[];
  for (var i=0;i<jobList.length;i++) {
    newJobList.push(jobList[i]);
  }
  window.reduxStore.dispatch(applyGeneralActionAction({
    jobList:newJobList,
    jobSelectorOpened:true
  },"openJobSelector"));
};



export function newJob(activityBaseProperties) {
  var activityName="activity-1";
  var newActivity=new FlowActivity(1,activityName,1,[],[],activityBaseProperties,-1,-1);
  var activities=[newActivity];
  var newJob=new FlowJob(null,"_NewJob",activities);
  var jobName=newJob.name;
  window.reduxStore.dispatch(applyGeneralActionAction({
    currentJob:newJob,
    jobName:jobName,
    activities:activities,
    currentActivityIndex:0,
    flowElements:[],
    flowConnectors:[],
    currentActivity:activities[0]
  },"activityBaseProperties"
  ))
};


export function setFlowJob(job,nodeElementSettings) {
  setFlowActivities(job.activities,nodeElementSettings);
  window.reduxStore.dispatch(applyGeneralActionAction({
    currentJob:job,
    jobName:job.name
  },"setFlowJob"))
}


export function closeJobSelector() {
  window.reduxStore.dispatch(applyGeneralActionAction({
    jobSelectorOpened:false
  },"closeJobSelector"))
}

export function finishValidateJob(job, host, nodeElementSettings) {
  console.log("finishValidateJob");
  setFlowActivities(job.activities,nodeElementSettings);
}

export function finishSaveJob(job,host) {
  if (job==="") { // it couldnt be saved
    alert("error saving job "+job);
  } else {
    refreshTools(host);
    updateCurrentJob(job);
  }
}

export function finishSaveAsJob(job,host) {
  if (job==="") { // it couldnt be saved
    alert("error saving job "+job);
  } else {
    refreshTools(host);
    updateCurrentJob(job);
  }
}

export function updateActivitiesOnJob(job,activities,componentLinks)  {
    var resultJob=job;
    resultJob.activities=activities;
    resultJob=updateSubflowActivities(resultJob,componentLinks);
    return resultJob;
}

function updateSubflowActivities(job,componentLinks) {
  var resultJob=job;
  var activities = job.activities;
  return resultJob;
}

/*
  for (var i=0;i<componentLinks.length;i++) {
        resultJob =



     if (state.componentLinks[i].component!==action.activityName) {
       updatedComponentLinks.push(state.componentLinks[i]);
     } else {
       isSubFlow=true;
     }
  }


  componentLinks:[
     ...state.componentLinks,
     {component:action.activity.name,
       parentActivity:action.parentActivityName,
       parentNode:action.parentNodeId}
  ]


  if (isSubFlow) {
    for (var i=0;i<state.activities.length;i++) {
       var activity=state.activities[i];
       if (activity.name!==action.activityName) {
         updatedActivities.push(activity);
       } else {
         deletedIndex=i;
       }
    }
    if (deletedIndex<=currentActivityIndex) {
      currentActivityIndex--;
    }
 }



  for (var i=0;i<activities.length;i++) {


    if (activities[i].name===name) {
      result=activities[i];
    }
  }

  */
