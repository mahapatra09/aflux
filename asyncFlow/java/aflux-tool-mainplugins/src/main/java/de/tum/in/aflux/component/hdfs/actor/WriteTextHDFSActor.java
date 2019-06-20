

/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright 2019 Tanmaya Mahapatra
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

package de.tum.in.aflux.component.hdfs.actor;

import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.springframework.data.hadoop.store.output.TextFileWriter;
import org.springframework.data.hadoop.store.strategy.naming.StaticFileNamingStrategy;

import de.tum.in.aflux.component.files.model.FileToolData;
import de.tum.in.aflux.component.hdfs.HDFSConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class WriteTextHDFSActor extends AbstractAFluxActor {

	
	public WriteTextHDFSActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,1);
	}

	public WriteTextHDFSActor() {
		this("-1",null,null,null);
	}	
	
	TextFileWriter createTextFileWriter(String datasetBasepath,String fileName,boolean appendable) {
    	System.out.println("path:"+datasetBasepath);
        Path path = new Path(datasetBasepath);
        TextFileWriter textFileWriter = new TextFileWriter(this.getFluxEnvironment().getHadoopConfiguration(), path, null);
        // RollingFileNamingStrategy rollingFileNamingStrategy = new RollingFileNamingStrategy();
        // textFileWriter.setFileNamingStrategy(rollingFileNamingStrategy);
        StaticFileNamingStrategy fileNamingStrategy = new StaticFileNamingStrategy(fileName);
        textFileWriter.setFileNamingStrategy(fileNamingStrategy);
        textFileWriter.setAppendable(appendable);
        // textFileWriter.getOutputContext();
        return textFileWriter;
	}
	
	
	// Sending each string
	@Override
	protected void runCore(Object message) throws Exception {
		String basePath=this.getProperty(HDFSConstants.BASE_PATH);
		String fileName=this.getProperty(HDFSConstants.FILE_NAME);
		String stAppendable=this.getProperty(HDFSConstants.APPENDABLE);
		boolean appendable=stAppendable.toLowerCase().equals("true");
		TextFileWriter textFileWriter = createTextFileWriter(basePath,fileName,appendable); 
		if (message instanceof String) {
			textFileWriter.write((String) message);
		} else if (message instanceof List<?>) {
			textFileWriter.setAppendable(true);
			for (String messageElement:(List<String>) message) {
				textFileWriter.write(messageElement);
			}
		} else if (message instanceof FileToolData) {
			textFileWriter.write(((FileToolData) message).getContent());
		} else {
			textFileWriter.write(message.toString());
		}
		textFileWriter.flush();
		textFileWriter.close();
	}
}
