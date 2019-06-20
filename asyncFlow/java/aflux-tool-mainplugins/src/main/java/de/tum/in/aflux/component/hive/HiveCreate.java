

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

import de.tum.in.aflux.component.hive.actor.HiveCreateActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class HiveCreate extends AbstractMainExecutor {
	public static final String NAME="Hive CREATE TABLE";
// https://cwiki.apache.org/confluence/display/Hive/LanguageManual+DDL
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(HiveConstants.TEMPORARY,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
			new ToolProperty(HiveConstants.EXTERNAL,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
			new ToolProperty(HiveConstants.NAME,"",PropertyInputType.TEXT,null,"The name","",false),
			new ToolProperty(HiveConstants.COLUMNS,"",PropertyInputType.TEXT,null,"Definition of columns","",false),
			new ToolProperty(HiveConstants.COMMENT,"",PropertyInputType.TEXT,null,"Table comment","",false),
			new ToolProperty(HiveConstants.PARTITIONED,"",PropertyInputType.TEXT,null,"Partitioned by clause","",false),
			new ToolProperty(HiveConstants.ROW_FORMAT,"",PropertyInputType.TEXT,null,"row format clause","",false),
			new ToolProperty(HiveConstants.FILE_FORMAT,"",PropertyInputType.TEXT,null,"Stored as clause","",false),
			new ToolProperty(HiveConstants.LOCATION,"",PropertyInputType.TEXT,null,"hdfs location","",false),
			new ToolProperty(HiveConstants.ADD_TIMESTAMP,"true",PropertyInputType.CHECKBOX,null,"Add timestamp to table name","",false),
	};
	

	public HiveCreate() {
		super(NAME,
				HiveCreateActor.class.getCanonicalName(),
				HiveCreate.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(HiveConstants.COLOR);
	}
	

}
