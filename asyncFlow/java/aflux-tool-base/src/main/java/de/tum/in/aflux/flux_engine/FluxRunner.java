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

package de.tum.in.aflux.flux_engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;

/**
 * method of the runner implemtation that actors can use 
 * @author Tanmaya Mahapatra
 *
 */
public interface FluxRunner {
	
	/**
	 * method to send messages to other objects. 
	 * This method is called each time an actor calls setOuput(index,message) or initially for async connectors
	 * It sends the message to all target connected elements
	 * 
	 * @param fluxId
	 * @param outputIndex
	 * @param value
	 * @throws Exception
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void broadcast(String fluxId, int outputIndex, Object value) throws Exception;

	
	
	/**
	 * method to send messages to other objects. 
	 * This method is called each time an actor calls setOuput(index,message) or initially for async connectors on a scheduled bassis 
	 * It sends the message to all target connected elements
	 * 
	 * @param fluxId
	 * @param outputIndex
	 * @param value
	 * @param duration
	 *   	duration expressed in milliseconds
	 * @throws Exception
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void broadcast(String fluxId, int outputIndex, Object value,int duration) throws Exception;
	
	
	/**
	 * 
	 * method to notify finalization of execution of an element
	 * Actor implementations does not need to call it.
	 * It is called internally
	 * 
	 * @param fluxId
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void finish(String fluxId);
	public void send(String source, String target, Object value, int duration) throws Exception;
	public void send(AbstractMainExecutor source,AbstractMainExecutor target,Object value,int duration) throws Exception;



	
	// mqtt methods
	public void addMqttPublisher(String fluxId,String host,String clientId, MqttConnectOptions options,String topic,int times,int timeLimit) throws MqttException;
	public void removeMqttPublisher(String fluxId) throws MqttException;
	
	public void addMqttSubscription(String fluxId,String host, String clientId, MqttConnectOptions options, String topic,int times,int timeLimit, int reconnectionInterval) throws MqttException;
	public void removeMqttSubscription(String fluxId) throws MqttException;

	public void mqttPublish(String fluxId, String message) throws MqttException;

	public void mqttReconnectSubscription(String fluxId) throws Exception;


	// kafka methods
	public void addKafkaProducer(String fluxId,String host,String clientId, ProducerConfig options,String topic,int times,int timeLimit) throws KafkaException;
	public void removeKafkaProducer(String fluxId) throws KafkaException;
	
	public void addKafkaConsumer(String fluxId,KafkaConsumer<String,String> consumer,String topic,int reconnectionInterval,String kafkaAfluxDataClass) throws KafkaException;
	public void removeKafkaConsumer(String fluxId) throws KafkaException;

	public void kafkaProduce(String fluxId, String message) throws KafkaException;

	public void kafkaReconnectConsumer(String fluxId) throws Exception;



	boolean isKafkaConsumerRegistered(String fluxId);



	void kafkaConsume(String fluxId, long pollTimeLimit) throws Exception;



	public void setVariable(String fluxId, String scope, String key, Object value);



	public Object getVariable(String fluxId, String scope, String key);



	public void removeVariable(String fluxId, String scope, String key);



	public String getProposedJobFolder();



	public void appendToFile(String mainFolder, String jobFolder, String fileName, String value) throws IOException;



	public void generateChart(String content) throws FileNotFoundException;




	public String[] readTextFile(String mainFolder, String jobFolder, String fileName) throws IOException;



	void generateChart(String mainFolder, String jobFolder, String fileName, String content)
			throws FileNotFoundException;







}
