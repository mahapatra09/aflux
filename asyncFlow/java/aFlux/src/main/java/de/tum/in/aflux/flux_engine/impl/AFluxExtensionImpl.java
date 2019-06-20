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