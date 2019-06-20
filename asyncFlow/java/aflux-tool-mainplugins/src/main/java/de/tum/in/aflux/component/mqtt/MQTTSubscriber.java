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

package de.tum.in.aflux.component.mqtt;

import de.tum.in.aflux.component.mqtt.actor.MQTTSubscriberActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * 
 * 
 * First time creates the MQTT subscriber
 * Each time a message is received it will output the message for the following nodes
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class MQTTSubscriber extends AbstractMainExecutor{
	public static final String NAME="MQTT Subscriber";

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
				new ToolProperty(MQTTConstants.TIME_LIMIT,"0",PropertyInputType.TEXT,null,"Time limit in seconds","",false),
				new ToolProperty(MQTTConstants.RECONNECTION_INTERVAL,"120",PropertyInputType.TEXT,null,"Time to verify reconnection ","",false)};
	
	
	
	public MQTTSubscriber() {
		super(NAME,
				MQTTSubscriberActor.class.getCanonicalName(),
				MQTTSubscriber.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true,
				connectionProperties);
		this.setColor(MQTTConstants.COLOR);
	}

	
}
