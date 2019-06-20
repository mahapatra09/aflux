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

import de.tum.in.aflux.component.commonanalytics.actor.CommonAnalyticsExecuteActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * Expected inputs for pig script
 * 1.- Is launched by signal but it can receive a previous script to be attached
 * 
 * 
 * Outputs
 * 1.- Results as String
 * 2.- Script as String
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsExecute extends AbstractMainExecutor {
	public static final String NAME="CA Execute";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(CommonAnalyticsConstants.EXECUTOR,"PIG",PropertyInputType.TEXT,null,"PIG / HIVE / SPARK","",false),
			new ToolProperty(CommonAnalyticsConstants.RUN,"true",PropertyInputType.CHECKBOX,null,"true / false : indicates if script should be run","",false),
	};
	

	public CommonAnalyticsExecute() {
		super(NAME,
				CommonAnalyticsExecuteActor.class.getCanonicalName(),
				CommonAnalyticsExecute.class.getName(),
				1, 
				4,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(CommonAnalyticsConstants.COLOR);

	}
	
	
	
}
