

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

package de.tum.in.aflux.model;

import de.tum.in.aflux.tools.core.PropertyInputType;


public class SubFlowElementBaseProperties {
	public static FlowElementProperty[] generate(String name,int width,String color,int concurrency) {
		FlowElementProperty[] nodeProperties=FlowElementBaseProperties.generate(name, width, color, concurrency);
		FlowElementProperty[] subFlowProperties={
				new FlowElementProperty("edit","Edit SubFlow",name,PropertyInputType.BUTTON,null,"name of the element","",true),
				};
		FlowElementProperty[] result = new FlowElementProperty[nodeProperties.length+subFlowProperties.length];
		int i=0;
		for (FlowElementProperty item:nodeProperties) {
			result[i]=item;
			i++;
		}
		for (FlowElementProperty item:subFlowProperties) {
			result[i]=item;
			i++;
		}
		return result;
	}
	
}
