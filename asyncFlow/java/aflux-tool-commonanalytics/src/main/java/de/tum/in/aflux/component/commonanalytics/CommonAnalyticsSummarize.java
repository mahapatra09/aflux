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

import de.tum.in.aflux.component.commonanalytics.actor.CommonAnalyticsSummarizeActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class CommonAnalyticsSummarize extends AbstractMainExecutor  {
	public static final String NAME="CA GROUP";
	static public ToolProperty[] connectionProperties={
			new ToolProperty(CommonAnalyticsConstants.TARGET_ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the grouped data and be used in subsequent sentences","",false),
			new ToolProperty(CommonAnalyticsConstants.SOURCE_ALIAS,"",PropertyInputType.TEXT,null,"Alias to be grouped","",false),
			new ToolProperty(CommonAnalyticsConstants.GROUP_LIST,"",PropertyInputType.TEXT,null,"Keyword. Use this clause to group the relation by field, tuple or expression. If it is empty groups by ALL","",false),
			new ToolProperty(CommonAnalyticsConstants.SUMMARY_LIST,"",PropertyInputType.TEXT,null,"Allows for more efficient computation of a group if the loader guarantees that the data for the same key is continuous and is given to a single map","",false)
	};

	public CommonAnalyticsSummarize() {
		super(NAME,
				CommonAnalyticsSummarizeActor.class.getCanonicalName(),
				CommonAnalyticsSummarize.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(CommonAnalyticsConstants.COLOR);

	}

}
