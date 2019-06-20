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

package de.tum.in.aflux.component.trafficA9.actor;

import java.util.Map;

import de.tum.in.aflux.component.trafficA9.model.TrafficA9Definition;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class TrafficA9RTXActor extends AbstractAFluxActor{
	public TrafficA9RTXActor(String fluxId, 
			FluxEnvironment fluxEnvironment, 
			FluxRunner fluxRunner,
			Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,0);
	}

	
	public TrafficA9RTXActor() {
		this("-1",null,null,null);
	}
	
	
	@Override
	protected void runCore(Object message) throws Exception {
		TrafficA9Definition wf=new TrafficA9Definition();
		this.setOutput(1, wf);
	}

}
