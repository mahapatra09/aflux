

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
/**
 * Actor component to insert hive data
 * 
 * INSERT { INTO | OVERWRITE } [TABLE] table_name
  [(column_list)]
  [ PARTITION (partition_clause)]
{
    [hint_clause] select_statement
  | VALUES (value [, value ...]) [, (value [, value ...]) ...]
}

partition_clause ::= col_name [= constant] [, col_name [= constant] ...]

hint_clause ::= [SHUFFLE] | [NOSHUFFLE]    (Note: the square brackets are part of the syntax.)
 * 
 * @author jguastav
 *
 */
public class HiveInsertActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public HiveInsertActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		HiveExecutionPlan executionPlan=HiveBuilder.buildExecutionPlan(message);
		// build sentence
		String sentence = "INSERT ";
		sentence+=HiveBuilder.buildIntoOverwriteClause(this.getProperty(HiveConstants.INTO_OVERWRITE));
		sentence+=HiveBuilder.buildTableClause(this.getProperty(HiveConstants.TABLE));
		String name = this.getProperty(HiveConstants.NAME);
		sentence+=" "+name;
		sentence+=HiveBuilder.buildColumnListClause(this.getProperty(HiveConstants.COLUMNS));
		sentence+="{";
		sentence+=
				HiveBuilder.buildSelectClause(
						this.getProperty(HiveConstants.HINT_CLAUSE),
						this.getProperty(HiveConstants.SELECT_STATEMENT));
		sentence+=
				HiveBuilder.buildValuesClause(this.getProperty(HiveConstants.VALUES));
		sentence+="}";
		sentence+= HiveBuilder.buildPartitionClause(this.getProperty(HiveConstants.PARTITION));
		
		HiveBuilder.deliverPlan("insert",sentence,name,log,executionPlan,this,null);

	}


}
