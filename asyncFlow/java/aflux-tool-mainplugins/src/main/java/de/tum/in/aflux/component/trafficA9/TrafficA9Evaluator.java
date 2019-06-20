

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

package de.tum.in.aflux.component.trafficA9;

import de.tum.in.aflux.component.trafficA9.actor.TrafficA9EvaluatorActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class TrafficA9Evaluator extends AbstractMainExecutor {
	public static final String NAME="TrafficA9 Evaluator";
	
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(TrafficA9Constants.PRODUCT_DISCRIMINATOR_LABEL,"0.2",PropertyInputType.TEXT,null,"Product value to decide open or close","",false),
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
	public TrafficA9Evaluator() {
		super(NAME,
				TrafficA9EvaluatorActor.class.getCanonicalName(),
				TrafficA9Evaluator.class.getName(),
				1, 
				2,
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor(TrafficA9Constants.COLOR);
	}
	

}
