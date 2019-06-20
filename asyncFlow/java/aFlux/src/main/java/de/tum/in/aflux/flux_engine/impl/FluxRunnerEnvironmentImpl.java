

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tum.in.aflux.flux_engine.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tum.in.aflux.flux_engine.launcher.FluxCompleteEnvironment;
import de.tum.in.aflux.model.FlowActivity;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.service.FluxJobService;
/**
 * Main execution context that manages running processes
 * Starts / Stops and manage running flows
 *
 * @author Tanmaya Mahapatra
 *
 */
@Component
public class FluxRunnerEnvironmentImpl {
	
	
	@Autowired
	FluxJobService jobService;
	
	@Autowired
	FluxCompleteEnvironment environment;

	
	private Map<FluxProcessKey,FluxRunnerImpl> runners=new HashMap<FluxProcessKey,FluxRunnerImpl>();

	
	/**
	 * Runs an activity
	 * @param jobName
	 * @param activityId
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void run(String jobName, Long activityId) {
		FluxRunnerImpl runner=new FluxRunnerImpl(environment,jobService,jobName,activityId,this);
		// only to show state of running processes
		FluxProcessKey processKey=new FluxProcessKey(jobName,activityId);
		if (!runners.containsKey(processKey)) {
			runners.put(new FluxProcessKey(jobName,activityId),runner);
			// run activity
			runner.run();
		}
	} 
	
	
	/**
	 * Runs all activities of a job
	 * @param jobName
	 *
	 * @author Tanmaya Mahapatra
	 */
	public void run(String jobName) {
		FlowJob flowJob=jobService.getByName(jobName);
		for (FlowActivity flowActivity:flowJob.getActivities()) {
			if (flowActivity.getParentActivityId()==null || flowActivity.getParentActivityId()<0) {
				run(jobName,flowActivity.getId());
			}
		}
	} 
	
	/**
	 * Stop all runnning activities of a job
	 * @param jobName
	 *
	 * @author Tanmaya Mahapatra
	 */
	public void stop(String jobName) {
		FlowJob flowJob=jobService.getByName(jobName);
		for (FlowActivity flowActivity:flowJob.getActivities()) {
			stop(jobName,flowActivity.getId());
		}
	} 
	
	
	/**
	 * Stop a running activity
	 * @param jobName
	 * @param activityName
	 *
	 * @author Tanmaya Mahapatra
	 */
	public void stop(String jobName, Long activityId) {
		FluxProcessKey processKey=new FluxProcessKey(jobName,activityId);
		if (runners.containsKey(processKey)) {
			FluxRunnerImpl runner = runners.get(processKey);
			if (runner!=null) {
				runner.stop();
				runners.remove(processKey);
			}
		}
	} 	
	
	/**
	 * Get the list of running processes
	 * @return
	 * The list of pairs JobName/ActivityName of current running processes
	 *
	 * @author Tanmaya Mahapatra
	 */
	public FluxProcessKey[] getRunningProcesses() {
		FluxProcessKey[] result=new FluxProcessKey[runners.size()];
		int i=0;
		for (Entry<FluxProcessKey,FluxRunnerImpl> processEntry:runners.entrySet()) {
			result[i]=processEntry.getKey();
			i++;
		}
		return result;
	}

	public String showLastChart(String jobName, Long activityId) {
		String result = jobService.getLastActivityChart(jobName,activityId);
		return result;
	}
	
	
}
