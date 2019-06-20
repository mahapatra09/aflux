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

package de.tum.in.aflux.component.hive.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tum.in.aflux.bigdata.hive.model.HiveExecutionPlan;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionSentence;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutor;
import de.tum.in.aflux.bigdata.hive.model.HiveSentenceType;
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

public class HiveExecuteActor extends AbstractAFluxActor {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	

	public HiveExecuteActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,4);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		HiveExecutionPlan executionPlan=new HiveExecutionPlan();
		if (message!=null) {
			if (message instanceof HiveExecutionPlan) {
				executionPlan=(HiveExecutionPlan) message;
			} 
		} 
		
		List<Map<String, Object>> rows=HiveExecutor.executeHivePlan(executionPlan, this, log);
		this.setOutput(1, rows.toString());
		
		
		
		List<String> stringSentences=new ArrayList<String>();
		List<HiveExecutionSentence> sentences=executionPlan.getSentences();
		for (HiveExecutionSentence s:sentences) {
			stringSentences.add(s.toString());
		}
		this.setOutput(2, stringSentences);
		this.setOutput(3, executionPlan);
		
		

	}





}
