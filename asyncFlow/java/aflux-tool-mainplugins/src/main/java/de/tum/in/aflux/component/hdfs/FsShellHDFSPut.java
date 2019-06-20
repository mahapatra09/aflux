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

package de.tum.in.aflux.component.hdfs;

import de.tum.in.aflux.component.hdfs.actor.FsShellHDFSPutActor;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class FsShellHDFSPut extends AbstractMainExecutor {
	public static final String NAME="HDFS put";

	static public ToolProperty[] connectionProperties={
			new ToolProperty(HDFSConstants.SOURCE_PATH,"",PropertyInputType.TEXT,null,"Local Source Path in HDFS FileSystem","",false),
			new ToolProperty(HDFSConstants.SOURCE_FILE_NAME,"",PropertyInputType.TEXT,null,"Local ource file name in HDFS File System","",false),
			new ToolProperty(HDFSConstants.TARGET_PATH,"",PropertyInputType.TEXT,null,"Local Target HDFS Path","",false),
			new ToolProperty(HDFSConstants.TARGET_FILE_NAME,"",PropertyInputType.TEXT,null,"Target HDFS File Name","",false)
	};
	

	public FsShellHDFSPut() {
		super(NAME,
				FsShellHDFSPutActor.class.getCanonicalName(),
				FsShellHDFSPut.class.getName(),
				1, 
				1,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				false,
				connectionProperties);
		this.setColor(HDFSConstants.COLOR);

	}
	

}
