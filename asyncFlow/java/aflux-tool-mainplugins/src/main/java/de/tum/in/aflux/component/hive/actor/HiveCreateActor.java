
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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tum.in.aflux.component.hive.HiveConstants;
import de.tum.in.aflux.bigdata.hive.model.HiveBuilder;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionPlan;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class HiveCreateActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public HiveCreateActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		HiveExecutionPlan executionPlan=HiveBuilder.buildExecutionPlan(message);
		String name = 		HiveBuilder.buildTableName(this.getProperty(HiveConstants.NAME), this.getProperty(HiveConstants.ADD_TIMESTAMP));
		// build sentence
		String sentence = "CREATE";
		sentence+=HiveBuilder.buildTemporaryClause(this.getProperty(HiveConstants.TEMPORARY));
		sentence+=HiveBuilder.buildExternalClause(this.getProperty(HiveConstants.EXTERNAL));
		sentence+=" TABLE";
		sentence+=" IF NOT EXISTS";
		
		sentence+=" "+name;
		sentence+=" ("+this.getProperty(HiveConstants.COLUMNS)+")";
		sentence+=HiveBuilder.buildCommentClause(this.getProperty(HiveConstants.COMMENT));
		sentence+=HiveBuilder.buildPartitionedClause(this.getProperty(HiveConstants.PARTITIONED));
		sentence+=HiveBuilder.buildRowFormatClause(this.getProperty(HiveConstants.ROW_FORMAT));
		sentence+=HiveBuilder.buildStoredAsClause(this.getProperty(HiveConstants.FILE_FORMAT));
		sentence+=HiveBuilder.buildLocationClause(this.getProperty(HiveConstants.LOCATION));
		
		HiveBuilder.deliverPlan("create-table",sentence,name,log,executionPlan,this,null);

	}

}
