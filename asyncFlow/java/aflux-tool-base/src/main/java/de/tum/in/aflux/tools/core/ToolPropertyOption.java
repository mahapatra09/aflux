

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
