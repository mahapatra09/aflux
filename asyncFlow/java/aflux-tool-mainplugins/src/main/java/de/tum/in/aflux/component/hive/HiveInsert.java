

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

package de.tum.in.aflux.component.hive;

import de.tum.in.aflux.component.hive.actor.HiveInsertActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

/**
 * Component to insert hive data
 * @author Tanmaya Mahapatra
 *
 *INSERT { INTO | OVERWRITE } [TABLE] table_name
  [(column_list)]
  [ PARTITION (partition_clause)]
{
    [hint_clause] select_statement
  | VALUES (value [, value ...]) [, (value [, value ...]) ...]
}
 *
 *
 */
public class HiveInsert extends AbstractMainExecutor {
	public static final String NAME="Hive INSERT";
	// https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL
		
		static public ToolProperty[] connectionProperties={
				new ToolProperty(HiveConstants.INTO_OVERWRITE,"INTO",PropertyInputType.TEXT,null,"INTO/OVERWRITE","",false),
				new ToolProperty(HiveConstants.TABLE,"true",PropertyInputType.TEXT,null,"true/false","",false),
				new ToolProperty(HiveConstants.NAME,"",PropertyInputType.TEXT,null,"The table name","",false),
				new ToolProperty(HiveConstants.COLUMNS,"",PropertyInputType.TEXT,null,"column list comma separated values","",false),
				new ToolProperty(HiveConstants.PARTITION,"",PropertyInputType.TEXT,null,"Partition clause","",false),
				new ToolProperty(HiveConstants.HINT_CLAUSE,"",PropertyInputType.TEXT,null,"shuffle / noshuffle","",false),
				new ToolProperty(HiveConstants.SELECT_STATEMENT,"",PropertyInputType.TEXT,null,"select statement","",false),
				new ToolProperty(HiveConstants.VALUES,"",PropertyInputType.TEXT,null,"values list","",false)
		};
		

		public HiveInsert() {
			super(NAME,
					HiveInsertActor.class.getCanonicalName(),
					HiveInsert.class.getName(),
					1, 
					1,
					NodeLaunchType.LAUNCHED_BY_SIGNAL,
					false,
					connectionProperties);
			this.setColor(HiveConstants.COLOR);
		}
		

}
