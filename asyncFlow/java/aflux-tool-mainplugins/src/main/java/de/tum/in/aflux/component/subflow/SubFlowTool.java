

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

package de.tum.in.aflux.component.subflow;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolImplementation;
import de.tum.in.aflux.tools.core.ToolProperty;
/**
 * It's a subflow tool component to be used as insertion tool for local subflow
 * It has no implementation actor. It's only the representation of a tool 
 * 
 * @author jguastav
 *
 */
public class SubFlowTool extends AbstractMainExecutor {
	static public final String NAME="local subFlow";
	static public final ToolProperty[] configurationProperties={};


	public SubFlowTool()  {
		super(NAME, null, SubFlowTool.class.getName(),1 , 1, NodeLaunchType.LAUNCHED_BY_SIGNAL,true,configurationProperties);
		this.setColor("#80CBC4");
		ToolImplementation toolImplementation=new ToolImplementation(null, null);
		this.setToolImplementation(toolImplementation);
	}


}
