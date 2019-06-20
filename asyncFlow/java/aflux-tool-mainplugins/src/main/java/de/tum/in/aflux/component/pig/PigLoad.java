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

package de.tum.in.aflux.component.pig;

import de.tum.in.aflux.component.pig.actor.PigLoadActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class PigLoad extends AbstractMainExecutor {
	public static final String NAME="Pig LOAD";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(PigConstants.SOURCE_DATA,"",PropertyInputType.TEXT,null,"The name of the file or directory, with NO single quotes.","",false),
			new ToolProperty(PigConstants.FUNCTION,"PigStorage(',')",PropertyInputType.TEXT,null,"The load function","",false),
			new ToolProperty(PigConstants.SCHEMA,"",PropertyInputType.TEXT,null,"A schema using the AS keyword, not enclosed in parentheses (see Schemas).","",false),
			new ToolProperty(PigConstants.ALIAS,"",PropertyInputType.TEXT,null,"Variable name to get the loaded data and be used in subsequent sentences","",false)
			
	};
	

	public PigLoad() {
		super(NAME,
				PigLoadActor.class.getCanonicalName(),
				PigLoad.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(PigConstants.COLOR);

	}
	
	

}


