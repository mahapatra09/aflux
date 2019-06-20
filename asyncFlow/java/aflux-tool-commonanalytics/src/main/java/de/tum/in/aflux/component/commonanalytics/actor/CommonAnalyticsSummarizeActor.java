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
 * 
 * summarize_sentence: collection_name '=' 'SUMMARIZE' collection_name  '(' (summary_expression_list)? ')' 'KEYS' '(' (groupby_list)? ')' eol; 

 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsSummarizeActor extends AbstractAFluxActor {
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public CommonAnalyticsSummarizeActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}


	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		CommonAnalyticsExecutionPlan executionPlan=CommonAnalyticsBuilder.buildExecutionPlan(message);
		
		
		String target = this.getProperty(CommonAnalyticsConstants.TARGET_ALIAS);
		String alias = CommonAnalyticsBuilder.buildAlias(executionPlan, this.getProperty(CommonAnalyticsConstants.SOURCE_ALIAS));
		String expressionList = this.getProperty(CommonAnalyticsConstants.SUMMARY_LIST);
		String keyList = this.getProperty(CommonAnalyticsConstants.GROUP_LIST);
		
		String sentence=
				target+" = "+  
				"SUMMARIZE "+alias+" ("+expressionList+") KEYS ("+keyList+")";
		
		
		String byExpression=null;
		
		String modifiers="";
		
		CommonAnalyticsBuilder.deliverPlan("join",sentence,target,log,executionPlan,this,byExpression,modifiers);
		
	}

}
