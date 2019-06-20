

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

package de.tum.in.aflux.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import de.tum.in.aflux.dao.FlowJobMongoRepository;
import de.tum.in.aflux.model.FlowActivity;
import de.tum.in.aflux.model.FlowElement;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.util.FileSystemUtil;

/**
 * 
 * 
 * Job Related Operations
 * 
 * @author Tanmaya Mahapatra
 *
 */

@Service
public class FluxJobService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	@Autowired
	private FlowJobMongoRepository repository;
	
	@Autowired
	private FluxElementTypeService toolService;
	
	
    public FlowJob save(FlowJob job,boolean isNew) {
    	String previousName=job.getName();
    	if (isNew) {
    		job.setId(null);
    	} else {
    		FlowJob previousJob=repository.findOne(job.getId());
    		previousName=previousJob.getName();
    	}
    	job=synchronizeSubflows(job); 
    	FlowJob result = repository.save(job);
    	toolService.updateGlobalSubFlows(job,previousName);
		return result;
    }
	
    


	private FlowJob synchronizeSubflows(FlowJob job) {
		for (int i=0;i<job.getActivities().size();i++) {
			FlowActivity activity=job.getActivities().get(i);
			activity=synchronizeSubflows(job,i);
		}
		return job;
	}




	private FlowActivity synchronizeSubflows(FlowJob job, int activityIndex) {
		FlowActivity activity=job.getActivities().get(activityIndex);
		for (int i=0;i<activity.getElements().size();i++) {
			FlowElement flowElement=activity.getElements().get(i);
			flowElement=synchronize(job,activityIndex,flowElement);
		}
		return activity;
	}




	private FlowElement synchronize(FlowJob job, int activityIndex, FlowElement flowElement) {
		FlowElement result=flowElement;
		if (flowElement.getSubFlow()!=null) {
			FlowActivity activity=findActivityById(job,flowElement.getSubFlow().getActivity().getId());
			result.getSubFlow().setActivity(activity);
		}
		return flowElement;
	}

	


	private FlowActivity findActivityById(FlowJob job, Long id) {
		boolean found=false;
		FlowActivity activity=null;
		for (int i=0;i<job.getActivities().size() && !found;i++) {
			if (job.getActivities().get(i).getId()==id) {
				found=true;
				activity=job.getActivities().get(i);
			}
		}
		return activity;
	}

	public String getLastActivityChart(String jobId,Long activityId) {
		// by now always get the last graph.
		// TODO: to improve this method. get last activity Chart

		String baseDir=FileSystemUtil.getJobsBaseDir("charts");
		String fullFileName=baseDir+"/"+activityId.toString()+".html";
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(fullFileName)));
		} catch (IOException e) {
			contents =  generateNoChartPage();
		}
		return contents;
	}

	
	private String generateNoChartPage() {
		String result="<html><head></head><body><p>No generated graph</p></body></html>";
		return result;
	}

	/**
     * Get Activity by a given name in a given job
     * @param job
     * @param activityId
     * @return
     */
    public FlowActivity getActivity(FlowJob job,Long activityId) {
    	FlowActivity result=null;
    	for (FlowActivity activity:job.getActivities()) {
    		if (activity.getId()==activityId) {
    			result=activity;
    		}
    	}
    	return result;
    }
    
    public  FlowJob getByName(String name) {
		Example<FlowJob> example = Example.of(new FlowJob(name));
		FlowJob job=repository.findOne(example);    	
		return job;
    }
    
    public FlowJob findOne(String id ) {
    	FlowJob job=repository.findOne(id);
    	return job;
    }
    
    public List<FlowJob> findAll() {
		List<FlowJob> result=repository.findAll();
		return result;
    }


	public FlowJob removeJob(String jobId) {
		FlowJob job=repository.findOne(jobId);  
		repository.delete(job);
		return job;
	}
	
	









    
}
