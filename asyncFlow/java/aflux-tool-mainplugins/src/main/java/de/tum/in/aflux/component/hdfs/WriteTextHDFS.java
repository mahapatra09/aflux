

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

package de.tum.in.aflux.component.hdfs;

import de.tum.in.aflux.component.hdfs.actor.WriteTextHDFSActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class WriteTextHDFS extends AbstractMainExecutor {
	public static final String NAME="write text HDFS";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(HDFSConstants.BASE_PATH,"",PropertyInputType.TEXT,null,"Source Name file","",false),
			new ToolProperty(HDFSConstants.FILE_NAME,"",PropertyInputType.TEXT,null,"Source folder in the server machine (does not end with /)","",false),
			new ToolProperty(HDFSConstants.INPUT_FORMAT,"CSV",PropertyInputType.TEXT,null,"Proposed output name","",false),
			new ToolProperty(HDFSConstants.OUTPUT_FORMAT,"CSV",PropertyInputType.TEXT,null,"Options are: CSV, JSON, TXT","",false),
			new ToolProperty(HDFSConstants.APPENDABLE,"true",PropertyInputType.TEXT,null,"Options are: true, false","",false),
			new ToolProperty(HDFSConstants.ENCODING,"UTF-8",PropertyInputType.TEXT,null,"encoding","",false)
	};
	

	public WriteTextHDFS() {
		super(NAME,
				WriteTextHDFSActor.class.getCanonicalName(),
				WriteTextHDFS.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_DATA,
				false,
				connectionProperties);
		this.setColor(HDFSConstants.COLOR);

	}
	
}
