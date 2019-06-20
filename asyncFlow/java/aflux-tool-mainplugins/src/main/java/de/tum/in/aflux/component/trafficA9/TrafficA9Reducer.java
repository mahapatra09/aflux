

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
