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

package de.tum.in.aflux.component.files;

import de.tum.in.aflux.component.files.actor.ReadFileActor;
import de.tum.in.aflux.component.files.actor.WriteFileActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;


/**
 * Component tool that receives Strings and writes them to a specified text file adding eol
 * @author Tanmaya Mahapatra
 *
 */
public class WriteFileTool extends AbstractMainExecutor {
	public static final String NAME="Write File";

	static public ToolProperty[] connectionProperties={
				new ToolProperty(FilesToolConstants.TARGET_NAME,"",PropertyInputType.TEXT,null,"Proposed output name","",false),
				new ToolProperty(FilesToolConstants.ENCODING,"UTF-8",PropertyInputType.TEXT,null,"encoding","",false)
	};
	

	public WriteFileTool() {
		super(NAME,
				WriteFileActor.class.getCanonicalName(),
				WriteFileTool.class.getName(),
				1, 
				0,
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor("#F48FB1");
	}

}
