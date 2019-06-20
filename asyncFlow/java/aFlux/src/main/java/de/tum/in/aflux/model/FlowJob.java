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
