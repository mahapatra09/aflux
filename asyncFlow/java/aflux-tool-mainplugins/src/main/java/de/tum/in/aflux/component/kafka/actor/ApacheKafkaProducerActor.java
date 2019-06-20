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
