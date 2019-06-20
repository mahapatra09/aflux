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

import de.tum.in.aflux.component.trafficA9.actor.TrafficA9ReportActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class TrafficA9Report extends AbstractMainExecutor {
	public static final String NAME="TrafficA9 Report";
	
	
	static public ToolProperty[] connectionProperties={};
	
	/**
	 * Gets an TrafficA9 workflow
	 */
	public TrafficA9Report() {
		super(NAME,
				TrafficA9ReportActor.class.getCanonicalName(),
				TrafficA9Report.class.getName(),
				1, 
				0,
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor(TrafficA9Constants.COLOR);
	}

}
