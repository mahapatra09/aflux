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
import de.tum.in.aflux.util.AFluxUtils;

/**
 * 
 * Creation of load sentence
// syntax
//	LOAD DATA INPATH 'hdfs_file_or_directory_path' [OVERWRITE] INTO TABLE tablename
// 	  [PARTITION (partcol1=val1, partcol2=val2 ...)]
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class HiveLoadActor extends AbstractAFluxActor{
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public HiveLoadActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		// get previous script
		HiveExecutionPlan executionPlan=HiveBuilder.buildExecutionPlan(message);
		// build sentence
		
		
		String tableName = HiveBuilder.buildTableName(this.getProperty(HiveConstants.NAME), this.getProperty(HiveConstants.ADD_TIMESTAMP));
		
		String preserveSourceString=this.getProperty(HiveConstants.PRESERVE_SOURCE);
		boolean preserveSource=false;
		String columns="";
		if (preserveSourceString!=null) {
			preserveSourceString=preserveSourceString.toUpperCase().trim();
			preserveSource=preserveSourceString.equals("TRUE") || preserveSourceString.equals("YES");
		}
		String hdfsFileName="'"+this.getProperty(HiveConstants.HDFS_FILE)+"'";
		if (preserveSource) {
			
			  String hiveCreateSentence = 
					  HiveBuilder.CREATE_TABLE_EXTERNAL_PRE+
					  " "+tableName+" ("+this.getProperty(HiveConstants.COLUMNS)+")"+HiveBuilder.CREATE_TABLE_CSV_POST+" LOCATION "+hdfsFileName;
				HiveBuilder.deliverPlan("load",hiveCreateSentence,tableName,log,executionPlan,this,null);
		} else {
			String sentence = "LOAD DATA INPATH ";
			sentence+=hdfsFileName;
			sentence+=HiveBuilder.buildOverwriteClause(this.getProperty(HiveConstants.OVERWRITE));
			sentence+=" INTO TABLE "+tableName;
			sentence+= HiveBuilder.buildPartitionClause(this.getProperty(HiveConstants.PARTITION));
			HiveBuilder.deliverPlan("load",sentence,tableName,log,executionPlan,this,null);
		}
		
	}

	
}






