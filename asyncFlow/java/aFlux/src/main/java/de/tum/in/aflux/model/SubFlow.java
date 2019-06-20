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

package de.tum.in.aflux.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Tanmaya Mahapatra
 * 
 * Subflow Class . Each subflow has base information of activity that defined subflow initially and the same detail.
 * When a subflow is used in a flow... all information is cloned. If after that activity is removed, flow where it is used has all the information
 * If source activity  is modified, all used subflows are not updated
 * In future can be done a new function to refresh outdated used subflows
 *
 */
@Document
public class SubFlow {
	
	
	/**
	 * job Name where activity base of subflow was defined
	 */
	String jobName;
	
	/**
	 * Each subflow is a set of connected components defined in an activity
	 * An activity that has property subFlow = true is a subFlow Definition
	 */
	FlowActivity activity;
	
	
	public String getJobName() {
		return jobName;
	}
	
	
	
	/**
	 * Empty arguments constructor
	 */
	public SubFlow() {
		super();
	}




	/**
	 * SubFlow main constructor
	 * @param jobName
	 * @param activity
	 */
	public SubFlow(String jobName, FlowActivity activity) {
		super();
		this.jobName = jobName;
		this.activity = activity;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public FlowActivity getActivity() {
		return activity;
	}
	public void setActivity(FlowActivity activity) {
		this.activity = activity;
	}


	/**
	 * Returns the value corresponding to a given argument 
	 * @param argument
	 * @return
	 * 
	 */
	public String getProperty(String argument) {
		return getActivity().getProperty(argument);
	}
	
	
	
}
