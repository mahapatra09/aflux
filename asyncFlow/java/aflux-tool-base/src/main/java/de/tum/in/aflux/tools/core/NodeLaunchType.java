

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

package de.tum.in.aflux.tools.core;

public enum NodeLaunchType {
	/**
	 * Value that referes to elements that need data to be launched
	 * LAUNCHED_BY_DATA elements will no be started if they does not have connected predecessors 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	LAUNCHED_BY_DATA (0),
	/**
	 * Value that referes to elements that does not need data to be launched
	 * LAUNCHED_BY_SIGNAL elements can be started no having predecessors
	 * LAUNCHED_BY_SIGNAL elements can have data input interfaces but they can be launched no having input data 
	 * 
	 * @author Tanmaya Mahapatra
	 *
	 */
	LAUNCHED_BY_SIGNAL (1);
	
	
	int id;
	
	NodeLaunchType(int i) {
		this.id=i;
	}
	
}
