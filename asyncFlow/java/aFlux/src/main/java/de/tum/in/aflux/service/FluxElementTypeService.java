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

package de.tum.in.aflux.service;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import de.tum.in.aflux.dao.FlowElementTypeRepository;
import de.tum.in.aflux.model.FlowElementBaseProperties;
import de.tum.in.aflux.model.FlowElementProperty;
import de.tum.in.aflux.model.FlowElementType;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.model.SubFlow;
import de.tum.in.aflux.tools.core.ToolImplementationType;



/**
 * Related tool operations
 * @author Tanmaya Mahapatra
 *
 */
@Service
public class FluxElementTypeService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	@Autowired
	private FlowElementTypeRepository repository;
	@Autowired
	private FluxPluginService pluginService;
	
	@Autowired
	private SubFluxService subFluxService;


	/**
	 * Persist a tool
	 * @param elementType
	 * @param isNew
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
    public FlowElementType save(FlowElementType elementType,boolean isNew) {
    	if (isNew) {
    		elementType.setId(null);
    	}
    	FlowElementType result = repository.save(elementType);
		return result;
    }
	
    
    /**
     * Get tool by its unique name
     * @param name
     * @return
     * 
     * @author Tanmaya Mahapatra
     */
    public  FlowElementType getByName(String name) {
    	FlowElementType flowElementBase=new FlowElementType();
    	flowElementBase.setName(name);
		Example<FlowElementType> example = Example.of(flowElementBase);
		FlowElementType elementType=repository.findOne(example);    	
		return elementType;
    }	
	
    
    /**
     * Get all available tools in repository
     * @return
     * 
     * 
     * @author Tanmaya Mahapatra
     */
	public List<FlowElementType> findAll() {
		List<FlowElementType> result=repository.findAll();
		return result;
	}


	/**
	 * Erase a jar and deactivate it for next restart
	 * @param jarLocation
	 * @param jarName
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public List<String> remove(String jarLocation, String jarName) {
		List<String> removedClasses=new ArrayList<String>();
		List<FlowElementType> toolList=repository.findAll();
		for (FlowElementType tool:toolList) {
			if (tool.getJarLocation()!=null && tool.getJarName()!=null) {
				if (tool.getJarLocation().equals(jarLocation) && tool.getJarName().equals(jarName)) {
					repository.delete(tool);
					removedClasses.add(tool.getClassName());
				}
			}
		}
		return removedClasses;
	}

	
	/**
	 * 
	 * Persist a set of tools available in a jar
	 * and update from the jar the existent persisted tools
	 * 
	 * @param jarLocation
	 * @param jarName
	 * @param classNames
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws URISyntaxException
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public List<String> add(String jarLocation, String jarName, List<String> classNames) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, URISyntaxException {
		for (String className:classNames) {
			FlowElementType tool=repository.findByJarLocationAndJarNameAndClassName(jarLocation, jarName, className);
			if (tool!=null) {
				tool.update();
			} else {
				tool=new FlowElementType(jarLocation,jarName,className);
			}
			repository.save(tool);
		}
		return classNames;
	}
	
	/**
	 * Get all Subflows declared in Jobs and transform to Tools
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private List<FlowElementType> getGlobalSubflows() {
		List<SubFlow> subFlowList=subFluxService.findAll();
		return generateTools(subFlowList);
	}
	
	
	/**
	 * 
	 * Generate tools based on subflows definition to let be available global subflows in toolbar
	 * SubFlows are defined setting true to subFlow property of any activity
	 * 
	 * @param subFlowList
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private List<FlowElementType> generateTools(List<SubFlow> subFlowList) {
		List<FlowElementType> toolList=new ArrayList<FlowElementType>();
		for (SubFlow subFlow:subFlowList) {
			FlowElementType tool=new FlowElementType(subFlow);
			toolList.add(tool);
		}
		return toolList;
	}
	
	/**
	 * 
	 * Persist a new list of tools on repository removing the old one
	 * 
	 * @param previousToolList
	 * @param toolList
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private void updateGlobalSubFlowsTools(List<FlowElementType> previousToolList,
			List<FlowElementType> toolList) {
		Map<String,FlowElementType> toolMap=new HashMap<String,FlowElementType>();
		for (FlowElementType tool:toolList) {
			toolMap.put(tool.getName(), tool);
		}
		// remove or update tools in database
		for (FlowElementType tool:previousToolList) {
			FlowElementType newTool=toolMap.get(tool.getName());
			if (newTool==null) {
				repository.delete(tool);
			} else {
				newTool.setId(tool.getId());
				repository.save(newTool);
				toolMap.remove(tool.getName());
			}
		}
		// In map remains only the new Global tools
		toolMap.forEach((i,t)->{
			repository.save(t);
		});
		
	}
	
	/**
	 * 
	 * Update all persisted subflows - synchronize tools information
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public void updateGlobalSubFlowsTools() {
		List<FlowElementType> previousToolList=repository.findByType(ToolImplementationType.SUBFLOW);
		List<FlowElementType> onlyPreviousBuiltFlows=new ArrayList<FlowElementType>();
		for (FlowElementType type:previousToolList) {
			if (type!=null && type.getClassName()==null) {
				onlyPreviousBuiltFlows.add(type);
			}
		}
		List<FlowElementType> toolList=getGlobalSubflows();
		updateGlobalSubFlowsTools(onlyPreviousBuiltFlows,toolList);
		
	}

	/**
	 * Update all global subflows synchronizing tools information for a specified job
	 * @param job
	 * @param previousName
	 */
	public void updateGlobalSubFlows(FlowJob job, String previousName) {
		List<FlowElementType> previousTools=findByJobName(previousName);
		List<SubFlow> currentSubFlows=subFluxService.getSubFlows(job);
		List<FlowElementType> newTools=generateTools(currentSubFlows);
		updateGlobalSubFlowsTools(previousTools,newTools);
	}


	
	public FlowElementProperty[] getElementBaseProperties() {
    	return FlowElementBaseProperties.generate("",0,"",1);
	}
	public List<FlowElementType> findByJobName(String jobName) {
		return repository.findByJobName(jobName);
	}

}
