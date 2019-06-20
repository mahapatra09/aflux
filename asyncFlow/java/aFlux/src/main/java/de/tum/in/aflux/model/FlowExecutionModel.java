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
