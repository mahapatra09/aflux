

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

package de.tum.in.aflux.flux_engine.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

public class KafkaAfluxProducer {

	String bootstrapServers=""; // example: localhost:9092
	String topic=""; // 
	
	
	public KafkaAfluxProducer(AbstractMainExecutor executor, FluxRunner fluxRunnerImpl, String bootstrapServers,
			String clientId, ProducerConfig options, String topic,  int times, int timeLimit) {
		this.bootstrapServers=bootstrapServers;
		this.topic=topic;
		// TODO Auto-generated constructor stub
	}

	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	public void produce(String value) {
	      
	      // create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers",this.bootstrapServers);
	      
	      //Set acknowledgements for producer requests.      
	      props.put("acks", "all");
	      
	      //If the request fails, the producer can automatically retry,
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serializa-tion.StringSerializer");
	         
	      props.put("value.serializer", 
	         "org.apache.kafka.common.serializa-tion.StringSerializer");
	      
	      Producer<String, String> producer = new KafkaProducer
	         <String, String>(props);
          producer.send(new ProducerRecord<String, String>(this.topic, 
	 	            value));
          System.out.println("Message sent successfully");
          producer.close();
	            
	   }		
	}


