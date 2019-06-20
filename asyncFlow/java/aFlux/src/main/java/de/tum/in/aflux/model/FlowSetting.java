

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

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


/**
 * Class to store seting data
 * @author Tanmaya Mahapatra
 *
 */

public class FlowSetting {
	private @Id String id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private NodeElementSetting nodeElement;
	private boolean initalizeDatabase;
	
	private FlowSetting() {}
	
	public FlowSetting(NodeElementSetting nodeElement) {
		this.nodeElement=nodeElement;
		this.initalizeDatabase=false;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NodeElementSetting getNodeElement() {
		return nodeElement;
	}

	public void setNodeElement(NodeElementSetting nodeElement) {
		this.nodeElement = nodeElement;
	}

	public boolean isInitalizeDatabase() {
		return initalizeDatabase;
	}

	public void setInitalizeDatabase(boolean initalizeDatabase) {
		this.initalizeDatabase = initalizeDatabase;
	}
	
	
	
	
}
