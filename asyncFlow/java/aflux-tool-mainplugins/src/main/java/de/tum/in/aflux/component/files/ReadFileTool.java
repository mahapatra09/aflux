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
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

/**
 * 
 * 
 * Tool that reads a file and appends the content to a target file
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class ReadFileTool extends AbstractMainExecutor {

	public static final String NAME="Read File";

	static public ToolProperty[] connectionProperties={
				new ToolProperty(FilesToolConstants.SOURCE_FOLDER,"",PropertyInputType.TEXT,null,"Source folder in the server machine (does not end with /)","",false),
				new ToolProperty(FilesToolConstants.SOURCE_NAME,"",PropertyInputType.TEXT,null,"Source Name file","",false),
				new ToolProperty(FilesToolConstants.TARGET_NAME,"",PropertyInputType.TEXT,null,"Proposed output name","",false),
				new ToolProperty(FilesToolConstants.FORMAT,"CSV",PropertyInputType.TEXT,null,"Options are: CSV, JSON, TXT","",false),
				new ToolProperty(FilesToolConstants.ENCODING,"UTF-8",PropertyInputType.TEXT,null,"encoding","",false)
	};
	

	public ReadFileTool() {
		super(NAME,
				ReadFileActor.class.getCanonicalName(),
				ReadFileTool.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor("#F48FB1");
	}
}
