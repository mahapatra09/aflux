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
import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionStep;
import de.tum.in.aflux.bigdata.pig.model.PigExecutor;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
/**
 * 
 * 
 * Expected inputs for pig script
 * 1.- Is launched by signal but it can receive a previous script to be attached
 * 
 * 
 * Outputs
 * 1.- Results as String
 * 2.- Script as String
 * 
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class PigExecuteActor extends AbstractAFluxActor {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	

	public PigExecuteActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,4);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		PigExecutionPlan executionPlan=new PigExecutionPlan();
		if (message!=null) {
			if (message instanceof PigExecutionPlan) {
				executionPlan=(PigExecutionPlan) message;
			}
		}
		
		
		
		String finalScript=executionPlan.getScript();
		String result= PigExecutor.execute(executionPlan, this, log);
		
		this.setOutput(1, result);
		this.setOutput(2, finalScript);
		this.setOutput(3,executionPlan);

	}




}


