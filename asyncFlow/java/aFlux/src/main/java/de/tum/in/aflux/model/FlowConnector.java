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

import javax.persistence.Id;
import javax.persistence.Transient;


/**
 * 
 * Connetor between elements
 * A connection between data elements (index >=1) means the data produced in source element will be passed to target elements
 * A connection between and async output and a target element means the target element will be launched as soon as source element starts
 * Data Messages are not sent at the end of the element execution but when the element execute this.setOutput(index,message)
 * 
 * @author Tanmaya Mahapatra
 *
 */

public class FlowConnector {
	
	private @Id Long id;
	@Transient
	private FlowElement sourceElement;
	private int sourceIndex;
	@Transient
	private FlowElement targetElement;
	private int targetIndex;
	private boolean deleted;
	private Long sourceId;
	private Long targetId;
	private Boolean asyncInterface;

	private FlowConnector() {}
	
	/**
	 * Constructor for not async connectors
	 * @param id
	 * @param sourceElement
	 * @param sourceIndex
	 * @param targetElement
	 * @param targetIndex
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	public FlowConnector(Long id,FlowElement sourceElement,int sourceIndex,FlowElement targetElement,int targetIndex) {
		this.id=id;
		this.targetIndex=targetIndex;
		this.sourceIndex=sourceIndex;
		this.deleted=false;
		this.asyncInterface=false;
		this.setSourceElement(sourceElement);
		this.setTargetElement(targetElement);
	}

	/**
	 * Constructor for asynchronic connectors
	 * @param id
	 * @param sourceElement
	 * @param targetElement
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public FlowConnector(Long id,FlowElement sourceElement,FlowElement targetElement) {
		this.id=id;
		this.targetIndex=1;
		this.sourceIndex=-1;
		this.deleted=false;
		this.asyncInterface=true;
		this.setSourceElement(sourceElement);
		this.setTargetElement(targetElement);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public FlowElement getSourceElement() {
		return sourceElement;
	}

	public void setSourceElement(FlowElement sourceElement) {
		this.sourceElement = sourceElement;
		if (sourceElement!=null) {
			this.setSourceId(sourceElement.getId());
		}
	}

	public FlowElement getTargetElement() {
		return targetElement;
	}

	public void setTargetElement(FlowElement targetElement) {
		this.targetElement = targetElement;
		if (targetElement!=null) {
			this.setTargetId(targetElement.getId());
		}
	}





	public Long getSourceId() {
		return sourceId;
	}


	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}	
	
	
	@Override
	public String toString() {
		return "FlowConnector [id=" + id + ", \nsourceElement=" + sourceElement
				+ ", \nsourceIndex=" + sourceIndex + ", \ntargetElement="
				+ targetElement + ", \ntargetIndex=" + targetIndex + ", deleted="
				+ deleted + ", \nsourceId=" + sourceId + ", \ntargetId=" + targetId
				+ "]";
	}

	public Boolean getAsyncInterface() {
		return asyncInterface;
	}

	public void setAsyncInterface(Boolean async) {
		this.asyncInterface = async;
	}

	
	}
	

