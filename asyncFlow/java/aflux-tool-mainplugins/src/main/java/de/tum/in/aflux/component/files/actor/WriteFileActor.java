

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
