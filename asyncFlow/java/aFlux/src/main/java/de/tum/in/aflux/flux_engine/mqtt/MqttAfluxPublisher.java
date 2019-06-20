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
