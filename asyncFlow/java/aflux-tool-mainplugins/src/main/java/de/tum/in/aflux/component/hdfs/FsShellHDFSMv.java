

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

import de.tum.in.aflux.component.hdfs.actor.FsShellHDFSGetActor;
import de.tum.in.aflux.component.hdfs.actor.FsShellHDFSMvActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class FsShellHDFSMv extends AbstractMainExecutor {
	public static final String NAME="HDFS mv";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(HDFSConstants.SOURCE_PATH,"",PropertyInputType.TEXT,null,"Source Path in HDFS FileSystem","",false),
			new ToolProperty(HDFSConstants.SOURCE_FILE_NAME,"",PropertyInputType.TEXT,null,"Source file name in HDFS File System","",false),
			new ToolProperty(HDFSConstants.TARGET_PATH,"",PropertyInputType.TEXT,null,"Local Target Path","",false),
			new ToolProperty(HDFSConstants.TARGET_FILE_NAME,"",PropertyInputType.TEXT,null,"Local File Name","",false)
	};
	

	public FsShellHDFSMv() {
		super(NAME,
				FsShellHDFSMvActor.class.getCanonicalName(),
				FsShellHDFSMv.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(HDFSConstants.COLOR);

	}
	

}
