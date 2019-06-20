

// actions

import { setOutputAction } from '../redux-actions/workspaceActionTypes';
import { showRunningProcessesAction } from '../redux-actions/workspaceActionTypes';
import { setTimeoutHandleAction } from '../redux-actions/workspaceActionTypes';




// functions
import { isInBottom } from '../functions/WorkspaceManager';


var client = require('../functions/client');


export function	refresh(host) {
  			refreshOutput(host,window.reduxStore.getState().aFlux.stdOutput);
  			// this.refreshProcesses();
        // console.log(window.reduxStore.geState().aFlux.runningProcesses);
  			if (window.reduxStore.getState().aFlux.runningProcesses.length>0) {
          // remove to avoid refresh
          // TODO: Reactivate
          // setTimeout(()=>checkProcessesFromServer(host),1200);
          window.reduxStore.dispatch(setTimeoutHandleAction(setTimeout(()=>checkProcessesFromServer(host),1300)));
  			}
  	}

export function refreshOutput(host,stdOutput) {
  		client({method: 'GET',
  			mode:'no-cors',
  			path: host+'/showOutput'}).done(
          response => {addToStandardOutput(response.entity,stdOutput)});

  	}

export function addToStandardOutput(stringList,stdOutput)  {
  		var initialSituationAtBottom=isInBottom("stdoutput");
  		var newArray=stdOutput.concat(stringList);
      // console.log("addToStandardOutput");
      // console.log(newArray);
      window.reduxStore.dispatch(setOutputAction(newArray));
  		/*
  		if (initialSituationAtBottom) {
  				gotoBottom("stdoutput");
  		}
  		*/
  	}


export function showRunningProcesses(processes,host) {
    window.reduxStore.dispatch(showRunningProcessesAction(processes));
    refresh(host);
};


function checkProcessesFromServer(host) {
  client({method: 'GET',
    mode:'no-cors',
    path: host+'/running'}).done(
      response => {showRunningProcesses(response.entity,host)});
}

export function addToStandardOutputCycle(stringList,host,stdOutput) {
  addToStandardOutput(stringList,stdOutput);
};

export function checkRunFinished(runningProcesses,host) {
  // showRunningProcesses(runningProcesses,host);
  if (runningProcesses.length===0) {
    // this.setState()
    // before implementation
      window.reduxStore.dispatch();
  }
};



export function onKeyPressEvent(event) {
  alert(event.key);
  if(event.key == 'Delete'){
    alert("Delete pressed");
}
};
