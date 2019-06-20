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

import de.tum.in.aflux.component.trafficA9.actor.TrafficA9ReducerActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class TrafficA9Reducer extends AbstractMainExecutor {

	public static final String NAME="TrafficA9 Reducer";
	
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(TrafficA9Constants.IGNORE_FIRST_RESULTS,"0",PropertyInputType.TEXT,null,"Number of discarded ticks at beggining of each experiment","",false),
			new ToolProperty(TrafficA9Constants.SAMPLE_SIZE,"60",PropertyInputType.TEXT,null,"Number of ticks of each experiment","",false),
			new ToolProperty(TrafficA9Constants.OCCUPANCY_FILTER,"-1",PropertyInputType.TEXT,null,"Value that indicates occupancy is taken","",false)
	};
	
	/**
	 * 5 outputs
	 * 1.- publish topic shoulder-control
	 * 		It should have 1st output connected to a Kafka Publisher to the topic shoulder-control
	 * 2.- report  -> results.csv log_results
	 * 
	 * 
	 * Related components
	 * 		connected to output 1 -> It should have 1st output connected to a Kafka Publisher to the topic shoulder-control
	 * 		connected to input -> 
	 * 			3 kafka consumers
	 * 				setting in classFactory property
	 * 					de.tum.in.aflux.component.trafficA9.model.Occupancy
	 * 					de.tum.in.aflux.component.trafficA9.model.Tick
	 * 					de.tum.in.aflux.component.trafficA9.model.Speed
	 * 
	 * 
	 */
	public TrafficA9Reducer() {
		super(NAME,
				TrafficA9ReducerActor.class.getCanonicalName(),
				TrafficA9Reducer.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor(TrafficA9Constants.COLOR);
	}
	

}
