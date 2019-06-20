

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
