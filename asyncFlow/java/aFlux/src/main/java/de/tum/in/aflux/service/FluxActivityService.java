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

package de.tum.in.aflux.service;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tum.in.aflux.flux_engine.impl.FluxError;


/**
 * Services related with activities
 * @author Tanmaya Mahapatra
 *
 */
@Component
public class FluxActivityService {
	
	
	/**
	 * Get Flux Activity from Database
	 * 
	 * TODO: Get actually the connectors and elements based on activity name
	 * 
	 * @param name
	 * @return
	 */
	/*
	public FlowActivity load(String name) {
		
		Example<FlowActivity> example = Example.of(new FlowActivity(null,name));
		FlowActivity activity=repository.findOne(example);
		return activity;
	}
	*/
	
	public List<FluxError> validate() {
		return null;
	}
	
	public void compile() {
		
	}

	
	
}
