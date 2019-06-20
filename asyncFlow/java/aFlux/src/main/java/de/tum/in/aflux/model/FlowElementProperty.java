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

package de.tum.in.aflux.model;

import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;
import de.tum.in.aflux.tools.core.ToolPropertyOption;


/**
 * 
 * Set of properties that each instance can be setted by the user
 * Each tool should define the set of values can be setted in the instance
 * @author Tanmaya Mahapatra
 *
 */
public class FlowElementProperty extends FlowElementTypeProperty {


	
	private FlowElementProperty() {
		super();
	};
	
	public FlowElementProperty(ToolProperty toolProperty, String value) {
		super(toolProperty.getName(),toolProperty.getInitialValue(),toolProperty.getType(),toolProperty.getOptions(),toolProperty.getHint(),toolProperty.getHtml(),toolProperty.getReadOnly());
		this.value=value;
	}
	
	public FlowElementProperty(String name, String value, String initialValue, PropertyInputType type,ToolPropertyOption[] options,String hint, String html,Boolean readOnly) {
		super(name, initialValue, type,options,hint, html,readOnly);
		this.value=value;
	}

	/**
	 * String representation of the value
	 */
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format(
				"FlowElementProperty [value=%s, name=%s, initialValue=%s, type=%s, hint=%s, html=%s, readOnly=%s]",
				value, name, initialValue, type, hint, html, readOnly);
	}
	
	
	
}
