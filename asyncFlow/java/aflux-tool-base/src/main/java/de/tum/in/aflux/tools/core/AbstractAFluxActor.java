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

package de.tum.in.aflux.tools.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import akka.actor.UntypedAbstractActor;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
/**
 * Base class to be extended to build actor
 * The main method actor sould implement is runCore(....)
 * 
 * @author Tanmaya Mahapatra
 *
 */
public abstract class AbstractAFluxActor extends UntypedAbstractActor {
	
	
	// Global Elements
	private FluxEnvironment environment;
	private FluxRunner runner;
	private String fluxId;
	private Map<String,String> properties;
	/**
	 * True if it can send async signals
	 */
	private Boolean asyncCapable;
	/**
	 * index of the output connector to set the duration
	 */
	private int durationOutputConnector;

	
	
	
	public AbstractAFluxActor(String fluxId,
			FluxEnvironment fluxEnvironment,
			FluxRunner fluxRunner,
			Map<String,String> properties,
			int durationOutputConnector) {
		this.fluxId=fluxId;
		this.runner=fluxRunner;
		this.environment = fluxEnvironment;
		this.properties=properties;
		this.durationOutputConnector=durationOutputConnector;
	}


	/**
	 * Main entry point on receiving a message from another actor or starting it
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		if (this.runner!=null) {
			this.runner.broadcast(this.fluxId, -1, message);
		}
		Long startedTime=System.currentTimeMillis();
		runCore(message);
		Long endTime=System.currentTimeMillis();
		Long duration=endTime-startedTime;
		if (this.durationOutputConnector>=1) {
			this.setOutput(this.durationOutputConnector, duration.toString()+" ms");
		}
		if (runner!=null) {
			this.runner.finish(this.fluxId);
		}
	}

	public void sendOutput(String message) {
		this.environment.showMessage(fluxId, message);
	}
	
	
	/**
	 * Method each plugin element actor should implement
	 * 
	 * Inside elements can be calles
	 * 	setOutput(index,message): to set the message to be sent to another actor
	 *  sendMessage(....) : to show information in the output console of the app
	 *  getEnvironment()... : to use shared resources of the execution runnning environment
	 *  getProperties() : to access customized data of the element setted in each instance
	 *  
	 * @param message
	 * @throws Exception
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	protected abstract void runCore(Object message) throws Exception;


	/**
	 * Set output to be scheduled
	 * @param index
	 * @param value
	 * @param duration
	 * @throws Exception
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void setOutput(int index, Object value,int duration) throws Exception {
		if (duration==0) {
			this.setOutput(index, value);
		} else {
			this.runner.broadcast(this.fluxId,index,value,duration);
		}
	}
	
	
	private void send(String source, String target, Object value,
			int duration) throws Exception {
		this.runner.send(source,target,value,duration);
	}

	
	
	public void setOutput(int index, Object value) throws Exception {
		this.runner.broadcast(this.fluxId,index,value);
	}

	/**
	 * Customizted setted values for each instance 
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public Map<String, String> getProperties() {
		return properties;
	}


	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}



	public Boolean getAsyncCapable() {
		return asyncCapable;
	}



	public void setAsyncCapable(Boolean asyncCapable) {
		this.asyncCapable = asyncCapable;
	}



	public String getFluxId() {
		return fluxId;
	}



	public void setFluxId(String fluxId) {
		this.fluxId = fluxId;
	}

	public FluxEnvironment getFluxEnvironment() {
		return this.environment;

	}
	
	
	
	
	public int getDurationOutputConnector() {
		return durationOutputConnector;
	}


	public void setDurationOutputConnector(int durationOutputConnector) {
		this.durationOutputConnector = durationOutputConnector;
	}


	public String getProperty(String key) throws Exception {
		String result=getProperties().get(key);
		if (result==null) {
			throw new Exception("Unexistent key on Actor: key="+key+" for "+fluxId+":"+getProperties().toString()+"\n"+
					"Program error: Actor is using getProperty on a key is not entered in Tool Class extending AbstractMainExecutor");
		} 
		return result;
	}
	
	// mqtt methods
	protected void addMqttSubscription(String brokerUrl, String clientId, MqttConnectOptions options,String topic,int times,int timeLimit, int reconnectionInterval) throws MqttException {
		this.runner.addMqttSubscription(this.getFluxId(),brokerUrl,clientId,options,topic,times,timeLimit,reconnectionInterval);
	}

	protected void addMqttPublisher(String brokerUrl, String clientId, MqttConnectOptions options,String topic,int times,int timeLimit) throws MqttException {
		this.runner.addMqttPublisher(this.getFluxId(),brokerUrl,clientId,options,topic,times,timeLimit);
	}
	
	
	protected void mqttPublish(String message) throws MqttException {
		this.runner.mqttPublish(this.getFluxId(),message);
	}

	protected void mqttReconnectSubscription() throws Exception {
		this.runner.mqttReconnectSubscription(this.getFluxId());
		
	}


	// kafka methods
	
	protected void addKafkaConsumer(KafkaConsumer consumer,String topic,int reconnectInterval, String kafkaAfluxDataClass) throws KafkaException {
		this.runner.addKafkaConsumer(this.getFluxId(),consumer,topic,reconnectInterval,kafkaAfluxDataClass);
	}

	protected void addKafkaProducer(String brokerUrl, String clientId, ProducerConfig options,String topic,int times,int timeLimit) throws MqttException {
		this.runner.addKafkaProducer(this.getFluxId(),brokerUrl,clientId,options,topic,times,timeLimit);
	}
	
	
	protected void kafkaProduce(String message) throws MqttException {
		this.runner.kafkaProduce(this.getFluxId(),message);
	}

	protected void kafkaReconnectConsumer() throws Exception {
		this.runner.kafkaReconnectConsumer(this.getFluxId());
	}

	protected void kafkaReconnectSubscription() {
		// TODO Auto-generated method stub
	}

	
	protected boolean isKafkaConsumerRegistered() {
		return this.runner.isKafkaConsumerRegistered(this.getFluxId());
	}
	protected void kafkaConsume(long pollTimeInterval) throws Exception {
		this.runner.kafkaConsume(this.getFluxId(),pollTimeInterval);
	}

	
	// environment variables management
	
	protected void setVariable(String scope,String key,Object value) {
		this.runner.setVariable(this.getFluxId(),scope,key,value);
	}
	
	protected Object getVariable(String scope,String key) {
		return this.runner.getVariable(this.getFluxId(),scope,key);
	};
	
	protected void removeVariable(String scope,String key) {
		this.runner.removeVariable(this.getFluxId(),scope,key);
	}
	
	protected String getProposedJobFolder() {
		return this.runner.getProposedJobFolder();
	}
	
	protected void appendToFile(String mainFolder, String jobFolder, String fileName, String value) throws IOException {
		this.runner.appendToFile(mainFolder,jobFolder,fileName,value);
	}

	public void generateChart(String content) throws FileNotFoundException {
		this.runner.generateChart(content);
	}

	protected void generateChart(String mainFolder, String jobFolder, String fileName, String graphResult) throws FileNotFoundException {
		this.runner.generateChart(mainFolder,jobFolder,fileName,graphResult);
	}
	
	
	public String[] readTextFile(String mainFolder, String jobFolder, String fileName) throws IOException {
		return this.runner.readTextFile(mainFolder,jobFolder,fileName);
	}
	
	
}
