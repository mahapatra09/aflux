
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

package de.tum.in.aflux.component.mqtt;

import de.tum.in.aflux.component.mqtt.actor.MQTTPublisherActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * 
 * 
 * First time creates the publisher and publishes the received data
 * Next times only publishes the received data
 * @author Tanmaya Mahapatra
 *
 */
public class MQTTPublisher extends AbstractMainExecutor{
	public static final String NAME="MQTT Publisher";

	static public ToolProperty[] connectionProperties={
				new ToolProperty(MQTTConstants.HOST,"tcp://broker.hivemq.com:1883",PropertyInputType.TEXT,null,"MQTT Broker host","",false),
				new ToolProperty(MQTTConstants.TOPIC,"",PropertyInputType.TEXT,null,"Topic name","",false),
				new ToolProperty(MQTTConstants.CLIENT_ID,"",PropertyInputType.TEXT,null,"client_id.","",false),
				new ToolProperty(MQTTConstants.USERNAME,"",PropertyInputType.TEXT,null,"credential usernam","",false),
				new ToolProperty(MQTTConstants.PASSWORD,"",PropertyInputType.TEXT,null,"credential password","",false),
				new ToolProperty(MQTTConstants.CLEAN_SESSION,"true",PropertyInputType.TEXT,null,"True if should be started a new session when defining the client connection","",false),
				new ToolProperty(MQTTConstants.CONNECTION_TIMEOUT_DEFAULT,"60",PropertyInputType.TEXT,null,"Connection timeout default in seconds","",false),
				new ToolProperty(MQTTConstants.KEEP_ALIVE_INTERVAL,"60",PropertyInputType.TEXT,null,"Keep alive interval in seconds","",false),
				new ToolProperty(MQTTConstants.TIMES,"0",PropertyInputType.TEXT,null,"number of publish to execute","",false),
				new ToolProperty(MQTTConstants.TIME_LIMIT,"0",PropertyInputType.TEXT,null,"Time limit in seconds","",false)};
	
				
	
	
	
	public MQTTPublisher() {
		super(NAME,
				MQTTPublisherActor.class.getCanonicalName(),
				MQTTPublisher.class.getName(),
				1, 
				0,
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor(MQTTConstants.COLOR);
	}

}
