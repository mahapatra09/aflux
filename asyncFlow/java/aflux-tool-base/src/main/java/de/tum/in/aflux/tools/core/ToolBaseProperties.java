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
