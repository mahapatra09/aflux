

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

import de.tum.in.aflux.component.hive.actor.HiveSelectActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;
/**
 * Select Hive Component
 * @author Tanmaya Mahapatra
 * 
 * 
[WITH CommonTableExpression (, CommonTableExpression)*]    (Note: Only available starting with Hive 0.13.0)
SELECT [ALL | DISTINCT] select_expr, select_expr, ...
  FROM table_reference
  [WHERE where_condition]
  [GROUP BY col_list]
  [ORDER BY col_list]
  [CLUSTER BY col_list
    | [DISTRIBUTE BY col_list] [SORT BY col_list]
  ]
 [LIMIT [offset,] rows]



 *
 */
public class HiveSelect extends AbstractMainExecutor {
	public static final String NAME="Hive SELECT";
		
		static public ToolProperty[] connectionProperties={
				new ToolProperty(HiveConstants.ALL_DISTINCT,"ALL",PropertyInputType.TEXT,null,"ALL/DISTINCT","",false),
				new ToolProperty(HiveConstants.EXPRESSION_LIST,"",PropertyInputType.TEXT,null,"Comma separated list of expressions","",false),
				new ToolProperty(HiveConstants.SOURCE_LIST,"",PropertyInputType.TEXT,null,"Comma separated list of source tables","",false),
				new ToolProperty(HiveConstants.CONDITIONS,"",PropertyInputType.TEXT,null,"Where clause","",false),
				new ToolProperty(HiveConstants.GROUP_BY,"",PropertyInputType.TEXT,null,"group by clause","",false),
				new ToolProperty(HiveConstants.HAVING,"",PropertyInputType.TEXT,null,"group by clause","",false),
				new ToolProperty(HiveConstants.ORDER_BY,"",PropertyInputType.TEXT,null,"order by clause","",false),
				new ToolProperty(HiveConstants.LIMIT,"",PropertyInputType.TEXT,null,"integer expression","",false),
				new ToolProperty(HiveConstants.UNION,"",PropertyInputType.TEXT,null,"ALL / DISTINCT / blank - if it is not blank builds an union with the previous select","",false),
				new ToolProperty(HiveConstants.CREATE_TABLE,"",PropertyInputType.TEXT,null,"Name of the table to be created / Empty on no creation operation","",false)

				
		};
		

		public HiveSelect() {
			super(NAME,
					HiveSelectActor.class.getCanonicalName(),
					HiveSelect.class.getName(),
					1, 
					1,
					NodeLaunchType.LAUNCHED_BY_SIGNAL,
					false,
					connectionProperties);
			this.setColor(HiveConstants.COLOR);
		}
		

}
