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
