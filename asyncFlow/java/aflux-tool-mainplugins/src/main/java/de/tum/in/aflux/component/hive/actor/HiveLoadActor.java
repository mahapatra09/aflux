

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






