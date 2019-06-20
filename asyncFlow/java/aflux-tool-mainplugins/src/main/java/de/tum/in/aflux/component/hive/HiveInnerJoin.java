

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

package de.tum.in.aflux.component.hive;

import de.tum.in.aflux.component.hive.actor.HiveInnerJoinActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * Component to add joing clauses to select sentences
 * 
 * Use following select sentences
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class HiveInnerJoin extends AbstractMainExecutor {
	public static final String NAME="Hive INNER JOIN";
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(HiveConstants.INNER,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
			new ToolProperty(HiveConstants.TABLE,"",PropertyInputType.TEXT,null,"join table factor","",false),
			new ToolProperty(HiveConstants.CONDITION,"",PropertyInputType.TEXT,null,"join condition","",false)

	};
	

	public HiveInnerJoin() {
		super(NAME,
				HiveInnerJoinActor.class.getCanonicalName(),
				HiveInnerJoin.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(HiveConstants.COLOR);
	}
	

}
