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
