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
