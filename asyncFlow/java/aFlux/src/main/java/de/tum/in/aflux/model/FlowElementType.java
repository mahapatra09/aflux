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

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Id;

import de.tum.in.aflux.plugin.util.PluginLoader;
import de.tum.in.aflux.tools.core.AbstractMainBaseTool;
import de.tum.in.aflux.tools.core.ToolImplementationType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * 
 * Definition of elements
 * The definition defines the features an element has
 * Ths is the representation of the tool to be persisted and interact with fronted
 * The real implementation will be made with a pair of classes that extend from AbstractMainBaseTool and AbstractAFluxActor
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class FlowElementType {

	
	private static final String SUB_FLOW_CLASS_NAME="de.tum.in.aflux.component.subflow.SubFlowTool";
	

	private @Id String id;

	@Column(unique=true)
	private String name;
	private String style;
	private String color;
	
	
	/**
	 * Type is a value that specifies the kind of implementation
	 * Posible values ar 
	 * EXECUTOR
	 * SUBFLOW
	 * 
	 * 
	 */
	private ToolImplementationType type;
	
	/**
	 * Category name to group tools
	 */
	private String category;
	
	
	// node implementation
	private String jarLocation;
	private String jarName;
	private String className;
	
	// subflow implementation
	private String jobName;
	private String subFlowName;
	
	// subflow definition
	SubFlow subFlow;
	
	
	private Integer inputInterfaces;
	/**
	 * number of output data interfaces
	 * this number does not include the async interface
	 */
	private Integer outputInterfaces;

	// Configuration elements
	public FlowElementTypeProperty[] properties;
	
	//Support fields
	private String help;
	
	
	private Boolean asyncInterface;
	
	public FlowElementType() {}
	
	
	public FlowElementType(String jarLocation,String jarName,String className) 
			throws MalformedURLException, 
				ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				URISyntaxException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		this((AbstractMainBaseTool) PluginLoader.getInstance(className));
		this.setJarLocation(jarLocation);
		this.setJarName(jarName);
		
		if (!className.equals(SUB_FLOW_CLASS_NAME)) {
			this.setType(ToolImplementationType.EXECUTOR);
		} else {
			this.setType(ToolImplementationType.SUBFLOW);
			this.setClassName(className);
			
		}
	}


	public FlowElementType(AbstractMainBaseTool tool) {
		this.name=tool.getName();
		this.inputInterfaces=tool.getInputInterfaces();
		this.outputInterfaces=tool.getOutputInterfaces();
		this.asyncInterface=tool.getAsyncInterface();
		this.color=tool.getStyle().getColor();
		this.style=tool.getStyle().getStyle();
		this.jarLocation=tool.getToolImplementation().getJarLocation();
		this.jarName=tool.getToolImplementation().getJarName();
		this.className=tool.getToolImplementation().getClassName();
		this.properties=convertProperties(tool.getProperties());
		this.help=tool.getHelp();
		// TODO: Review this removal
		// this.setType(tool.getToolImplementation().getType());
		this.jobName=tool.getToolImplementation().getJobName();
		this.setType(tool.getToolImplementation().getType());;
		this.jobName=null;
		if (tool.getToolImplementation().getType().equals(ToolImplementationType.SUBFLOW)) {
			this.subFlowName=tool.getToolImplementation().getSubFlowName();
			this.setSubFlowName(tool.getToolImplementation().getSubFlowName());
			this.setSubFlow(new SubFlow(null,new FlowActivity(-1L,-1,"")));
		} else {
			this.subFlow=null;
			this.setSubFlowName(null);
		}
	}

	
	public FlowElementType(SubFlow subFlow) {
		this.name=subFlow.getJobName()+"."+subFlow.getActivity().getName();
		this.color=subFlow.getProperty(FlowActivity.COLOR_PROPERTY);
		this.style="";
		this.setType(ToolImplementationType.SUBFLOW);
		this.setCategory(subFlow.getProperty(FlowActivity.CATEGORY_PROPERTY));
		this.setJobName(subFlow.getJobName());
		this.setSubFlowName(subFlow.getActivity().getName());
		this.inputInterfaces=1;
		this.outputInterfaces=1;
		this.asyncInterface=true;
		this.help="Global SubFlow defined in Job:"+this.getJobName()+" Activity:"+this.getSubFlowName();
		this.properties=SubFlowElementBaseProperties.generate(this.name, 0, this.color, 1);
		this.subFlow=subFlow;
	}


	
	
	
	/**
	 * update tool information
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws URISyntaxException
	 */
	public void update() throws 
			MalformedURLException, 
			ClassNotFoundException, 
			InstantiationException, 
			IllegalAccessException, 
			NoSuchMethodException, 
			SecurityException, 
			IllegalArgumentException, 
			InvocationTargetException, 
			URISyntaxException {
		FlowElementType newInstance=new FlowElementType(this.jarLocation,this.jarName,this.className);
		this.name=newInstance.getName();
		this.inputInterfaces=newInstance.getInputInterfaces();
		this.outputInterfaces=newInstance.getOutputInterfaces();
		this.asyncInterface=newInstance.getAsyncInterface();
		this.color=newInstance.getColor();
		this.style=newInstance.getStyle();
		this.properties=newInstance.getProperties();
		this.help=newInstance.getHelp();
	}
	

	
	private FlowElementTypeProperty[] convertProperties(ToolProperty[] toolProperties) {
		FlowElementTypeProperty[] result;
		if (toolProperties==null) {
			result=new FlowElementTypeProperty[0];
		} else {
			result=new FlowElementTypeProperty[toolProperties.length];
			for (int i =0;i<toolProperties.length;i++) {
				result[i]=new FlowElementTypeProperty(toolProperties[i].getName(),
						toolProperties[i].getInitialValue(),
						toolProperties[i].getType(),
						toolProperties[i].getOptions(),
						toolProperties[i].getHint(),
						toolProperties[i].getHtml(),
						toolProperties[i].getReadOnly());
			}
		}
		return result;
	}


	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getInputInterfaces() {
		return inputInterfaces;
	}

	public void setInputInterfaces(Integer inputInterfaces) {
		this.inputInterfaces = inputInterfaces;
	}

	public Integer getOutputInterfaces() {
		return outputInterfaces;
	}

	public void setOutputInterfaces(Integer outputInterfaces) {
		this.outputInterfaces = outputInterfaces;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}




	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public FlowElementTypeProperty[] getProperties() {
		return properties;
	}
	
	public void setProperties(FlowElementTypeProperty[] properties) {
		this.properties = properties;
	}

	public Boolean getAsyncInterface() {
		return asyncInterface;
	}

	public void setAsyncInterface(Boolean async) {
		this.asyncInterface = async;
	}
	public ToolImplementationType getType() {
		return type;
	}
	public void setType(ToolImplementationType type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public SubFlow getSubFlow() {
		return subFlow;
	}
	public void setSubFlow(SubFlow subFlow) {
		this.subFlow = subFlow;
	}
	
	
	

}
