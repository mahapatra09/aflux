

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
