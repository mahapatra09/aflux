

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

package de.tum.in.aflux.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Data structure to persist job information
 * @author Tanmaya Mahapatra
 *
 */
@Document
public class FlowJob {
	private @Id String id;

	@Column(unique=true)	
	private String name;
	
	
	private List<FlowActivity> activities;
	
	private FlowJob() {}
	
	
	public FlowJob(String name) {
		this.name=name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<FlowActivity> getActivities() {
		return activities;
	}


	public void setActivities(List<FlowActivity> activities) {
		this.activities = activities;
	}
	
	
	
    @Override
	public String toString() {
    	/*
		return "FlowJob [id=" + id + ", name=" + name + ", activities="
				+ activities + "]";
				*/
		return "FlowJob [id=" + id + ", name=" + name + ", activities="
		 +activities+ "]";
	}
}
