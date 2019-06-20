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
