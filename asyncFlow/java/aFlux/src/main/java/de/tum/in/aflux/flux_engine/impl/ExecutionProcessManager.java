

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

package de.tum.in.aflux.flux_engine.impl;

import java.util.HashMap;
import java.util.Map;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

/**
 * Class that allows register the state of running processes
 * All actors notifies start and finish of execution to this class.
 * In each moment this class has the current ids and quantity of running processes
 * A flux (or activity) is considered to be finish if the count of processes registered in this class is empty
 * @author Tanmaya Mahapatra
 *
 */
public class ExecutionProcessManager {

	// mqtt 
	Map<String,Integer> executing;
	int mqttSubscriptions=0;
	
	// kafka 
	Map<String,Integer> executingKafka;
	int kafkaConsumers=0;
	
	public ExecutionProcessManager() {
		this.executing=new HashMap<String,Integer>();
		this.executingKafka=new HashMap<String,Integer>();
	}
	
	/**
	 * Flow is considered finished if there is no running processes
	 * This method shoud be consulted only after a flow has started
	 * @return true if has finished (there are no running processes) / false if there are running processes waiting to finish
	 */
	public  synchronized boolean hasFinished() {
		if  (this.executing.size()>0) {
			System.out.println("Executing --------------------------------------------------------------------------------------------------------");
			System.out.println(this.executing.toString());
			System.out.println(this.executingKafka.toString());
		}
		return this.executing.size()==0 && this.mqttSubscriptions<=0 && this.kafkaConsumers<=0; 
	}

	/**
	 * Adds information of the running process.
	 * It should be called each time an actor starts to run
	 * @param executor
	 */
	public void run(AbstractMainExecutor executor) {
		Integer executingProcesses=executing.get(executor.getFlowId());
		if (executingProcesses!=null) {
			executing.put(executor.getFlowId(),executingProcesses+1);
		} else {
			executing.put(executor.getFlowId(),1);
		}
		System.out.println("Run --------------------------------------------------------------------------------------------------------");
		System.out.println(this.executing.toString());
	}

	/**
	 * Notifies the end of a running process
	 * Each time a running process finish the quantity of running processes is decreased and it is removed if the quantity of processes reaches 0 
	 * @param fluxId
	 */
	public synchronized void finish(String fluxId) {
		Integer executions=this.executing.get(fluxId);
		if (executions!=null) {
			executions--;
			if (executions<=0) {
				this.executing.remove(fluxId);
			} else {
				this.executing.put(fluxId, executions);
			}
		}
		System.out.println("Finishing -----fluxId "+fluxId+"---------------------------------------------------------------------------------------------------");
		System.out.println(this.executing.toString());
		
	}

	public void addMqttSubscription() {
		this.mqttSubscriptions++;
	}

	public void removeMqttSubscription() {
		this.mqttSubscriptions--;
	}

	public void addKafkaConsumer() {
		this.kafkaConsumers++;
	}

	public void removeKafkaConsumer() {
		this.kafkaConsumers--;
	}
	
	
}
