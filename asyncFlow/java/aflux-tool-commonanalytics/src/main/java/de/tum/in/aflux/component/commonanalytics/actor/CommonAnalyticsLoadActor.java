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
 * load_sentence: 'LOAD' file_name 'TO' collection_name 'STRUCTURE' '(' structure_definition_list ')'  eol;

 * @author Tanmaya Mahapatra
 * 
 *
 */
public class CommonAnalyticsLoadActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public CommonAnalyticsLoadActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		CommonAnalyticsExecutionPlan executionPlan=CommonAnalyticsBuilder.buildExecutionPlan(message);
		// build sentence
		String target = this.getProperty(CommonAnalyticsConstants.ALIAS);
		String fileName = this.getProperty(CommonAnalyticsConstants.FILE_NAME);
		String alias = this.getProperty(CommonAnalyticsConstants.ALIAS);
		String structure = this.getProperty(CommonAnalyticsConstants.STRUCTURE);
		
		
		
		
		
		String sentence=
				"LOAD '"+fileName+"' TO "+alias+" STRUCTURE("+structure+")";
		CommonAnalyticsBuilder.deliverPlan("load",sentence,target,log,executionPlan,this);

	}
}
