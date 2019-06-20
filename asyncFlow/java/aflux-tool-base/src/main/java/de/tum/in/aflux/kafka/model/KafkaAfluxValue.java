

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

package de.tum.in.aflux.kafka.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class KafkaAfluxValue {
	
	ConsumerRecord<String,String> record;
	String factoryClass;
	
	public KafkaAfluxValue(ConsumerRecord<String,String> record,String factoryClass) {
		super();
		this.record = record;
		this.factoryClass= factoryClass;
	}

	public ConsumerRecord<String,String> getRecord() {
		return this.record;
	}
	
	public Object getValue() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object returnValue=null;
		if (this.record!=null) {
			if (this.record.value()!=null) {
				if (this.factoryClass!=null && this.factoryClass.length()>0) {
					Class<?> mainClass=Class.forName(this.factoryClass);
					Constructor<?> constructor=mainClass.getConstructor(String.class);
					returnValue=constructor.newInstance(this.record.value().toString());
				} else {
			returnValue=this.record.value();
				}
			}
		}
		return returnValue;
	}

}
