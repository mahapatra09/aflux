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

package de.tum.in.aflux.component.strings;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.component.strings.actor.OutputToolActor;
import de.tum.in.aflux.tools.core.ToolProperty;

public class OutputTool extends AbstractMainExecutor{
	static public final String NAME="output string";
	
	static public final ToolProperty[] configurationProperties={new ToolProperty("value","",PropertyInputType.TEXT,null,"String value to show","",false)};
	public OutputTool() {
		super(NAME, OutputToolActor.class.getCanonicalName(), 
				OutputTool.class.getName() , 1, 1,NodeLaunchType.LAUNCHED_BY_SIGNAL, false,configurationProperties);
		this.setColor("#80DEEA");
	}



}
