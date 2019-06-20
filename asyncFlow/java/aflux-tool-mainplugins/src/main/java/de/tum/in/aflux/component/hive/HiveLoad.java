

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

package de.tum.in.aflux.component.hive;

import de.tum.in.aflux.component.hive.actor.HiveLoadActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

/**
 * Component to load data in hive table
 * @author Tanmaya Mahapatra
 * 
 * // syntax
//	LOAD DATA INPATH 'hdfs_file_or_directory_path' [OVERWRITE] INTO TABLE tablename
// 	  [PARTITION (partcol1=val1, partcol2=val2 ...)]


 *
 */
public class HiveLoad extends AbstractMainExecutor {
	public static final String NAME="Hive LOAD";
	// https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL
		
		static public ToolProperty[] connectionProperties={
				new ToolProperty(HiveConstants.HDFS_FILE,"",PropertyInputType.TEXT,null,"Hdfs File","",false),
				new ToolProperty(HiveConstants.OVERWRITE,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
				new ToolProperty(HiveConstants.NAME,"",PropertyInputType.TEXT,null,"The table name","",false),
				new ToolProperty(HiveConstants.PARTITION,"",PropertyInputType.TEXT,null,"Partition clause","",false),
				new ToolProperty(HiveConstants.PRESERVE_SOURCE,"true",PropertyInputType.CHECKBOX,null,"Set to true to preserve source hdfs file","", false),
				new ToolProperty(HiveConstants.ADD_TIMESTAMP,"true",PropertyInputType.CHECKBOX,null,"Add timestamp to table name","",false),
				new ToolProperty(HiveConstants.COLUMNS,"",PropertyInputType.TEXT,null,"Table structure when preserve source is true","",false)
		};
		

		public HiveLoad() {
			super(NAME,
					HiveLoadActor.class.getCanonicalName(),
					HiveLoad.class.getName(),
					1, 
					1,
					NodeLaunchType.LAUNCHED_BY_SIGNAL,
					false,
					connectionProperties);
			this.setColor(HiveConstants.COLOR);
		}
		

}
