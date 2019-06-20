
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

package de.tum.in.aflux.component.strings;


import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.component.strings.actor.ShowStringValueActor;

public class ShowStringValue  extends AbstractMainExecutor {

	public static final String NAME="show value";
	public ShowStringValue() {
		super(NAME,ShowStringValueActor.class.getCanonicalName(),ShowStringValue.class.getName(),1, 0, NodeLaunchType.LAUNCHED_BY_DATA,false,null);
		this.setColor("#80DEEA");

	}



	

	

}
