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

import de.tum.in.aflux.component.pig.actor.PigExecuteActor;
import de.tum.in.aflux.component.pig.actor.PigScriptActor;
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
public class PigScript extends AbstractMainExecutor {
	public static final String NAME="Pig Script";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(PigConstants.SCRIPT,"",PropertyInputType.TEXT,null,"Script source code","",false)
	};
	

	public PigScript() {
		super(NAME,
				PigScriptActor.class.getCanonicalName(),
				PigScript.class.getName(),
				1, 
				2,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(PigConstants.COLOR);

	}
	
	
	
}
