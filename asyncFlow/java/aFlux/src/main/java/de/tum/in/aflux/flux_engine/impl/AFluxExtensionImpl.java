

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

package de.tum.in.aflux.flux_engine.impl;

import akka.actor.Props;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.flux_engine.FluxExtension;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;

import java.net.URLClassLoader;
import java.util.Map;

/**
 * Helper class to create akka actors based on the name
 * To work classes must be loaded in classloader first. This initializtion is made based on plugins
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class AFluxExtensionImpl implements FluxExtension {

    public Props props(String actorClassName,String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner, Map<String, String> propertiesMap) throws ClassNotFoundException {
    		
	    final URLClassLoader sysloader = (URLClassLoader) AbstractMainExecutor.class.getClassLoader();
		Class classToLoad = Class.forName (actorClassName, true, sysloader);
    	
        return Props.create(classToLoad,fluxId,fluxEnvironment,fluxRunner,propertiesMap);
    }
}