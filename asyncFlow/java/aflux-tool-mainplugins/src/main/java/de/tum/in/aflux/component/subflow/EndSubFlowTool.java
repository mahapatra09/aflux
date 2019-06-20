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

import de.tum.in.aflux.component.subflow.actor.EndSubFlowActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolProperty;

/**
 * Tool to set the final of a subflow
 * It is the output interface to the parent flow
 * @author jguastav
 *
 */
public class EndSubFlowTool extends AbstractMainExecutor {

	static public final String NAME="end subFlow";
	static public final ToolProperty[] configurationProperties={};
	
	
	public EndSubFlowTool()  {
		super(NAME, EndSubFlowActor.class.getCanonicalName(), EndSubFlowTool.class.getName(),1 , 1, NodeLaunchType.LAUNCHED_BY_SIGNAL,false,configurationProperties);
		this.setColor("#80CBC4");
	}
}
