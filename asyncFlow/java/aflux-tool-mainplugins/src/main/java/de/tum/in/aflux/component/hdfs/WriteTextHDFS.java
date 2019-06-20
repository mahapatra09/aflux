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
