/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
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
