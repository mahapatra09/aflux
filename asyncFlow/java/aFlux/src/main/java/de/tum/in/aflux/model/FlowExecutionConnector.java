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


/**
 * Connection element in an execution environment.
 * The difference with the FlowConnector is that identifies source and target flowElements using the id assigned during execution that considerates also the subflows with the notation idFlowElement.idSubFlow
 * @author Tanmaya Mahapatra
 *
 */
public class FlowExecutionConnector extends FlowConnector {

	private String sourceExecutionId;
	private String targetExecutionId;
	

	/**
	 * Generates a new connector element pointing to the index assigned in the running environment
	 * @param flowConnector
	 * @param prefix
	 */
	public FlowExecutionConnector(FlowConnector flowConnector,String prefix) {
		super(flowConnector.getId(),flowConnector.getSourceElement(),flowConnector.getSourceIndex(),flowConnector.getTargetElement(),flowConnector.getTargetIndex());
		this.setSourceExecutionId(prefix+flowConnector.getSourceElement().getId().toString());
		this.setTargetExecutionId(prefix+flowConnector.getTargetElement().getId().toString());
	};

	public String getSourceExecutionId() {
		return sourceExecutionId;
	}

	public void setSourceExecutionId(String sourceExecutionId) {
		this.sourceExecutionId = sourceExecutionId;
	}

	public String getTargetExecutionId() {
		return targetExecutionId;
	}

	public void setTargetExecutionId(String targetExecutionId) {
		this.targetExecutionId = targetExecutionId;
	}

	@Override
	public String toString() {
		return String.format("FlowExecutionConnector [sourceExecutionId=%s, targetExecutionId=%s]\n"+super.toString(), sourceExecutionId,
				targetExecutionId);
	}
	
	
	

}
