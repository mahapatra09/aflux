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
