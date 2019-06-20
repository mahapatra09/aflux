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
