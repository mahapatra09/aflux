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

package de.tum.in.aflux.flux_engine.launcher;


import de.tum.in.aflux.flux_engine.FluxEnvironment;

/**
 * 
 * Complete FluxEnvironment
 * 
 * Environment that contains all shared data between flows and executions
 * Differet from Flux Environment this interface is intended to have ALL shared resources
 * FluxEnvironment different from this should have only methods accesibles by actor elements
 * Any method that is considered should be accessible by actors should be moved to FluxEnvironmnet
 * 
 * @author Tanmaya Mahapatra
 *
 */
public interface FluxCompleteEnvironment extends FluxEnvironment{
	
	public final static String INACTIVE_STATUS="INACTIVE";
	public final static String RUNNING_STATUS="RUNNING";

	public String getStatus();
	public void setStatus(String status);
	public void setHadoopConfiguration(org.apache.hadoop.conf.Configuration hadoopConfiguration) ;
	public void setVariable(String id, Long id2, String fluxId, String scope, String key, Object value);
	public Object getVariable(String id, Long id2, String fluxId, String scope, String key);
	public void removeVariable(String id, Long id2, String fluxId, String scope, String key);


}
