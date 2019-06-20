

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

import org.springframework.data.annotation.Transient;
/**
 * Information about main element
 * This information is intended to be persisted and used in model frontend
 * @author Tanmaya Mahapatra
 *
 */
public class FlowElement {
	private Long id;
	
	/**
	 * Name in the graph. this name is not an identifier as identifiers can be present in implementation
	 * 
	 */
    private String name;
	/**
	 * Contains result of semantics validation
	 *
	 * @author Federico Fernández
	 */
	private String errors;
	private FlowElementType type;
	private String  color;
	private int  x;
	private int y;
	// TODO: width could be calculated from name
	private int  width;
	private int  height;
	private int inputInterfaces;
	private int outputInterfaces;
	private boolean deleted;
	private FlowElementProperty[] properties;
	private Boolean asyncInterface;
	private String dispatcher;
	private String mailbox;
	private SubFlow subFlow;

	private FlowElement() {}
	
	
	public FlowElement(Long id,
				String name,
				FlowElementType elementType,
				int x,int y,int width,int height,
				int inputInterfaces,int outputInterfaces,
				String color,
				int concurrency,
				FlowElementProperty[] properties,
				SubFlow subFlow) {
		this.id = id;
	    this.name = name;
		this.type = elementType;
		this.color = color;
		this.x = x;
		this.y = y;
		// TODO: width could be calculated from name
		this.width = width;
		this.height = height;
		this.inputInterfaces=inputInterfaces;
		this.outputInterfaces=outputInterfaces;
		this.deleted=false;
		FlowElementProperty[] baseProperties=FlowElementBaseProperties.generate(name, width, color, concurrency);
		FlowElementProperty[] toolProperties=properties;
		if (baseProperties==null) {
			baseProperties=new FlowElementProperty[0];
		}
		if (toolProperties==null) {
			toolProperties=new FlowElementProperty[0];
		}
		
		this.properties= new FlowElementProperty[baseProperties.length+toolProperties.length]; 
		for (int i=0;i<baseProperties.length;i++) {
			this.properties[i]=baseProperties[i];
		}
		for (int i=0;i<toolProperties.length;i++) {
			this.properties[i+baseProperties.length]=toolProperties[i];
		}
		this.asyncInterface=elementType.getAsyncInterface();
		this.dispatcher=dispatcher;
		this.mailbox=mailbox;
		this.subFlow=subFlow;
		this.errors=null;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FlowElementType getType() {
		return type;
	}

	public void setType(FlowElementType type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public int getInputInterfaces() {
		return inputInterfaces;
	}

	public void setInputInterfaces(int inputInterfaces) {
		this.inputInterfaces = inputInterfaces;
	}

	public int getOutputInterfaces() {
		return outputInterfaces;
	}

	public void setOutputInterfaces(int outputInterfaces) {
		this.outputInterfaces = outputInterfaces;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	
	
	
	public FlowElementProperty[] getProperties() {
		return properties;
	}

	public void setProperties(FlowElementProperty[] properties) {
		this.properties = properties;
	}

	
	
	
	
	
	public Boolean getAsyncInterface() {
		return asyncInterface;
	}

	public void setAsyncInterface(Boolean async) {
		this.asyncInterface = async;
	}



	
	public String[] getPropertyValues() {
		String[] result;
		if (this.properties==null) {
			result=new String[0];
		} else {
			result=new String[this.getProperties().length];
			for (int i =0;i<this.getProperties().length;i++) {
				result[i]=this.getProperties()[i].getValue();
			}
		}
		return result;
	}

	
	@Transient
	public int getAsyncInterfaces() {
		if (this.getAsyncInterface()==true) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Transient
	public int getTotalOutputInterfaces() {
		return this.getOutputInterfaces()+this.getAsyncInterfaces();
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	
	
	
	public SubFlow getSubFlow() {
		return subFlow;
	}
	public void setSubFlow(SubFlow subFlow) {
		this.subFlow = subFlow;
	}
	@Override
	public String toString() {
		return "FlowElement [id=" + id + ", name=" + name
				+ ", inputInterfaces=" + inputInterfaces
				+ ", outputInterfaces=" + outputInterfaces + ", activity="
				+ "]";
	}


	
	
}
