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

package de.tum.in.aflux.tools.core;

/**
 * Implementation information related to an executor
 * @author Tanmaya Mahapatra
 *
 */
public class ToolImplementation {
	
	/**
	 * Type is a value that specifies the kind of implementation
	 * Posible values ar 
	 * EXECUTOR
	 * SUBFLOW
	 * 
	 * 
	 */
	private ToolImplementationType type;	

	// executor implementation properties
	private String className;
	private String jarName;
	private String jarLocation;
	
	
	// subflow implementation
	private String jobName;
	private String subFlowName;
	
	
	
	public ToolImplementation(String jarLocation,String jarName,String className){
		this.jarLocation=jarLocation;
		this.jarName=jarName;
		this.className=className;
		this.type=ToolImplementationType.EXECUTOR;
	}
	
	
	// By now ToolImplementation should refer only to single node elements

	public ToolImplementation(String jobName,String subFlowName) {
		this.jobName=jobName;
		this.subFlowName=subFlowName;
		this.type=ToolImplementationType.SUBFLOW;
	}

	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getJarName() {
		return jarName;
	}
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
	public String getJarLocation() {
		return jarLocation;
	}
	public void setJarLocation(String jarLocation) {
		this.jarLocation = jarLocation;
	}
	public ToolImplementationType getType() {
		return type;
	}
	public void setType(ToolImplementationType type) {
		this.type = type;
	}

	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getSubFlowName() {
		return subFlowName;
	}
	public void setSubFlowName(String subFlowName) {
		this.subFlowName = subFlowName;
	}

	
	
}
