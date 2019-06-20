

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

package de.tum.in.aflux.component.mqtt.actor;

import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;


import de.tum.in.aflux.component.mqtt.MQTTConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.mqtt.model.MqttReconnectSignal;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class MQTTSubscriberActor extends AbstractAFluxActor {
	
	public MQTTSubscriberActor(String fluxId, 
			FluxEnvironment fluxEnvironment, 
			FluxRunner fluxRunner,
			Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,0);
	}

	
	public MQTTSubscriberActor() {
		this("-1",null,null,null);
	}
	
	
	@Override
	protected void runCore(Object message) throws Exception {
		if (message instanceof MqttMessage) {
			System.out.println("Subscriber: to set output");
			this.setOutput(1, message.toString());
			System.out.println("Subscriber: set output");
		} else if (message instanceof MqttReconnectSignal) {
			System.out.println("mqtt:actor:reconnectSubscription");
			this.mqttReconnectSubscription();
		} else {
			MqttConnectOptions options= new MqttConnectOptions();
			String userName=this.getProperty(MQTTConstants.USERNAME);
			if (userName!=null && userName.length()>0) {
				String password=this.getProperty(MQTTConstants.PASSWORD);
				options.setUserName(userName);
				options.setPassword(password.toCharArray());
			}
			String cleanSessionString=this.getProperty(MQTTConstants.CLEAN_SESSION);
			if (cleanSessionString!=null) {
				boolean cleanSession=cleanSessionString.toUpperCase().equals("TRUE");
				options.setCleanSession(cleanSession);
			}
			String timeoutString=this.getProperty(MQTTConstants.CONNECTION_TIMEOUT_DEFAULT);
			if (timeoutString!=null) {
				int timeout=Integer.parseInt(timeoutString);
				options.setConnectionTimeout(timeout);
			}
			
			String keepAliveString=this.getProperty(MQTTConstants.KEEP_ALIVE_INTERVAL);
			if (keepAliveString!=null) {
				int keepAlive=Integer.parseInt(keepAliveString);
				options.setKeepAliveInterval(keepAlive);
			}
			
			int times=Integer.parseInt(this.getProperty(MQTTConstants.TIMES));
			int timeLimit=Integer.parseInt(this.getProperty(MQTTConstants.TIME_LIMIT));
			
			String brokerUrl=this.getProperty(MQTTConstants.HOST);
			String clientId = this.getProperty(MQTTConstants.CLIENT_ID);
			String topicString = this.getProperty(MQTTConstants.TOPIC);
			int reconnectionInterval=120;
			String reconnectionIntervalString=this.getProperty(MQTTConstants.RECONNECTION_INTERVAL);
			if (reconnectionIntervalString!=null) {
				reconnectionInterval=Integer.parseInt(reconnectionIntervalString);
			}
			if (reconnectionInterval==0) {
				reconnectionInterval=120;
			}
			
			this.addMqttSubscription(brokerUrl, clientId,options,topicString,times,timeLimit,reconnectionInterval);
			this.mqttReconnectSubscription();
			
		}
		
		
		
	}




}
