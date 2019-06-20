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

package de.tum.in.aflux.component.commonanalytics;

import de.tum.in.aflux.component.commonanalytics.actor.CommonAnalyticsJoinActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class CommonAnalyticsJoin extends AbstractMainExecutor  {
	public static final String NAME="CA JOIN";
	static public ToolProperty[] connectionProperties={
			new ToolProperty(CommonAnalyticsConstants.ALIAS1,"",PropertyInputType.TEXT,null,"The name of a relation.","",false),
			new ToolProperty(CommonAnalyticsConstants.ALIAS2,"",PropertyInputType.TEXT,null,"Variable name to get the grouped data and be used in subsequent sentences","",false),
			new ToolProperty(CommonAnalyticsConstants.TARGET_ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the grouped data and be used in subsequent sentences","",false),
			new ToolProperty(CommonAnalyticsConstants.COLUMNS,"",PropertyInputType.TEXT,null,"Keyword. Use this clause to group the relation by field, tuple or expression. If it is empty groups by ALL","",false),
			new ToolProperty(CommonAnalyticsConstants.MATCH,"",PropertyInputType.TEXT,null,"Keyword. Use this clause to group the relation by field, tuple or expression. If it is empty groups by ALL","",false)
	};

	public CommonAnalyticsJoin() {
		super(NAME,
				CommonAnalyticsJoinActor.class.getCanonicalName(),
				CommonAnalyticsJoin.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(CommonAnalyticsConstants.COLOR);

	}
	
}
