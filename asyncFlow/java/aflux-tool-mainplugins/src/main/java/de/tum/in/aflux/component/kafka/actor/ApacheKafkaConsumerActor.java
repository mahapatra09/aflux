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

package de.tum.in.aflux.component.kafka.actor;

import java.util.Map;
import java.util.Properties;

import de.tum.in.aflux.component.kafka.ApacheKafkaConsumerConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.kafka.model.KafkaAfluxReconnectSignal;
import de.tum.in.aflux.kafka.model.KafkaAfluxValue;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static de.tum.in.aflux.component.kafka.ApacheKafkaConsumer.serviceProperties;

public class ApacheKafkaConsumerActor extends AbstractAFluxActor
{
    public ApacheKafkaConsumerActor(String fluxId,
                                    FluxEnvironment fluxEnvironment,
                                    FluxRunner fluxRunner,
                                    Map<String,String> properties)
    {
        super(fluxId, fluxEnvironment, fluxRunner,properties,0);
    }


    public ApacheKafkaConsumerActor()
    {
        this("-1",null,null,null);
    }

    @Override
    protected void runCore(Object message) throws Exception {
    	if (message!=null) {
    		System.out.println("message !=null =:"+message);
		if (message instanceof KafkaAfluxValue) {
			// this message comes from a KafkaConsumer.poll(...),
			// based on a previous setted subscriber
    			System.out.println("kafka-consumer-actor: to set output");
    			Object stringValue=((KafkaAfluxValue) message).getValue();
    			if (stringValue==null) {
        			System.out.println("kafka-consumer-actor: value=:null");
    			} else {
        			System.out.println("kafka-consumer-actor: value=:"+stringValue.toString());
    			}
    			this.setOutput(1, ((KafkaAfluxValue) message).getValue());
    			System.out.println("kafka-consumer-actor: set output");
		} else if (message instanceof KafkaAfluxReconnectSignal) {
			// not used by now. check if this is needed
    			System.out.println("kafka-consumer-actor:reconnectSubscription");
			this.kafkaReconnectSubscription();
		} else {
    			System.out.println("kafka-consumer-actor: create Subscriber if needed");
    			System.out.println("kafka-consumer-actor: class:"+message.getClass().getCanonicalName());
			long pollTimeLimit=new Long(this.getProperty(ApacheKafkaConsumerConstants.POLL_TIME_LIMIT));
			if (pollTimeLimit==0) {
				pollTimeLimit=1000;
			}
			if (!this.isKafkaConsumerRegistered()) {
    				System.out.println("kafka-consumer-actor: create Subscriber");
				// Initial running event - creates the subscriber

		    	String topic="";
    		    	String factoryClass="";
            Properties props = new Properties();

            for (int i = 0; i < serviceProperties.length; i++) {
		        	String currentName=serviceProperties[i].getName();
		        	if (currentName.equals(ApacheKafkaConsumerConstants.TOPIC)) {
		        		topic=this.getProperty(currentName);
    		        	} else if (currentName.equals(ApacheKafkaConsumerConstants.FACTORY_CLASS)) {
    		        		factoryClass=this.getProperty(currentName);
		        	} else if (currentName.equals(ApacheKafkaConsumerConstants.COLOR) ||currentName.equals(ApacheKafkaConsumerConstants.POLL_TIME_LIMIT) ) {
		        	} else {
		                props.put(currentName, this.getProperty(currentName) );
		        	}
            }
		        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    				this.addKafkaConsumer(consumer,topic,100,factoryClass);
			} 
// 			this.kafkaReconnectSubscription();
    			System.out.println("kafka-consumer-actor: kafkaConsume");
			this.kafkaConsume(pollTimeLimit);
    		}
			
		}
    }

		
		
		
}
