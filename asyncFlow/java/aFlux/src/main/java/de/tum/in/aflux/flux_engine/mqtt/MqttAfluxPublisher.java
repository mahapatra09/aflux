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

package de.tum.in.aflux.flux_engine.mqtt;

import java.util.Date;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

public class MqttAfluxPublisher {
	int times;
	boolean finishByTimes=false;
	MqttClient client;
	AbstractMainExecutor executor;
	FluxRunner runner;
	Date createdOn;
	boolean finished=false;
	String topic;

	public MqttAfluxPublisher(
			AbstractMainExecutor executor,
			FluxRunner runner,
			String host,
			String clientId,
			MqttConnectOptions options,
			String topic,
			int times,
			int timeLimit) throws MqttSecurityException, MqttException {
		this.executor=executor;
		this.runner=runner;
	    this.client=new MqttClient(host,clientId,null);	
	    this.client.connect(options);
	    this.createdOn=new Date();
	    this.times=times;
	    if (times>0) {
	    	this.finishByTimes=true;
	    }
	    this.topic=topic;
	}
	
	
	
	public boolean publish(String message) throws MqttException {
		System.out.println("Publisher: got client");
		MqttTopic mqttTopic=this.client.getTopic(this.topic);
		System.out.println("Publisher: got topic");
		if (message!=null) {
			mqttTopic.publish(new MqttMessage(message.getBytes()));
			System.out.println("Publisher: publish");
		} else {
			System.out.println("Publisher: null message - not published");
			
		}
	
		if (finishByTimes) {
			this.times--;
			this.finished=this.times<=0;
			if (this.finished) {
				this.runner.removeMqttPublisher(this.executor.getFlowId());
			}
		}
		return !this.finished;
	}



	public void disconnect() throws MqttException {
		if (this.client!=null) {
			this.client.disconnect();
			this.client.close();
		}
		
	}
	
	
	
}
