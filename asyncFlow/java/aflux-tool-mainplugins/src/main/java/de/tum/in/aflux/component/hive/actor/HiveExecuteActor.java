

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
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
