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

package de.tum.in.aflux.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;

/**
 * Nodes and connectors arranged for an running environment. 
 * All the nodes are present in a map and all connectors in a list
 * All nodes referres to all nodes in all levels of main flows and subflows
 * 
 * @author Tanmaya Mahapatra
 * 
 *
 */

public class FlowExecutionModel {
	Map<String,AbstractMainExecutor> executors=new HashMap<String,AbstractMainExecutor>();
	List<FlowExecutionConnector> connectors= new ArrayList<FlowExecutionConnector>();
	/**
	 * ids containing entry nodes for subfluxes
	 */
	Map<String,String> initialNodes=new HashMap<String,String>();
	/**
	 * ids containing entry nodes for subfluxes
	 */
	Map<String,String> finalNodes=new HashMap<String,String>();
	
	
	
	/**
	 * Actors of the activity
	 */
	public Map<String, AbstractMainExecutor> getExecutors() {
		return executors;
	}
	public void setExecutors(Map<String, AbstractMainExecutor> executors) {
		this.executors = executors;
	}
	public List<FlowExecutionConnector> getConnectors() {
		return connectors;
	}
	public void setConnectors(List<FlowExecutionConnector> connectors) {
		this.connectors = connectors;
	}
	@Override
	public String toString() {
		return String.format("FlowExecutionModel [\n\texecutors=%s, \n\tconnectors=%s]", executors, connectors);
	}
	public Map<String, String> getInitialNodes() {
		return initialNodes;
	}
	public void setInitialNodes(Map<String, String> initialNodes) {
		this.initialNodes = initialNodes;
	}
	public Map<String, String> getFinalNodes() {
		return finalNodes;
	}
	public void setFinalNodes(Map<String, String> finalNodes) {
		this.finalNodes = finalNodes;
	}
	
	
	
	

}
