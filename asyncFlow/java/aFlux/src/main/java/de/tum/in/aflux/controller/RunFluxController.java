

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright 2019 Tanmaya Mahapatra
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

package de.tum.in.aflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tum.in.aflux.flux_engine.impl.FluxProcessKey;
import de.tum.in.aflux.flux_engine.impl.FluxRunnerEnvironmentImpl;

/**
 * class to manage running processes related requests
 * @author Tanmaya Mahapatra
 *
 */
@CrossOrigin(origins="http://localhost:3000")
@Controller
public class RunFluxController {

	
	
	@Autowired
	FluxRunnerEnvironmentImpl runnerEnvironment;
	
	
	@RequestMapping(value="/run/{jobName}/{activityId}", method = RequestMethod.GET)	
	public @ResponseBody FluxProcessKey[] runActivity(@PathVariable String jobName,@PathVariable Long activityId) {
		runnerEnvironment.run(jobName,(Long) activityId);
		FluxProcessKey[] result=runnerEnvironment.getRunningProcesses();
		return result;
	}
	
	@RequestMapping(value="/runAll/{jobName}", method = RequestMethod.GET)	
	public @ResponseBody FluxProcessKey[] runAll(@PathVariable String jobName) {
		runnerEnvironment.run(jobName);
		FluxProcessKey[] result=runnerEnvironment.getRunningProcesses();
		return result;
	}
	
		
	@RequestMapping(value="/stop/{jobName}/{activityId}", method = RequestMethod.GET)	
	public @ResponseBody FluxProcessKey[] stopActivity(@PathVariable String jobName,@PathVariable Long activityId) {
		runnerEnvironment.stop(jobName,activityId);
		FluxProcessKey[] result=runnerEnvironment.getRunningProcesses();
		return result;
	}
	
	@RequestMapping(value="/stopAll/{jobName}", method = RequestMethod.GET)	
	public @ResponseBody FluxProcessKey[] stop(@PathVariable String jobName) {
		runnerEnvironment.stop(jobName);
		FluxProcessKey[] result=runnerEnvironment.getRunningProcesses();
		return result;
	}
	
	@RequestMapping(value="/running", method = RequestMethod.GET)	
	public @ResponseBody FluxProcessKey[] getRunningProcesses() {
		FluxProcessKey[] result=runnerEnvironment.getRunningProcesses();
		return result;
	}
	

}
