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

package de.tum.in.aflux.component.files.actor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import de.tum.in.aflux.component.files.FilesToolConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class WriteFileActor extends AbstractAFluxActor{
	public WriteFileActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}
	
	public WriteFileActor() {
		this("-1",null,null,null);
	}
	
	// Sending each string
	@Override
	protected void runCore(Object message) throws Exception {
		String finalTargetName=this.getProperty(FilesToolConstants.TARGET_NAME);
		String stringToAppend;
		if (message==null) {
			stringToAppend="\n";
		} else {
			stringToAppend=message.toString();
		}
		if (!stringToAppend.endsWith("\n")) {
			stringToAppend+="\n";
		}
		Path path=Paths.get(finalTargetName);
		Path parent=path.getParent();
		
		if (parent!=null) {
			if (!Files.exists(parent)) {
			    Files.createDirectories(parent);
			}
		}
		if (!Files.exists(path)) {
			File  f=path.toFile();
			f.createNewFile();
		}
	    Files.write(path, stringToAppend.getBytes(), StandardOpenOption.APPEND);
	}



}
