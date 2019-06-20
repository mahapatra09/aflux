

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

package de.tum.in.aflux.flux_engine.impl;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import de.tum.in.aflux.flux_engine.launcher.FluxCompleteEnvironment;
import de.tum.in.aflux.model.FlowActivity;
import de.tum.in.aflux.model.FlowConnector;
import de.tum.in.aflux.model.FlowElement;
import de.tum.in.aflux.model.FlowElementType;
import de.tum.in.aflux.model.FlowExecutionConnector;
import de.tum.in.aflux.model.FlowExecutionModel;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.ToolBaseProperties;

/**
 * Class for manage the 
 * @author Tanmaya Mahapatra
 *
 */
public class FlowExecutorModelManager {
	
	private static final String INITIAL_FLOW_NODE_CLASS_NAME="de.tum.in.aflux.component.subflow.StartSubFlowTool";
	private static final String FINAL_FLOW_NODE_CLASS_NAME="de.tum.in.aflux.component.subflow.EndSubFlowTool";
	
	
	
	/**
	 * Instantiate initially all akka actors that will participate in the activity execution
	 * It generates a flat graph that includes all subflows
	 * @param flowElements
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 
	 * @throws MalformedURLException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws URISyntaxException 
	 * @author Tanmaya Mahapatra

	 * 
	 * 
	 */
	public static FlowExecutionModel generateExecutionModel(
			FlowExecutionModel executionModel,
			String prefix,
			List<FlowElement> flowElements,
			List<FlowConnector> flowConnectors,
			FluxCompleteEnvironment environment) 
					throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, NoSuchMethodException, InvocationTargetException, URISyntaxException {
		FlowExecutionModel result=executionModel;
		for (FlowElement flowElement:flowElements) {
			try {
				switch (flowElement.getType().getType()) {
					case EXECUTOR:
						AbstractMainExecutor executorElement =buildActorExecutor(flowElement,prefix,environment);
						result.getExecutors().put(executorElement.getFlowId().toString(),executorElement);
						environment.showMessage(prefix+flowElement.getId(), "added class "+executorElement.getActorClassName());
						break;
					case SUBFLOW:
						FlowExecutionModel subFlowModel=buildSubFlowExecutor(flowElement,prefix+flowElement.getId()+".",environment);
						result.getExecutors().putAll(subFlowModel.getExecutors());
						result.getConnectors().addAll(subFlowModel.getConnectors());
						result.getInitialNodes().putAll(subFlowModel.getInitialNodes());
						result.getFinalNodes().putAll(subFlowModel.getFinalNodes());
						environment.showMessage(prefix+flowElement.getId(), "added subflow "+prefix+flowElement.getId());
				} 
			} catch (ClassNotFoundException e) {
				environment.getOutput().sendMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (InstantiationException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (IllegalAccessException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (MalformedURLException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (NoSuchMethodException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (SecurityException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (IllegalArgumentException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (InvocationTargetException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (URISyntaxException e) {
				environment.showMessage(prefix+flowElement.getId(), e.getMessage());
				e.printStackTrace();
				throw e;
			}
		}
		result.getConnectors().addAll(generateExecutionConnectors(flowConnectors,prefix));
		result=rebuildConnectors(result);
		return result;
	}
	

	private static AbstractMainExecutor buildActorExecutor(FlowElement flowElement,String prefix,FluxCompleteEnvironment environment) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = flowElement.getType().getClassName();
		Class<?> clazz;
		clazz = Class.forName(className);
		AbstractMainExecutor executorElement =(AbstractMainExecutor) clazz.newInstance();
		executorElement.setFlowId(prefix+flowElement.getId().toString());
		executorElement.setFluxEnvironment(environment);
		executorElement.setPropertyValues(flowElement.getPropertyValues());
		return executorElement;
	}
	
	
	/**
	 * Generate flow Elements for SubFlow at the same level of main Flow adding one entry and one end point if they don't exist
	 * @param flowElement
	 * @param prefix
	 * 		identifier of parent node . should be not empty and end with "."
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws URISyntaxException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws MalformedURLException 
	 */
	private static FlowExecutionModel buildSubFlowExecutor(FlowElement flowElement,String prefix,FluxCompleteEnvironment environment) 
			throws ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				MalformedURLException, 
				NoSuchMethodException, 
				SecurityException, 
				IllegalArgumentException, 
				InvocationTargetException, 
				URISyntaxException {
		assert prefix!=null && prefix.length()>1 && prefix.substring(prefix.length()-1).equalsIgnoreCase(".");
		
		// this activity should be the activity in job
		FlowActivity activity=flowElement.getSubFlow().getActivity();
		
		
		
		FlowActivity dummyActivity=
				new FlowActivity(
						activity.getId(),
						activity.getIndex(),
						activity.getName(),
						new ArrayList<FlowElement>(activity.getElements()),
						new ArrayList<FlowConnector>(activity.getConnectors()));
		dummyActivity=enforceFinalNode(dummyActivity);
		dummyActivity=enforceStartNode(dummyActivity,
				Integer.parseInt(flowElement.getPropertyValues()[ToolBaseProperties.CONCURRENCY_INDEX]));

		Long initialNode=findClassNode(dummyActivity,INITIAL_FLOW_NODE_CLASS_NAME);
		Long finalNode=findClassNode(dummyActivity,FINAL_FLOW_NODE_CLASS_NAME);
		
		

		FlowExecutionModel subFlowModel=
				generateExecutionModel(new FlowExecutionModel(),prefix,dummyActivity.getElements(),dummyActivity.getConnectors(),environment);
		subFlowModel= addNodeTranslationItems(subFlowModel,initialNode,finalNode,prefix);
		
		return subFlowModel;
	}
	
	
	
	
	

	/** 
	 * Generate execution connectors with ids based on execution environment (taking into account prefixes for subflows element identifiers)
	 * @param connectors
	 * @param prefix
	 * @return
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static List<FlowExecutionConnector> generateExecutionConnectors(List<FlowConnector> connectors, String prefix) {
		List<FlowExecutionConnector> result =new ArrayList<FlowExecutionConnector>();
		for (FlowConnector connector:connectors) {
			result.add(new FlowExecutionConnector(connector,prefix));
		}
		return result;
	}

	/**
	 * Make connectors point to and from node elements (not subflow elements)
	 * @param executionModel
	 * @return
	 * 
	 *  @author Tanmaya Mahapatra
	 */
	private static FlowExecutionModel rebuildConnectors(FlowExecutionModel executionModel) {
		FlowExecutionModel result=executionModel;
		// normalize initial map
		result.setInitialNodes(normalizeTranslation(executionModel.getInitialNodes()));
		result.setFinalNodes(normalizeTranslation(executionModel.getFinalNodes()));
		Map<String,String> initialNodesMap=result.getInitialNodes();
		Map<String,String> finalNodesMap=result.getFinalNodes();
		// replace connectors for deepest nodes
		for (FlowExecutionConnector connector:result.getConnectors()) {
			if (connector.getSourceIndex()>=0) {
				if (finalNodesMap.containsKey(connector.getSourceExecutionId())) {
					connector.setSourceExecutionId(finalNodesMap.get(connector.getSourceExecutionId()));
				}
			} else { // is async connector == -1
				if (initialNodesMap.containsKey(connector.getSourceExecutionId())) {
					connector.setSourceExecutionId(initialNodesMap.get(connector.getSourceExecutionId()));
				}
			}
			if (initialNodesMap.containsKey(connector.getTargetExecutionId())) {
				connector.setTargetExecutionId(initialNodesMap.get(connector.getTargetExecutionId()));
			}
		}
		return result;
	}
	
	
	/**
	 * Make sure activity has a start node for subflow
	 * If it does not have start node the method add one flowElement start connected to all no predecessor input connections 
	 * @param activity
	 * 	The changed activity with the added start flowElement if needed
	 * @return
	 * @throws URISyntaxException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws MalformedURLException 
	 */
	private static FlowActivity enforceStartNode(FlowActivity activity,int concurrency) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, URISyntaxException {
		FlowActivity changedActivity=activity;
		Long initialNode=findClassNode(changedActivity,INITIAL_FLOW_NODE_CLASS_NAME);
		if (initialNode==null) {
			Map<Long,FlowElement> noPredecessorNodes=getNoPredecessorNodes(changedActivity);
			FlowElement initialFlowElement=
					new FlowElement(Long.valueOf(changedActivity.getElements().size()+1),
							"Dummy Start",
							new FlowElementType(null,null,INITIAL_FLOW_NODE_CLASS_NAME),
							-1,-1,-1,-1,
							1,1,null,concurrency,null,null);
			changedActivity.getElements().add(initialFlowElement);
			changedActivity=addDummyConnectorsFromStart(changedActivity,initialFlowElement,noPredecessorNodes);
			
		}
		return changedActivity;
	}
	

	/**
	 * Make sure an activity has a final node to define the final point of a subflow
	 * If it does not have final node the method add one final flowElement connected to all output connectors with no targets 
	 * @param activity
	 * 	The changed activity with the added final flowElement if needed
	 * @return
	 * @throws URISyntaxException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws MalformedURLException 
	 */
	private static FlowActivity enforceFinalNode(FlowActivity activity) 
			throws MalformedURLException, 
				ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				NoSuchMethodException, 
				SecurityException, 
				IllegalArgumentException, 
				InvocationTargetException, 
				URISyntaxException {
		FlowActivity changedActivity=activity;
		Long finalNodeId=findClassNode(changedActivity,FINAL_FLOW_NODE_CLASS_NAME);
		if (finalNodeId==null) {
			Map<AbstractMap.SimpleEntry<Long, Integer>,FlowElement> noDirectionNodes=getNoDirectionNodes(changedActivity);
			FlowElement finalFlowElement=
					new FlowElement(Long.valueOf(changedActivity.getElements().size()+1),
							"Dummy Final",
							new FlowElementType(null,null,FINAL_FLOW_NODE_CLASS_NAME),
							-1,-1,-1,-1,
							1,1,null,1,null,null);
			changedActivity.getElements().add(finalFlowElement);
			changedActivity=addDummyConnectorsToFinal(changedActivity,finalFlowElement,noDirectionNodes);
		}
		return changedActivity;
	}
	
	
	
	/**
	 * Detects one element that is implemented using the given className
	 *  
	 * @param activity
	 * @param className
	 * @return
	 * 
	 * 	Returns the id of the element or null if it is not found
 	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static Long findClassNode(FlowActivity activity,String className) {
		Long flowElementId=null;
		for (FlowElement flowElement:activity.getElements()) {
			if (flowElement.getType().getClassName().equals(className)) {
				flowElementId=flowElement.getId();
			}
		}
		return flowElementId;
	}

	
	/**
	 * Get a set of ids of nodes having no predecessors
	 * @param activity
	 * @return
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static Map<Long,FlowElement> getNoPredecessorNodes(FlowActivity activity) {
		Map<Long,FlowElement> noPredecessors=new HashMap<Long,FlowElement>();
		for (FlowElement element:activity.getElements()) {
			noPredecessors.put(element.getId(),element);
		}
		for (FlowConnector connector:activity.getConnectors())  {
			if (noPredecessors.containsKey(connector.getTargetId())) {
				noPredecessors.remove(connector.getTargetId());
			}
		}
		return noPredecessors;
	}
	
	
	
	


	
	/**
	 * Refactor the translation maps of nodes to subflow nodes to deepest node in each case
	 * @param translation
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static Map<String, String> normalizeTranslation(Map<String, String> translation) {
		
		for (Map.Entry<String, String> entry:translation.entrySet()) {
			String deepestString=getDeepest(translation,entry.getValue());
		}
		return translation;
	}

	private static String getDeepest(Map<String, String> translation, String value) {
		String result=value;
		if (translation.containsKey(value)) {
			result=getDeepest(translation,translation.get(value));
		}
		return result;
	}

	
	/**
	 * Add translation keys to map links to and from nodes on subflows to the start and final node of each subflow
	 * 
	 * 
	 * @param subFlowModel
	 * @param initialNode
	 * @param finalNode
	 * @param prefix
	 * 		identifier of the parent node . should never be empty
	 * @return
	 */
	
	private static FlowExecutionModel addNodeTranslationItems(FlowExecutionModel subFlowModel, Long initialNode,
			Long finalNode, String prefix) {
		assert prefix!=null && prefix.length()>1;
		FlowExecutionModel changedModel=subFlowModel;
		// Gets the prefix with no end point - It's the subFlow id
		String nodeReferenceId=prefix.substring(0,prefix.length()-1);
		changedModel.getInitialNodes().put(nodeReferenceId, prefix+initialNode);
		changedModel.getFinalNodes().put(nodeReferenceId,prefix+finalNode);
		return changedModel;
	}



	/**
	 * 
	 * @param activity
	 * @return
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static Map<SimpleEntry<Long, Integer>, FlowElement> getNoDirectionNodes(FlowActivity activity) {
		// populate map
		Map<AbstractMap.SimpleEntry<Long,Integer>,FlowElement> freeOutputConnections=new HashMap<AbstractMap.SimpleEntry<Long, Integer>,FlowElement>();
		for (FlowElement element:activity.getElements()) {
			if (element.getAsyncInterface()) {
				freeOutputConnections.put(new AbstractMap.SimpleEntry<Long, Integer>(element.getId(), new Integer(0)),element);
			}
			for (int i=1;i<=element.getOutputInterfaces();i++) {
				freeOutputConnections.put(new AbstractMap.SimpleEntry<Long, Integer>(element.getId(), new Integer(i)),element);
			}
		}
		for (FlowConnector connector:activity.getConnectors()) {
			AbstractMap.SimpleEntry<Long, Integer> newKey=new AbstractMap.SimpleEntry<Long,Integer>(connector.getSourceElement().getId(),new Integer(connector.getSourceIndex()));
			if (freeOutputConnections.containsKey(newKey)) {
				freeOutputConnections.remove(newKey);
			}
		}
		return freeOutputConnections;
	}

	
	/**
	 * Add connections from Start node to all node that does not have predecessors
	 * 
	 * 
	 * 
	 * @param activity
	 * The activity
	 * @param initialFlowElement
	 * The Start Node
	 * @param noPredecessorNodes
	 * List of no predecessor nodes
	 * 
	 * @return
	 * 	The changed activity
	 * 
	 * 
	 * author Tanmaya Mahapatra
	 */
	private static FlowActivity addDummyConnectorsFromStart(
			FlowActivity activity, 
			FlowElement initialFlowElement,
			Map<Long,FlowElement> noPredecessorNodes) {
		FlowActivity activityWithNewConnectors=activity;
		for (Map.Entry<Long, FlowElement> elementEntry:noPredecessorNodes.entrySet()) {
			activityWithNewConnectors.getConnectors().add(
					new FlowConnector(
							new Long(activity.getConnectors().size()+1),
							initialFlowElement,1,
							elementEntry.getValue(),1));
		}
		return activityWithNewConnectors;
	}

	
	/**
     *
	 * 
	 * Add connections from nodes / connectors with no target to Final node 
	 * 
	 * @param activity
	 * @param finalFlowElement
	 * @param noDirectionNodes
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private static FlowActivity addDummyConnectorsToFinal(FlowActivity activity, FlowElement finalFlowElement,
			Map<SimpleEntry<Long, Integer>, FlowElement> noDirectionNodes) {
		FlowActivity changedActivity=activity;
		for (Map.Entry<SimpleEntry<Long, Integer>, FlowElement> source:noDirectionNodes.entrySet()) {
			changedActivity.getConnectors().add(new FlowConnector(
						new Long(activity.getConnectors().size()+1),
						source.getValue(),source.getKey().getValue(),
						finalFlowElement,1
					));
		}
		return changedActivity;
	}
	
	
	
	
	
}
