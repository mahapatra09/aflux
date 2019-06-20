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

public class CommonAnalyticsJoinActor extends AbstractAFluxActor {
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public CommonAnalyticsJoinActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}


	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		CommonAnalyticsExecutionPlan executionPlan=CommonAnalyticsBuilder.buildExecutionPlan(message);
		
		
		String target = this.getProperty(CommonAnalyticsConstants.TARGET_ALIAS);
		String alias1=this.getProperty(CommonAnalyticsConstants.ALIAS1);
		String alias2=this.getProperty(CommonAnalyticsConstants.ALIAS2);
		String expressionList=this.getProperty(CommonAnalyticsConstants.COLUMNS);
		String matchingList=this.getProperty(CommonAnalyticsConstants.MATCH);

		String sentence=
				target+" = "+  
				"JOIN "+
				alias1+" AND "+alias2+" COLUMNS("+expressionList+") MATCH("+matchingList+")";
		
		CommonAnalyticsBuilder.deliverPlan("join",sentence,target,log,executionPlan,this,"","");
		
	}

}
