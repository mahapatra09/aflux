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

package de.tum.in.aflux.component.control;


import de.tum.in.aflux.component.control.actor.RunNTimesToolActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class RunNTimesTool extends AbstractMainExecutor {
	public static final String NAME="Run n times";
	static public ToolProperty[] configurationProperties={
				new ToolProperty(ControlToolConstants.TIMES,"1",PropertyInputType.TEXT,null,"Number of times that will run the following nodes, 0 means forever","",false),
				new ToolProperty(ControlToolConstants.DELAY,"1000",PropertyInputType.TEXT,null,"Time between each call in milliseconds","",false),
				new ToolProperty(ControlToolConstants.DATA,"INDEX",PropertyInputType.TEXT,null,"Data to be sent: Could be INDEX (id of the repetition as String object) / INPUT (resends the received data)","",false),
				};

	public RunNTimesTool() {
		super(NAME, 
				RunNTimesToolActor.class.getCanonicalName(), 
				RunNTimesTool.class.getName(), 
				1, 1, NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true, 
				configurationProperties);
		setColor("#EF9A9A");
	}
}







