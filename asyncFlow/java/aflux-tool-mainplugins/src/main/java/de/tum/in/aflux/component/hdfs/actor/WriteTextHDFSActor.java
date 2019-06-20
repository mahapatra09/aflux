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
