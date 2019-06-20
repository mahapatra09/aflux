

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

package de.tum.in.aflux.component.pig;

import de.tum.in.aflux.component.pig.actor.PigDescribeActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**^
 * 
 * 
 * Tool for show the schema of a collection
 * @author Tanmaya Mahapatra
 *
 */
public class PigDescribe extends AbstractMainExecutor  {
	public static final String NAME="Pig DESCRIBE";
	
	
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(PigConstants.ALIAS,"",PropertyInputType.TEXT,null,"Alias name associated to the collection to be describede","",false),
			
	};

	public PigDescribe() {
		super(NAME,
				PigDescribeActor.class.getCanonicalName(),
				PigDescribe.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(PigConstants.COLOR);

	}


}
