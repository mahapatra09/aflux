

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

package de.tum.in.aflux.flux_engine.kafka;

import java.util.Arrays;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.kafka.model.KafkaAfluxValue;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

public class KafkaAfluxConsumer {
	
	KafkaConsumer consumer;
	AbstractMainExecutor executor;
	FluxRunner runner;
	String factoryClass;
	/**
	 * reconnectionInterval in milliseconds
	 */
	int reconnectionInterval;
	

	public KafkaAfluxConsumer(AbstractMainExecutor executor, FluxRunner fluxRunnerImpl,KafkaConsumer consumer,String topicName,int reconnectionInterval,String factoryClass) {
		this.executor=executor;
		this.runner=fluxRunnerImpl;
		this.consumer=consumer;
		this.consumer.subscribe(Arrays.asList(topicName));
		this.reconnectionInterval=reconnectionInterval;
		this.factoryClass=factoryClass;
		System.out.println("Subscribed to topic " + topicName);
	}

	public void disconnect() {
		this.consumer.close();
	}

	public String getFluxId() {
		return this.executor.getFlowId();
	}

	public boolean isFinished() {
		return false;
	}

	public void reconnect() {
		// TODO Auto-generated method stub
		
	}

	public int getReconnectionInterval() {
		// TODO Auto-generated method stub
		return this.reconnectionInterval;
	}
	
	
	public void consume(long pollTimeLimit) throws Exception {
	       //Kafka consumer configuration settings
			/*
	       String topicName = this.topic;
	       Properties props = new Properties();
	       
	       props.put("bootstrap.servers", this.bootstrapServers);
	       props.put("group.id", "test");
	       props.put("enable.auto.commit", "true");
	       props.put("auto.commit.interval.ms", "1000");
	       props.put("session.timeout.ms", "30000");
	       props.put("key.deserializer", 
	          "org.apache.kafka.common.serialization.StringDeserializer");
	       props.put("value.deserializer", 
	          "org.apache.kafka.common.serialization.StringDeserializer");
	       KafkaConsumer<String, String> consumer = new KafkaConsumer
	          <String, String>(props);
	       
	       //Kafka Consumer subscribes list of topics here.
	       consumer.subscribe(Arrays.asList(topicName));
	       
	       //print the topic name
	       System.out.println("Subscribed to topic " + topicName);
	       */
	       
			System.out.println("kafka-consumer-aflux: pollTimeLimit="+pollTimeLimit);
          ConsumerRecords<String, String> records = consumer.poll(pollTimeLimit);
          
          		System.out.println("kafka-consumer-aflux: count="+records.count());
          
	          for (ConsumerRecord<String, String> record : records) {
	        	  System.out.println("kafka-consumer-aflux: for record");
	        	  KafkaAfluxValue kafkaValue=new KafkaAfluxValue(record,this.factoryClass);
	        	  //TODO: Check how to do to set in order
	        	  this.runner.send(this.executor,this.executor,kafkaValue,0);
	        	  
		          // print the offset,key and value for the consumer records.
		          System.out.printf("offset = %d, key = %s, value = %s\n", 
		             record.offset(), record.key(), record.value());
	        	  
	          }
	          
	}

}
