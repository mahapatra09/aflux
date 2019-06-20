

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
