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

package de.tum.in.aflux.component.pig.actor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tum.in.aflux.component.pig.PigConstants;
import de.tum.in.aflux.bigdata.pig.model.PigBuilder;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class PigFilterActor extends AbstractAFluxActor  {
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public PigFilterActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}


	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		PigExecutionPlan executionPlan=PigBuilder.buildExecutionPlan(message);
		
		
		String target = this.getProperty(PigConstants.TARGET_ALIAS);
		String sentence=
				target+" = "+  
				"FILTER ";
		
		
		String byExpression=
				PigBuilder.buildByExpression(
						PigBuilder.buildAlias(executionPlan,this.getProperty(PigConstants.SOURCE_ALIAS)),
						this.getProperty(PigConstants.BY));
		sentence+=byExpression;
		
		PigBuilder.deliverPlan("filter",sentence,target,log,executionPlan,this,byExpression,"");
		
	}

}
