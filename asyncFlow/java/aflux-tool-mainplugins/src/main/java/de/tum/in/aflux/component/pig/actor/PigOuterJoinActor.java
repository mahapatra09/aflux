

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright 2019 Tanmaya Mahapatra
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

public class PigOuterJoinActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public PigOuterJoinActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}


	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		PigExecutionPlan executionPlan=PigBuilder.buildExecutionPlan(message);
		
		
		String target = this.getProperty(PigConstants.TARGET_ALIAS);
		String sentence=
				target+" = "+  
				"JOIN ";
		
		
		String byExpression=
				PigBuilder.buildByExpression(
						PigBuilder.buildAlias(executionPlan,this.getProperty(PigConstants.LEFT_ALIAS)),
						this.getProperty(PigConstants.BY));
		sentence+=byExpression;
		
		if (this.getProperty(PigConstants.SIDE)!=null && (
				this.getProperty(PigConstants.SIDE).toUpperCase().equals("RIGHT") ||
				this.getProperty(PigConstants.SIDE).toUpperCase().equals("LEFT") ||
				this.getProperty(PigConstants.SIDE).toUpperCase().equals("FULL") 
				)) {
			sentence+=" "+this.getProperty(PigConstants.SIDE).toUpperCase();
		}
		sentence += PigBuilder.buildOuterClause(this.getProperty(PigConstants.OUTER));
		
		sentence +=",";
		byExpression=
				PigBuilder.buildByExpression(
						PigBuilder.buildAlias(executionPlan,this.getProperty(PigConstants.RIGHT_ALIAS)),
						this.getProperty(PigConstants.RIGHT_BY));
		sentence+=byExpression;
		
		String modifiers="";
		String type=this.getProperty(PigConstants.TYPE).toLowerCase();
		if (type!=null && (type.equals("replicated") || type.equals("skewed") )) {
			modifiers+=" USING '"+type+"'";
		}
		

		modifiers+=PigBuilder.buildParalellClause(this.getProperty(PigConstants.PARALLEL));
		
		PigBuilder.deliverPlan("join-outer",sentence,target,log,executionPlan,this,byExpression,modifiers);
		
	}

}
