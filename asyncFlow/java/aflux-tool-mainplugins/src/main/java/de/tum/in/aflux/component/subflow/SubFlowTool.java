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
