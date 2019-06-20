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
