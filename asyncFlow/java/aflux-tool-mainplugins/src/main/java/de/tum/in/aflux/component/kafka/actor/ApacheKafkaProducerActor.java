

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

package de.tum.in.aflux.component.kafka.actor;

import java.util.Map;
import java.util.Properties;

import static de.tum.in.aflux.component.kafka.ApacheKafkaProducer.serviceProperties;

import de.tum.in.aflux.component.kafka.ApacheKafkaProducerConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;//import simple producer packages
import org.apache.kafka.clients.producer.ProducerRecord;

public class ApacheKafkaProducerActor extends AbstractAFluxActor
{
    public ApacheKafkaProducerActor(String fluxId,
                                    FluxEnvironment fluxEnvironment,
                                    FluxRunner fluxRunner,
                                    Map<String,String> properties)
    {
        super(fluxId, fluxEnvironment, fluxRunner,properties,0);
    }


    public ApacheKafkaProducerActor()
    {
        this("-1",null,null,null);
    }

    
    @Override
    protected void runCore(Object message) throws Exception
    {
        Properties props = new Properties();
        String topic="";
        for (int i = 0; i < serviceProperties.length; i++) {
        	String currentName=serviceProperties[i].getName();
        	if (currentName.equals(ApacheKafkaProducerConstants.TOPIC)) {
        		topic=this.getProperty(currentName);
        	} else if (currentName.equals(ApacheKafkaProducerConstants.COLOR)) {
        	} else {
                props.put(currentName, this.getProperty(currentName) );
        	}
        }

        String value=message.toString();
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        producer.send(new ProducerRecord<String, String>(topic,value));
        System.out.println("Kafka Message sent successfully");
        System.out.println("Topic:"+topic);
        System.out.println("ApacheKafkaProducerActor.Message value:"+value==null?"null":value);
        producer.close();
    }
}
