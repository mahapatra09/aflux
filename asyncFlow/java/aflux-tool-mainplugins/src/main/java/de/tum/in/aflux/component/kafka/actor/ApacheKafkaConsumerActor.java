

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
