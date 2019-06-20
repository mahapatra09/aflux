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
