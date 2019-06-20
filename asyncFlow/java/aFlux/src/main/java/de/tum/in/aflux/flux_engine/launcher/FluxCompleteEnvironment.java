

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
