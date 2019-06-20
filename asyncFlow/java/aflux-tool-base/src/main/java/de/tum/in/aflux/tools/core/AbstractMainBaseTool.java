
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

/**
 * 
 * Main classes from which tools should extend
 * It representes the main frame of execution of an element or actor
 * Each AbstractMainBaseTool should be associated to a AbstractAfluxActor that has the core of execution
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public abstract class  AbstractMainBaseTool {

	/**
	 * Number of input interfaces (Can be data or no data interfaces)
	 * no data interfaces referes to a precedence relation
	 * 
	 * 	Number of input interfaces (Includes data and no data interfaces)
	 * Input interfaces are represented as connectors that can receive data (or signal) from another element. 
	 * “no data” interfaces refers to a precedence relation.
	 * There are no difference between data or no data connectors. The real difference depends on the implementation. Usually “no data” input means that in the implementation of runCore in the actor the task can be executed with no importance of the message that has received.
	 * In case the implementation doesn’t take data from the received message can be said that the element can be launched by SIGNAL.
	 * In this version …. inputInterfaces should be always 1
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 * 
	 */
	private int inputInterfaces;
	
	/**
	 * Number of output data interfaces. This number does not include the async output connector.
	 * Actors can send data to other actors calling
	 * setOutput(index,message) having index between 1 and outputInterfaces 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private int outputInterfaces;
	/**
	 *  indicates if is async capable or not
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 *  
	 */
	private Boolean asyncInterface;
	
	/**
	 * Indicates if this tool will be launched by receiving data or by a start signal
	 * Possible values ar LAUNCHED_BY_DATA or LAUNCHED_BY_SIGNAL
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private NodeLaunchType launchedBy;
	
	
	/**
	 * Help to be shown related to the tool
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private String help;
	
	/**
	 * names and initial values of the editable properties in order
	 * 	Set of properties that allow users to set values for each instance of each element. Each of these properties will appear in the properties pane to be edited for each element
	 * 	 Names and initial values of the editable properties in order
	 * Set of properties that allow users to set values for each instance of each element. Each of these properties will appear in the properties pane to be edited for each element
	 * Beyond these defined properties each element also will have a set of base properties that can be edited (name / width / color / dispatcher / mailbox)
	 * The value the user input for each property can be accessed in runCoreImplementation calling the method this.getProperties().get(index)

	 * @author Tanmaya Mahapatra
	 *
	 */
	private ToolProperty[] properties;

	
	/**
	 * 
	 * Complete class name of related actor that contains the implementation of the task
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private String actorClassName;

	/**
	 * Name of the tool to be shown in tool gallery
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private String name;
	
	/**
	 * visual style elements for the tool 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private ToolStyle style;

	/**
	 * Formal description of execution unit
	 * It refers to class implementation and not subflow implementation
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	private ToolImplementation toolImplementation;

	/**
	 * Conditions to perform semantics validation
	 *
	 * @author Federico Fernández
	 */
	private ToolSemanticsCondition[] semanticsConditions;

	
	
	
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

	public ToolProperty[] getProperties() {
		return properties;
	}


	public void setProperties(ToolProperty[] properties) {
		this.properties = properties;
	}

	public ToolSemanticsCondition[] getSemanticsConditions() {
		return semanticsConditions;
	}

	public void setSemanticsConditions(ToolSemanticsCondition[] semanticsConditions) {
		this.semanticsConditions = semanticsConditions;
	}

	public String getActorClassName() {
		return actorClassName;
	}

	public void setActorClassName(String actorClassName) {
		this.actorClassName = actorClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}
	
	public ToolStyle getStyle() {
		return style;
	}

	public void setStyle(ToolStyle style) {
		this.style = style;
	}

	public ToolImplementation getToolImplementation() {
		return toolImplementation;
	}

	public void setToolImplementation(ToolImplementation toolImplementation) {
		this.toolImplementation = toolImplementation;
	}

	public void setColor(String color) {
		this.style.setColor(color);
	}

	public NodeLaunchType getLaunchedBy() {
		return launchedBy;
	}

	public void setLaunchedBy(NodeLaunchType launchedBy) {
		this.launchedBy = launchedBy;
	}

	public Boolean getAsyncInterface() {
		return asyncInterface;
	}

	public void setAsyncInterface(Boolean async) {
		this.asyncInterface = async;
	}



	
}
