

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

package de.tum.in.aflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tum.in.aflux.flux_engine.impl.FluxRunnerEnvironmentImpl;

@CrossOrigin(origins="http://localhost:3000")
@Controller
public class BuildGraphController {

	@Autowired
	FluxRunnerEnvironmentImpl runnerEnvironment;

	/**
	 * Show last generated chart in the specified job/activity
	 * @param jobName
	 * @param activityId
	 * @return
	 */
	
	@RequestMapping(value="/showLastChart/{jobName}/{activityId}", method = RequestMethod.GET)	
	public @ResponseBody String showLastChart(@PathVariable String jobName,@PathVariable Long activityId) {
		String result=runnerEnvironment.showLastChart(jobName,(Long) activityId);
		return result;
	}
}