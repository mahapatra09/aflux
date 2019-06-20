

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

import java.util.List;

import de.tum.in.aflux.flux_engine.impl.FluxJobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.service.FluxJobService;

/**
 * Class to manage job related requests
 * @author Tanmaya Mahapatra
 *
 */
@CrossOrigin(origins="http://localhost:3000")
@Controller
public class FluxJobController {

	@Autowired
	private FluxJobService service;
	
    @RequestMapping(value = "/jobs")
    public  @ResponseBody List<FlowJob> getAll() {
		List<FlowJob> result=service.findAll();
		return result;
    }
	
    @RequestMapping(value = "/jobs/get/{id}")
    public  @ResponseBody FlowJob findOne(@PathVariable String id) {
		FlowJob job=service.findOne(id);    	
		return job;
    }

    @RequestMapping(value = "/jobs/validate", method = RequestMethod.POST)
	public @ResponseBody FlowJob validate(@RequestBody FlowJob job) {
		return FluxJobValidator.validateJob(job);
	}
    
    @RequestMapping(value = "/jobs/save", method = RequestMethod.POST)
    public @ResponseBody FlowJob save(@RequestBody FlowJob job) {
    	FlowJob result = service.save(job,false);
		return result;
    }
	
    @RequestMapping(value = "/jobs/saveAs", method = RequestMethod.POST)
    public @ResponseBody FlowJob saveAs(@RequestBody FlowJob job) {
    	FlowJob previousJob=service.getByName(job.getName());
    	FlowJob result=null;
    	if (previousJob==null) {
        	result = service.save(job,true);
    	}
		return result;
    }
	
    @RequestMapping(value = "/job/remove/{jobId}", method = RequestMethod.POST)
    public @ResponseBody FlowJob removeJob(@PathVariable String jobId) {
    	FlowJob result = service.removeJob(jobId);
		return result;
    }
    

	
}
