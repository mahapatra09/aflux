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
