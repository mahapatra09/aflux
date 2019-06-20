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
