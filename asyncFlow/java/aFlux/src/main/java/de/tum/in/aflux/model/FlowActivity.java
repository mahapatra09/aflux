

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


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

/**
 * Activity :
 * Each activity has a set of elements (actors) and connectors between them that 
 * can be executed and passed data
 *  
 * @author Tanmaya Mahapatra
 *
 */
public class FlowActivity {

	
	public static String NAME_PROPERTY="name";
	public static String COLOR_PROPERTY="color";
	public static String CATEGORY_PROPERTY="category";
	public static String SUBFLOW_PROPERTY="subFlow";
	
	
	
	private Long id;
	private String name;
	
	/**
	 * This index can be changed. Name inside a job should be unique
	 */
	private Integer index;
	

	private List<FlowElement> elements;

	
	private List<FlowConnector> connectors;
	private FlowElementProperty[] properties;
	
	/**
	 * parentActivityId and parentNodeId are redundant information.
	 * It is part of a doble link relation
	 * It points in local jobs for the subflow detail of the node identified by parentNodeId in the activity identified by parentActivityId in the current job
	 */
	private Long parentActivityId;
	private Long parentNodeId;
	

	private FlowActivity() {}
	
	public FlowActivity(Long id,Integer index,String name,List<FlowElement> elements,List<FlowConnector> connectors) {
		this.id=id;
		this.index=index;
		this.name=name;
		this.elements=elements;

		this.connectors=connectors;
		this.properties= ActivityBaseProperties.generate(name);
		this.parentNodeId=-1L;
		this.parentActivityId=-1L;

	}

	
	public FlowActivity(Long id,Integer index,String name) {
		this(id,index,name,new ArrayList<FlowElement>(),new ArrayList<FlowConnector>());
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

	public List<FlowElement> getElements() {
		return elements;
	}

	public void setElements(List<FlowElement> elements) {
		this.elements = elements;
	}
	public List<FlowConnector> getConnectors() {
		return connectors;
	}

	public void setConnectors(List<FlowConnector> connectors) {
		this.connectors = connectors;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	
	
	

	
	public Long getParentActivityId() {
		return parentActivityId;
	}

	public void setParentActivityId(Long parentActivityId) {
		this.parentActivityId = parentActivityId;
	}

	public Long getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(Long parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public FlowElementProperty[] getProperties() {
		return properties;
	}
	public void setProperties(FlowElementProperty[] properties) {
		this.properties = properties;
	}
	/**
	 * Returns the value corresponding to a given argument 
	 * @param argument
	 * @return
	 * 
	 */
	public String getProperty(String argument) {
		String result="";
		boolean found=false;
		for (int i=0;i<getProperties().length &!found;i++) {
			if (getProperties()[i].getName().equals(argument)) {
				result=getProperties()[i].getValue();
				found=true;
			}
		}
		return result;
	}
	
	
	
	
	@Override
	public String toString() {
		return "\n\nFlowActivity [id=" + id + ", name=" + name + ", index=" + index
				+ ", \n elements=" + elements + ", \n connectors=" + connectors + "]";
	}
	
	
	
}
