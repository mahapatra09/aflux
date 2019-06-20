

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

package de.tum.in.aflux.component.pig;

import de.tum.in.aflux.component.pig.actor.PigOuterJoinActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;
import de.tum.in.aflux.tools.core.ToolPropertyOption;

public class PigOuterJoin extends AbstractMainExecutor {
	public static final String NAME="Pig JOIN (Outer)";
	
	static ToolPropertyOption[] joinKind={
			new ToolPropertyOption("",""),
			new ToolPropertyOption("left","left"),
			new ToolPropertyOption("right","right"),
			new ToolPropertyOption("full","full")};
	static ToolPropertyOption[] kindOfJoin={
			new ToolPropertyOption("",""),
			new ToolPropertyOption("replicated","replicated"),
			new ToolPropertyOption("skewed","skewed")};

	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(PigConstants.LEFT_ALIAS,"",PropertyInputType.TEXT,null,"The name of the relation","",false),
			new ToolProperty(PigConstants.BY,"",PropertyInputType.TEXT,null,"The name of the join column for the corresponding relation","",false),
			new ToolProperty(PigConstants.SIDE,"",PropertyInputType.SELECT,joinKind,"Side: left / right / full","",false),
			new ToolProperty(PigConstants.OUTER,"false",PropertyInputType.CHECKBOX,null,"Optional keyword","",false),
			new ToolProperty(PigConstants.RIGHT_ALIAS,"",PropertyInputType.TEXT,null,"The name of the relation","",false),
			new ToolProperty(PigConstants.RIGHT_BY,"",PropertyInputType.TEXT,null,"The name of the join column for the corresponding relation","",false),
			new ToolProperty(PigConstants.TYPE,"",PropertyInputType.SELECT,kindOfJoin,"Kind of join may be replicated / skewed","",false),
			new ToolProperty(PigConstants.PARALLEL,"1",PropertyInputType.TEXT,null,"Increase the parallelism of a job by specifying the number of reduce tasks, n","",false),
			new ToolProperty(PigConstants.TARGET_ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the grouped data and be used in subsequent sentences","",false)
	};

	public PigOuterJoin() {
		super(NAME,
				PigOuterJoinActor.class.getCanonicalName(),
				PigOuterJoin.class.getName(),
				1, 
				1, 
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(PigConstants.COLOR);

	}
	

}
