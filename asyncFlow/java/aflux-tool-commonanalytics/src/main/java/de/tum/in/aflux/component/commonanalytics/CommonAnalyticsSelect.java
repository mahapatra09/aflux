

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

import de.tum.in.aflux.component.commonanalytics.actor.CommonAnalyticsSelectActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;
/**
 * Generator 
 * 
 * collection_sentence: collection_name '=' 'COLUMNS' collection_name  '(' expression_list ')' 'FILTER' '(' (condition_list)? ')' eol;

 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsSelect extends AbstractMainExecutor  {
	public static final String NAME="CA SELECT";
	static public ToolProperty[] connectionProperties={
			new ToolProperty(CommonAnalyticsConstants.TARGET_ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the grouped data and be used in subsequent sentences","",false),
			new ToolProperty(CommonAnalyticsConstants.SOURCE_ALIAS,"",PropertyInputType.TEXT,null,"The name of a relation.","",false),
			new ToolProperty(CommonAnalyticsConstants.COLUMNS,"",PropertyInputType.TEXT,null,"Keyword. Use this clause to group the relation by field, tuple or expression. If it is empty groups by ALL","",false),
			new ToolProperty(CommonAnalyticsConstants.FILTER,"",PropertyInputType.TEXT,null,"List of conditions","",false)
	};

	public CommonAnalyticsSelect() {
		super(NAME,
				CommonAnalyticsSelectActor.class.getCanonicalName(),
				CommonAnalyticsSelect.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(CommonAnalyticsConstants.COLOR);

	}
}
