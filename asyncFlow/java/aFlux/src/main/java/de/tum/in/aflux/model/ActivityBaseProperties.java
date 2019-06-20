/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package de.tum.in.aflux.model;

import de.tum.in.aflux.tools.core.PropertyInputType;

public class ActivityBaseProperties {
	
	
	public static FlowElementProperty[] generate(String name) {
		FlowElementProperty[] result={
				new FlowElementProperty(FlowActivity.NAME_PROPERTY,name,name,PropertyInputType.TEXT,null,"","",false),
				new FlowElementProperty(FlowActivity.SUBFLOW_PROPERTY,"false","false",PropertyInputType.CHECKBOX,null,"","",false),
				new FlowElementProperty(FlowActivity.CATEGORY_PROPERTY,"general","general",PropertyInputType.TEXT,null,"taxonomy value to group the subflows","",false),
				new FlowElementProperty(FlowActivity.COLOR_PROPERTY,"#90CAF9","#90CAF9",PropertyInputType.TEXT,null,"visual element color background","",false)
		};
		return result;
	}
	

}
