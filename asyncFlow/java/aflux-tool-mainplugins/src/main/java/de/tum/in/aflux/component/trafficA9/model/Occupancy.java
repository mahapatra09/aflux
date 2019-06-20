

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

package de.tum.in.aflux.component.trafficA9.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.tum.in.aflux.kafka.model.AbstractKafkaAfluxData;

public class Occupancy extends AbstractKafkaAfluxData{

	float occupancy;
	String id;
	
	public Occupancy() {
		super();
	}
	
	
	public Occupancy(String s) {
		super(s);
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Occupancy obj = mapper.readValue(s, Occupancy.class);
			this.id=obj.id;
			this.occupancy=obj.occupancy;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Occupancy message:"+s);
	}

	public float getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(float occupancy) {
		this.occupancy = occupancy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	


}
