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
 * Class to store the property definition that can be setted in each instance element
 * This structure is intended for definition. It lacks of the value of an instance
 * @author Tanmaya Mahapatra
 *
 */
public class FlowElementTypeProperty {
	
	
	String name;
	/**
	 * String representation of the value
	 */
	String initialValue;
	PropertyInputType type;
	String hint;
	String html;
	Boolean readOnly;
	ToolPropertyOption[] options;
	
	
	public String getName() {
		return name;
	}
	
	public FlowElementTypeProperty() {};
	
	public FlowElementTypeProperty(String name, String initialValue,PropertyInputType type,ToolPropertyOption[] options,String hint, String html,Boolean readOnly) {
		super();
		this.name = name;
		this.hint = hint;
		this.html = html;
		this.type = type;
		this.initialValue=initialValue;
		this.readOnly=readOnly;
		this.options=options;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}

	public String getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}
	
	
	
	public PropertyInputType getType() {
		return type;
	}
	public void setType(PropertyInputType type) {
		this.type = type;
	}
	
	

	public Boolean getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public static ToolProperty toToolProperty(FlowElementTypeProperty inputProperty) {
		ToolProperty result;
		if (inputProperty==null) {
			result=null;
		} else {
				result=new ToolProperty(inputProperty.getName(),
						inputProperty.getInitialValue(),
						inputProperty.getType(),
						inputProperty.getOptions(),
						inputProperty.getHint(),
						inputProperty.getHtml(),
						inputProperty.getReadOnly());
		}
		return result;
	}

	public ToolPropertyOption[] getOptions() {
		return options;
	}

	public void setOptions(ToolPropertyOption[] options) {
		this.options = options;
	}	
	
	

}
