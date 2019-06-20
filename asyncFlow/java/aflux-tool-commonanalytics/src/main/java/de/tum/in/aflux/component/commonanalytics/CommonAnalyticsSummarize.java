

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
