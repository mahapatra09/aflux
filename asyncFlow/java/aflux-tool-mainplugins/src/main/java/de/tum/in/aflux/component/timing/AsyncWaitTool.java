

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

package de.tum.in.aflux.component.timing;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.component.timing.actor.AsyncWaitToolActor;
import de.tum.in.aflux.tools.core.ToolProperty;

public class AsyncWaitTool extends AbstractMainExecutor {
	
	

	static public final String NAME="async wait";
	static public final ToolProperty[] configurationProperties={
			new ToolProperty("value","1000",PropertyInputType.TEXT,null,"Time to wait in milliseconds","",false)
			};

	public AsyncWaitTool() {
		super(NAME, AsyncWaitToolActor.class.getCanonicalName(), AsyncWaitTool.class.getName(),1 , 1, NodeLaunchType.LAUNCHED_BY_SIGNAL,true,configurationProperties);
		this.setColor("#A5D6A7");
	}


}
