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

package de.tum.in.aflux.model;

import de.tum.in.aflux.tools.core.PropertyInputType;

/**
 * 
 * Static data of common properties for all flow elements
 * 
 * @author  Tanmaya Mahapatra
 *
 */
public class FlowElementBaseProperties {
	
	public static FlowElementProperty[] generate(String name,int width,String color,int concurrency) {
		FlowElementProperty[] result={
				new FlowElementProperty("name",name,name,PropertyInputType.TEXT,null,"name of the element","",false),
				new FlowElementProperty("width",Integer.toString(width),Integer.toString(width),PropertyInputType.TEXT,null,"width in pixels of visual component","",false),
				new FlowElementProperty("color",color,color,PropertyInputType.TEXT,null,"visual element color background","",false),
				new FlowElementProperty("concurrency",Integer.toString(concurrency),Integer.toString(width),PropertyInputType.TEXT,null,"Number of concurrent node instances running","",false),
				};
		
		
		return result;
	}
	

}
