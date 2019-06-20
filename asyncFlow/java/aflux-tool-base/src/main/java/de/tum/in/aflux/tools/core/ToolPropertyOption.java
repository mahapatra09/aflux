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

public class ToolPropertyOption {
	String key;
	String value;
	
	
	/**
	 * Empty constructor to be used by Spring mongo repository
	 */
	public ToolPropertyOption() {
		super();
	}
	
	
	public ToolPropertyOption(String key,  String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Create options based on a unique string separating values by | and options by ; 
	 * @param definitionString
	 */
	public ToolPropertyOption(String definitionString) {
			String option[]=definitionString.split("|");
			this.key=option[0];
			this.value=option[1];
	} 
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
