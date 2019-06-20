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
