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

package de.tum.in.aflux.component.trafficA9;

import de.tum.in.aflux.component.trafficA9.actor.TrafficA9RTXActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class TrafficA9RTX extends AbstractMainExecutor{
	public static final String NAME="TrafficA9 rtx";
	
	
	static public ToolProperty[] connectionProperties={};
	
	/**
	 * Main component for launch rtx process
	 * 
	 * outputs a Traffic a9 workflow for start or for report 
	 */
	public TrafficA9RTX() {
		super(NAME,
				TrafficA9RTXActor.class.getCanonicalName(),
				TrafficA9RTX.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true,
				connectionProperties);
		this.setColor(TrafficA9Constants.COLOR);
	}
	

}
