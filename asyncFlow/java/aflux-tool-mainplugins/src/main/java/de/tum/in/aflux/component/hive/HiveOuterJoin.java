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
/**
 * Class to add hive outer joins to previous select
 * @author Tanmaya Mahapatra
 *
 */

import de.tum.in.aflux.component.hive.actor.HiveOuterJoinActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class HiveOuterJoin extends AbstractMainExecutor {
	public static final String NAME="Hive OUTER JOIN";
	
	static public ToolProperty[] connectionProperties={
			new ToolProperty(HiveConstants.SIDE,"",PropertyInputType.TEXT,null,"full/left/right","",false),
			new ToolProperty(HiveConstants.OUTER,"false",PropertyInputType.CHECKBOX,null,"true / false","",false),
			new ToolProperty(HiveConstants.TABLE,"",PropertyInputType.TEXT,null,"join table factor","",false),
			new ToolProperty(HiveConstants.CONDITION,"",PropertyInputType.TEXT,null,"join condition","",false)

	};
	

	public HiveOuterJoin() {
		super(NAME,
				HiveOuterJoinActor.class.getCanonicalName(),
				HiveOuterJoin.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(HiveConstants.COLOR);
	}
	

}
