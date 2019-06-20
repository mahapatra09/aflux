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

import de.tum.in.aflux.component.commonanalytics.actor.CommonAnalyticsLoadActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class CommonAnalyticsLoad extends AbstractMainExecutor {
	public static final String NAME="CA LOAD";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(CommonAnalyticsConstants.FILE_NAME,"",PropertyInputType.TEXT,null,"The name of the file or directory, with NO single quotes.","",false),
			new ToolProperty(CommonAnalyticsConstants.STRUCTURE,"",PropertyInputType.TEXT,null,"structure of the resulting table","",false),
			new ToolProperty(CommonAnalyticsConstants.ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the loaded data and be used in subsequent sentences","",false)
	};
	

	public CommonAnalyticsLoad() {
		super(NAME,
				CommonAnalyticsLoadActor.class.getCanonicalName(),
				CommonAnalyticsLoad.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(CommonAnalyticsConstants.COLOR);

	}
	
	

}


