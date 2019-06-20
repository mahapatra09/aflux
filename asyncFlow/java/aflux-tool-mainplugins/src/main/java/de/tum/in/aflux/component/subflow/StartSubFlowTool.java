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

import de.tum.in.aflux.component.subflow.actor.StartSubFlowActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolProperty;
/**
 * 
 * Class that acts as starting point of a subflux
 * It is the input interface from the parent flow
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class StartSubFlowTool extends AbstractMainExecutor  {
	static public final String NAME="start subFlow";
	static public final ToolProperty[] configurationProperties={};


	public StartSubFlowTool()  {
		super(NAME, 
				StartSubFlowActor.class.getCanonicalName(), 
				StartSubFlowTool.class.getName(),
				1 , 
				1, 
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true,
				configurationProperties);
		this.setColor("#80CBC4");
	}


}
