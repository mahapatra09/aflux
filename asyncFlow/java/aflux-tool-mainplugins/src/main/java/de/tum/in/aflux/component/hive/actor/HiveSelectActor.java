

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

/**
 * Hive Select Component
 * 
 * 
 * SELECT [ALL | DISTINCT] select_expr, select_expr, ...
  FROM table_reference
  [WHERE where_condition]
  [GROUP BY col_list]
  [ORDER BY col_list]
 [LIMIT  rows]
 
 
 * @author Tanmaya Mahapatra
 *
 */
public class HiveSelectActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public HiveSelectActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		HiveExecutionPlan executionPlan=HiveBuilder.buildExecutionPlan(message);
		// build sentence
		String sentence = "SELECT ";
		sentence+=HiveBuilder.buildALLDistinctClause(this.getProperty(HiveConstants.ALL_DISTINCT));
		sentence+=" "+this.getProperty(HiveConstants.EXPRESSION_LIST);
		sentence+=" FROM "+this.getProperty(HiveConstants.SOURCE_LIST);

		String modifiers="";
		modifiers+=HiveBuilder.buildWhereClause(this.getProperty(HiveConstants.CONDITIONS));
		modifiers+=
				HiveBuilder.buildGroupByClause(
						this.getProperty(HiveConstants.GROUP_BY),
						this.getProperty(HiveConstants.HAVING));
		modifiers+=HiveBuilder.buildLimitClause(this.getProperty(HiveConstants.LIMIT));

		String union=this.getProperty(HiveConstants.UNION);
		if (union==null) {
			union="";
		}
		if (union.toUpperCase().equals("ALL") || union.toUpperCase().equals("DISTINCT")) {
			union=union.toUpperCase();
			sentence=union+" "+sentence;
			HiveBuilder.deliverPlan("union",sentence,null,log,executionPlan,this,null,modifiers,null);
		} else {
			HiveBuilder.deliverPlan("select",sentence,null,log,executionPlan,this,null,modifiers,this.getProperty(HiveConstants.CREATE_TABLE));
		}
		

	}


}
