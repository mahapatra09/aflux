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

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

public class MqttAfluxSubscription implements MqttCallback {

	int times;
	boolean finishByTimes=false;
	FluxRunner runner; 
	AbstractMainExecutor executor;
	MqttClient client;
	Date createdOn;
	int timeLimit;
	boolean finished=false;
	MqttConnectOptions options=null;
	int reconnectionInterval = 120;
	
	public MqttAfluxSubscription(
			AbstractMainExecutor executor,
			FluxRunner runner,
			String host,
			String clientId,
			MqttConnectOptions options,
			String topic,
			int times,
			int timeLimit,
			int reconnectionInterval) throws MqttSecurityException, MqttException {
		this.executor=executor;
		this.runner=runner;
	    this.client=new MqttClient(host,clientId,null);	
	    this.client.setCallback(this);
	    this.options=options;
	    this.client.connect(options);
	    this.client.subscribe(topic);
	    this.createdOn=new Date();
	    this.times=times;
	    if (times>0) {
	    	this.finishByTimes=true;
	    }
	    this.reconnectionInterval=reconnectionInterval;
	}
	
	
	public void keepAliveConnection() throws MqttSecurityException, MqttException {
		System.out.println("mqtt:keepAliveConnection-----------------------------------");
		if (!finished) {
			if (!this.client.isConnected()) {
				this.client.connect(this.options);
			}
		}
	}
	
	
	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("mqtt:connectionLost-----------------------------------");
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("mqtt:deliveryComplete---");
		
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("mqtt:messageArrived----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		this.runner.send(this.executor,this.executor,message,0);
		if (finishByTimes) {
			this.times--;
			this.finished=this.times<=0;
			if (this.finished) {
				this.runner.removeMqttSubscription(this.executor.getFlowId());
			}
		}
	}

	public String getFluxId() {
		return executor.getFlowId();
	}

	public void disconnect() throws MqttException {
		System.out.println("mqtt:disconnect");
		if (this.client!=null) {
			this.client.disconnect();
			this.client.close();
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public void reconnect() throws MqttSecurityException, MqttException {
		System.out.println("mqtt:reconnect");
		if (!this.client.isConnected()) {
			System.out.println("mqtt:reconnecting mqtt----------------------------------------");
			this.client.connect(this.options);
		}
	}


	public int getReconnectionInterval() {
		return reconnectionInterval;
	}
	
	
	
}
