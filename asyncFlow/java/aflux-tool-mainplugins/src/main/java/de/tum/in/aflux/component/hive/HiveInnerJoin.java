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

import de.tum.in.aflux.component.hive.actor.HiveInnerJoinActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * Component to add joing clauses to select sentences
 * 
 * Use following select sentences
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class HiveInnerJoin extends AbstractMainExecutor {
	public static final String NAME="Hive INNER JOIN";
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(HiveConstants.INNER,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
			new ToolProperty(HiveConstants.TABLE,"",PropertyInputType.TEXT,null,"join table factor","",false),
			new ToolProperty(HiveConstants.CONDITION,"",PropertyInputType.TEXT,null,"join condition","",false)

	};
	

	public HiveInnerJoin() {
		super(NAME,
				HiveInnerJoinActor.class.getCanonicalName(),
				HiveInnerJoin.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(HiveConstants.COLOR);
	}
	

}
