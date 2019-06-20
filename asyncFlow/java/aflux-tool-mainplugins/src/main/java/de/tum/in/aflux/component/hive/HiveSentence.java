

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

import de.tum.in.aflux.component.hive.actor.HiveSentenceActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class HiveSentence extends AbstractMainExecutor {
	public static final String NAME="Hive Sentence";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(HiveConstants.SENTENCE,"",PropertyInputType.TEXT,null,"HiveQL sentence","",false)
	};
	

	public HiveSentence() {
		super(NAME,
				HiveSentenceActor.class.getCanonicalName(),
				HiveSentence.class.getName(),
				1, 
				2,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(HiveConstants.COLOR);
	}

}
