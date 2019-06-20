

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

package de.tum.in.aflux.component.commonanalytics.actor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tum.in.aflux.component.commonanalytics.CommonAnalyticsConstants;
import de.tum.in.aflux.component.commonanalytics.model.CommonAnalyticsBuilder;
import de.tum.in.aflux.component.commonanalytics.model.CommonAnalyticsExecutionPlan;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
/**
 * 
 * collection_sentence: collection_name '=' 'SELECT' collection_name  '(' expression_list ')' 'FILTER' '(' (condition_list)? ')' eol;

 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsSelectActor extends AbstractAFluxActor  {
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public CommonAnalyticsSelectActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}


	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		CommonAnalyticsExecutionPlan executionPlan=CommonAnalyticsBuilder.buildExecutionPlan(message);
		
		
		String target = this.getProperty(CommonAnalyticsConstants.TARGET_ALIAS);
		String alias = CommonAnalyticsBuilder.buildAlias(executionPlan, this.getProperty(CommonAnalyticsConstants.SOURCE_ALIAS));
		String expressionList = this.getProperty(CommonAnalyticsConstants.COLUMNS);
		String conditionList = this.getProperty(CommonAnalyticsConstants.FILTER);
		String sentence=
				target+" = "+  
				"SELECT "+alias+ " ("+expressionList+") FILTER ("+conditionList+")";
		CommonAnalyticsBuilder.deliverPlan("select",sentence,target,log,executionPlan,this,null,"");
		
	}

}
