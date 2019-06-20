

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

package de.tum.in.aflux.tools.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.SmallestMailboxPool;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import scala.concurrent.duration.Duration;
import de.tum.in.aflux.flux_engine.FluxExtension;

/**
 * 
 * Class used by the runner to manage execute elements.
 * this class contains the information for a running process based in a tool and an actor
 * 
 * @author Tanmaya Mahapatra
 *
 */
public abstract class AbstractMainExecutor extends AbstractMainBaseTool{
	private String flowId;
	private List<Object> inputs;
	private List<Object> outputs;
	private boolean finished=false;

	
	/**
	 * akka actor for akka implementation
	 */
	

	/**
	 * actor instance when it is running
	 */
	private ActorRef actorInstance;
	
	
	/**
	 * actor name in akka context
	 */
	private String actorInstanceName;
	
	
	/**
	 * Context on which actor is running
	 */
	private ActorSystem actorSystem;
	
	
	/**
	 * Unique name in the world (think that tools can be added by user)
	 */
	private String identifier;
	
	/**
	 * names and initial values of the editable properties in order
	 */
	private String[] propertyValues;
	
	
	
	// Global Elements
	private FluxEnvironment fluxEnvironment;
	
	
	/**
	 * 
	 * @param name
	 * @param actorClassName
	 * @param className
	 * @param inputInterfaces
	 * @param outputInterfaces
	 * @param launchedBy
	 * @param async
	 * @param properties
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public AbstractMainExecutor(
			String name,
			String actorClassName, 
			String className,
			int inputInterfaces,
			int outputInterfaces,
			NodeLaunchType launchedBy,
			Boolean async, 
			ToolProperty[] properties) {
		this.setActorClassName(actorClassName);
		if (actorClassName==null) {
			// is subflow
			this.setToolImplementation(new ToolImplementation(null,null));
		} else {
			// is executor
			this.setToolImplementation(new ToolImplementation(null,null,className));
		}
		this.identifier=className;
		this.setName(name);
		this.inputs=new ArrayList<Object>(inputInterfaces); 
		for (int i=0;i<inputInterfaces;i++) {
			inputs.add(null);
		}
		this.outputs=new ArrayList<Object>(outputInterfaces);
		for (int i=0;i<outputInterfaces;i++) {
			outputs.add(null);
		}
		
		
		
		String color="#90CAF9";
		
		this.setStyle(new ToolStyle("",color));
		this.setInputInterfaces(inputInterfaces);
		this.setOutputInterfaces(outputInterfaces);
		this.finished=false;
		this.setLaunchedBy(launchedBy);
		this.setAsyncInterface(async);
		ToolProperty[] baseProperties=ToolBaseProperties.generateToolProperties(name, 120, color);
		ToolProperty[] elementProperties=new ToolProperty[0];
		if (properties!=null) {
			elementProperties=properties;
		}
		ToolProperty[] finalProperties=new ToolProperty[baseProperties.length+elementProperties.length];
		for (int i=0;i<baseProperties.length;i++) {
			finalProperties[i]=baseProperties[i];
		}
		for (int i=0;i<elementProperties.length;i++) {
			finalProperties[i+baseProperties.length]=elementProperties[i];
		}
		
		this.setProperties(finalProperties);
	}

	/**
	 * Includes semanticsConditions as parameter in case semantics are to be defined.
	 * @param name
	 * @param actorClassName
	 * @param className
	 * @param inputInterfaces
	 * @param outputInterfaces
	 * @param launchedBy
	 * @param async
	 * @param properties
	 * @param semanticsConditions
	 *
	 * @author Federico Fernández
	 */
	public AbstractMainExecutor (
			String name,
			String actorClassName,
			String className,
			int inputInterfaces,
			int outputInterfaces,
			NodeLaunchType launchedBy,
			Boolean async,
			ToolProperty[] properties,
			ToolSemanticsCondition[] semanticsConditions) {
		this(name, actorClassName, className, inputInterfaces, outputInterfaces, launchedBy, async, properties);
		this.setSemanticsConditions(semanticsConditions);
	}
	

	private Map<String,String> generatePropertiesMap(ToolProperty[] properties,String values[]) {
		Map<String,String> result=new HashMap<String,String>();
		if (values!=null) {
			for (int i=0;i<values.length;i++) {
				result.put(properties[i].name,values[i]);
			}
		}
		return result;
	}

	public void instantiate(
			ActorSystem actorSystem,
			FluxExtension springExtension,
			String instanceName, 
			FluxRunner fluxRunner) throws ClassNotFoundException {
		this.actorSystem=actorSystem;
		this.actorInstanceName=instanceName;
		System.out.println("AbstractMainExecutor.instantiate()"+this.actorInstanceName);
		int propertiesSize=this.getProperties()==null?0:this.getProperties().length;
		int valuesSize=this.getPropertyValues()==null?0:this.getPropertyValues().length;
		if (propertiesSize!=valuesSize) {
			System.out.println("May be there is an outdated tool in frontend:"+instanceName);
		}
		assert(propertiesSize==valuesSize);
		/*
        this.actorInstance = 
        		this.actorSystem.actorOf(
        				springExtension.props(
        						this.getActorClassName(),
        						this.flowId,
        						this.fluxEnvironment,
        						fluxRunner,
        						generatePropertiesMap(
        								this.getProperties(),
        								this.getPropertyValues())), 
        								this.actorInstanceName);
*/
		
		int concurrency=Integer.parseInt(this.getPropertyValues()[ToolBaseProperties.CONCURRENCY_INDEX]);
		if (concurrency<=0) {
			concurrency=1;
		}
        this.actorInstance = 
        		this.actorSystem.actorOf(new SmallestMailboxPool(concurrency).props(springExtension.props(
						this.getActorClassName(),
						this.flowId,
						this.fluxEnvironment,
						fluxRunner,
						generatePropertiesMap(
								this.getProperties(),
								this.getPropertyValues()))),this.actorInstanceName);
        		
        
	}
	
	
	public void start() throws Exception {
		send(new Object(),null,0);
	}

	private class DummyMessage {};
	
	public void send(Object message, AbstractMainExecutor sender,int duration) throws Exception {
		Object messageToSend=message;
		if (messageToSend==null) {
			messageToSend=new DummyMessage();
		}
		
		try {
			System.out.println("AbstractMainExecutor.send()"+this.getFlowId());
			ActorRef mySender;
			if (sender== null) {
				System.out.println("AbstractMainExecutor.send(nosender)");
				mySender=ActorRef.noSender();
			} else {
				System.out.println("AbstractMainExecutor.send(sender)");
				mySender=sender.actorInstance;
			}
			this.sendScheduled(messageToSend,mySender,duration);
		} catch (Exception e) {
			e.printStackTrace();
			this.fluxEnvironment.getOutput().sendMessage(this.getFlowId(), e.getMessage());
			throw e;
		} finally {
			this.finished=true;
		}
	}

	
	private void sendScheduled(Object message,ActorRef sender,int duration) {
		System.out.println(this.actorInstance);
        if (duration==0) {
            this.actorInstance.tell(message, sender);
        } else {
        	this.actorSystem.scheduler().scheduleOnce(
        			Duration.create(duration, TimeUnit.MILLISECONDS), 
        			this.actorInstance, 
        			message, 
        			this.actorSystem.dispatcher(), 
        			sender);
        }
        
	}
	


	
	protected void setOutput(int index,Object o) {
		this.outputs.set(index, o);
	}
	
	public List<Object> getInputs() {
		return inputs;
	}

	public void setInputs(List<Object> inputs) {
		this.inputs = inputs;
	}

	public List<Object> getOutputs() {
		return outputs;
	}

	public void setOutputs(List<Object> outputs) {
		this.outputs = outputs;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public FluxEnvironment getEnvironment() {
		return fluxEnvironment;
	}

	protected void setEnvironment(FluxEnvironment fluxEnvironment) {
		this.fluxEnvironment = fluxEnvironment;
	}




	public boolean isFinished() {
		return finished;
	}




	public void setFinished(boolean finished) {
		this.finished = finished;
	}




	public boolean isReadyToStart() {
		boolean result=true;
		for (int i=0;i<this.getInputInterfaces();i++) {
			if (this.inputs.get(i)==null) {
				result=false;
			}
		}
		return result;
	}

	public String getIdentifier() {
		return identifier;
	}





	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}






	public FluxEnvironment getFluxEnvironment() {
		return fluxEnvironment;
	}





	public void setFluxEnvironment(FluxEnvironment fluxEnvironment) {
		this.fluxEnvironment = fluxEnvironment;
	}

	
	


	public String[] getPropertyValues() {
		return propertyValues;
	}


	public void setPropertyValues(String[] propertyValues) {
		this.propertyValues = propertyValues;
	}


	public ActorRef getActorInstance() {
		return actorInstance;
	}


	public void setActorInstance(ActorRef actorInstance) {
		this.actorInstance = actorInstance;
	}


	public String getActorInstanceName() {
		return actorInstanceName;
	}


	public void setActorInstanceName(String actorInstanceName) {
		this.actorInstanceName = actorInstanceName;
	}


	public ActorSystem getActorSystem() {
		return actorSystem;
	}


	public void setActorSystem(ActorSystem actorSystem) {
		this.actorSystem = actorSystem;
	}


	@Override
	public String toString() {
		return String.format(
				"AbstractMainExecutor [flowId=%s, inputs=%s, outputs=%s, finished=%s, actorInstance=%s, actorInstanceName=%s, actorSystem=%s, identifier=%s, propertyValues=%s, fluxEnvironment=%s]",
				flowId, inputs, outputs, finished, actorInstance, actorInstanceName, actorSystem, identifier,
				Arrays.toString(propertyValues), fluxEnvironment);
	}

}
