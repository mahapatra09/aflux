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
