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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tum.in.aflux.dao.FlowJobMongoRepository;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.model.SubFlow;
import de.tum.in.aflux.model.FlowActivity;

/**
 * Service to manage SubFlows
 * SubFlows are compound operations defined in an activity and used in other flows
 * 
 * Subflows are initially defined as an activity  marked as subflow=true but there 
 * When a subflow is used in a flow the entire definition is copied on it . Then if the definition is erased it does not affect the places where this subflow is used
 * When the activity is erased subflow disappears from toolbar to be added to a flow but it can still be used where it was used before
 * 
 * @author jguastav
 *
 */
@Service
public class SubFluxService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	@Autowired
	private FlowJobMongoRepository repository;	

	
	/**
	 * Get all subFlows from flowJob repository
	 * All subflows are defined for activities with subflow property set to true
	 * @return
	 */
	public List<SubFlow> findAll() {
		List<FlowJob> flowJobList=repository.findFlowJobsContainingSubFlows();
		List<SubFlow> subFlowList=new ArrayList<SubFlow>();
		for (FlowJob flowJob:flowJobList) {
			List<SubFlow> jobSubFlows=getSubFlows(flowJob);
			subFlowList.addAll(jobSubFlows);
		}
		return subFlowList;
	}
	
	public List<SubFlow> getSubFlows(FlowJob flowJob) {
		List<SubFlow> result=new ArrayList<SubFlow>();
		for (FlowActivity flowActivity:flowJob.getActivities()) {
			if (flowActivity.getProperty(FlowActivity.SUBFLOW_PROPERTY).equals("true")) {
				if (flowActivity.getParentActivityId()==null || flowActivity.getParentActivityId()<0) { // check it is not a local subflow
					result.add(new SubFlow(flowJob.getName(),flowActivity));
				}
			}
		}
		return result;
	}
                
}

