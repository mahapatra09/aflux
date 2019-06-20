

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

import javax.persistence.Id;
/**
 * Bean to store plugin information 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class FlowPlugin {
	@Id
	private String id;
	private String jarLocation;
	private String jarName;
	
	
	/**
	 * Indicates if the plugin is currengly active
	 * When a plugin is active the classes contained in the jar are loaded 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private Boolean activated;
	
	/**
	 * Dynamic activation indicates if a plugin can be activated in the current running app
	 * This property is setted to true when app is started for all inactive plugin-
	 * This property is setted to false only when a plugin is deactivated until the moment the app is restarted
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private Boolean dynamicActivation;
	
	
	
	public FlowPlugin() {
		super();
	}
	public String getJarLocation() {
		return jarLocation;
	}
	public void setJarLocation(String jarLocation) {
		this.jarLocation = jarLocation;
	}
	public String getJarName() {
		return jarName;
	}
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public FlowPlugin(String jarLocation, String jarName) {
		super();
		this.jarLocation = jarLocation;
		this.jarName = jarName;
		this.activated= false;
		this.dynamicActivation=true;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getDynamicActivation() {
		return dynamicActivation;
	}
	public void setDynamicActivation(Boolean dynamicActivation) {
		this.dynamicActivation = dynamicActivation;
	}

	
	
	
}
