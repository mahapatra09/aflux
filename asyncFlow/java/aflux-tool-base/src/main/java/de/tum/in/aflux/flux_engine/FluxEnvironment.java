
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

package de.tum.in.aflux.flux_engine;


import org.springframework.data.hadoop.fs.FsShell;
import de.tum.in.aflux.flux_engine.model.FluxOutput;
import org.springframework.data.hadoop.pig.PigOperations;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Interface defined to access the shared resources of the running environment that actors (elements) can use 
 * @author Tanmaya Mahapatra
 *
 */
public interface FluxEnvironment {
	
	public FluxOutput getOutput();
	public void showMessage(String fluxId,String message);
	public FsShell getFsShell();
	public PigOperations getPigTemplate();
	public JdbcTemplate getHiveTemplate();
	

	/**
	 * @deprecated
	 * 
	 */
	public org.apache.hadoop.conf.Configuration getHadoopConfiguration();
	
	
	
	/**
	 * 
	 * @return the hdfs hadoop service 
	 */
	// TODO: To be reactivated public HDFSOperationsService getHDFSOperations();

}
