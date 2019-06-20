

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import de.tum.in.aflux.component.files.FilesToolConstants;
import de.tum.in.aflux.component.files.model.FileToolData;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class ReadFileActor  extends AbstractAFluxActor {

	
	public ReadFileActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}
	
	
	
	public ReadFileActor() {
		this("-1",null,null,null);
	}
	
	// Sending each string
	@Override
	protected void runCore(Object message) throws Exception {
		String fullFileName=
					this.getProperty(FilesToolConstants.SOURCE_FOLDER)+"/"+
					this.getProperty(FilesToolConstants.SOURCE_NAME);
		String finalTargetName=this.getProperty(FilesToolConstants.SOURCE_NAME);
		String targetName=this.getProperty(FilesToolConstants.TARGET_NAME);
		String everything="";
		if (targetName!=null && targetName.length()>0) {
			finalTargetName=targetName;
		}
		this.sendOutput("Reading from file:"+fullFileName);
		BufferedReader br = new BufferedReader(new FileReader(fullFileName));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} catch (Exception e) {
			this.sendOutput(e.getMessage());
		} finally {
		    br.close();
		}
		FileToolData result=new FileToolData(finalTargetName,everything);
		this.setOutput(1, result);
	}



	
}
