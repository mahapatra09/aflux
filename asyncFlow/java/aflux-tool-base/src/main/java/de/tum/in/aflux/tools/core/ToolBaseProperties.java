

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

package de.tum.in.aflux.tools.core;

import de.tum.in.aflux.tools.core.ToolProperty;
/**
 * Base cutsom data of all elements
 * @author Tanmaya Mahapatra
 *
 */
public class ToolBaseProperties {
	public static final int CONCURRENCY_INDEX=3; 

	public static ToolProperty[] generateToolProperties(String name,int width,String color) {
		ToolProperty[] result={
				new ToolProperty("name",name,PropertyInputType.TEXT,null,"name of the element","",false),
				new ToolProperty("width",Integer.toString(width),PropertyInputType.TEXT,null,"width in pixels of visual component","",false),
				new ToolProperty("color",color,PropertyInputType.COLOR,null,"visual element color background","",false),
				new ToolProperty("concurrency","1",PropertyInputType.TEXT,null,"Number of concurrent workers that can execute messages","",false),
				};
		return result;
	}
}
