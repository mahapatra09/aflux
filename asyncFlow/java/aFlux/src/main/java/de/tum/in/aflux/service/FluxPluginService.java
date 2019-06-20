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

package de.tum.in.aflux.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import de.tum.in.aflux.dao.FlowPluginRepository;
import de.tum.in.aflux.model.FlowPlugin;
import de.tum.in.aflux.plugin.util.PluginLoader;
import de.tum.in.aflux.util.AFluxUtils;
import de.tum.in.aflux.util.FileSystemUtil;
/**
 * Service to manage plugins.
 * Plugins contains the set of tools that will be available in aFlux
 * 
 * @author Tanmaya Mahapatra
 * 
 *
 *
 */
@Service
public class FluxPluginService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());		
	
	@Autowired
	private FlowPluginRepository repository;
	
	@Autowired
	private FluxElementTypeService toolService;

	/**
	 * Should fail on activate all the plugin if there is any class that already has a tool
	 * It loads using main Classloader all tools and actors present in each JAR specified in flowPlugin collection in database
	 * @param id
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InstantiationException
	 * 
	 *  @author Tanmaya Mahapatra
	 */
	public void activateOnInit(String id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, URISyntaxException, IOException, InstantiationException {
		FlowPlugin pluginToChange=repository.findOne(id);
		if (pluginToChange!=null) {
			if (pluginToChange.getActivated()) {
				// it should not allow activate if there is any tool corresponding to another jar
				
				pluginToChange.setDynamicActivation(false);
				repository.save(pluginToChange);
				// load jar classes
				// Load jars in classLoader
				List<String> classNames=PluginLoader.loadJar(pluginToChange);
				// upload toolbar
				toolService.add(pluginToChange.getJarLocation(),pluginToChange.getJarName(),classNames);
			} else { // inactive
				if (!pluginToChange.getDynamicActivation()) {
					pluginToChange.setDynamicActivation(true);
					repository.save(pluginToChange);
				}
			}
		}
	} 
	
	
	/**
	 * Activate a plugin
	 * 
	 * When a plugin is activated all related classes inside the jar are loaded in the classloader and all tools are available in the application
	 * 
	 * If there is in the jar a class that has be already present in classloader corresponding to a tool or an actor the plugins is not activated
	 * Also by now cannot be activated a plugin deactivated in the current session
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InstantiationException
	 * 
	 *  @author Tanmaya Mahapatra
	 *  
	 *  
	 */
	public List<String> activate(String id) throws 
			NoSuchMethodException, 
			SecurityException, 
			IllegalAccessException, 
			IllegalArgumentException, 
			InvocationTargetException, 
			ClassNotFoundException, 
			URISyntaxException, 
			IOException, 
			InstantiationException {
		List<String> result=new ArrayList<String>();
		FlowPlugin pluginToChange=repository.findOne(id);
		if (pluginToChange!=null) {
			if (!pluginToChange.getActivated() && pluginToChange.getDynamicActivation()) {
				List<String> existentClassNames=PluginLoader.checkExecutorsList(FileSystemUtil.getUploadBaseDir()+pluginToChange.getJarLocation()+pluginToChange.getJarName());
				if (existentClassNames.size()==0) {
					pluginToChange.setActivated(true);
					pluginToChange.setDynamicActivation(false);
					repository.save(pluginToChange);
					// load jar classes
					// Load jars in classLoader
					List<String> classNames=PluginLoader.loadJar(pluginToChange);
					// upload toolbar
					result=toolService.add(pluginToChange.getJarLocation(),pluginToChange.getJarName(),classNames);
				} else {
					log.info("Failed to load plugin:"+id);
					log.info("Previously existent classes:"+existentClassNames.toString());
				}
			}
		}
		return result;
	}
	
	public List<FlowPlugin> findAll() {
		List<FlowPlugin> result=repository.findAll();
		return result;
	}
	
	public void register(String location,String name) {
		FlowPlugin flowPlugin=new FlowPlugin(location,name);
		repository.save(flowPlugin);
	}

	/**
	 * Deactivate plugin 
	 * It removes all related tools from app but does not remove from classloader.
	 * To remove classes from java classloader application should be restarted
	 * 
	 * A deactivated plugin only can be reactivated after reinit the application
	 * 
	 * 
	 * @param id
	 * @return
	 */
	public List<String> deactivate(String id) {
		List<String> result=new ArrayList<String>();
		FlowPlugin pluginToChange=repository.findOne(id);
		if (pluginToChange!=null) {
			if (pluginToChange.getActivated()) {
				pluginToChange.setActivated(false);
				pluginToChange.setDynamicActivation(false);
				repository.save(pluginToChange);
				// unload jar classes
				// upload toolbar
				// remove all tools from Database
				result=toolService.remove(pluginToChange.getJarLocation(),pluginToChange.getJarName());
			}
		}
		return result;
	}
	
	
	public void store(MultipartFile file) throws IllegalStateException, IOException {
		if (!file.isEmpty()) {
			String uploadBaseDir=FileSystemUtil.getUploadBaseDir();
			String jarLocation=AFluxUtils.getNowId()+"/";
			String fileDir=uploadBaseDir+jarLocation;
	        String originalName = file.getOriginalFilename();
	        if(! new File(fileDir).exists())
	        {
	            new File(fileDir).mkdir();
	        }			
	        String filePath = fileDir + originalName;
	        File dest = new File(filePath);
	        file.transferTo(dest);
	        register(jarLocation,originalName);
		}
	}


	/**
	 * Remove plugin and delete related file from filesystem
	 * @param id
	 * @return
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public List<String> remove(String id) {
		List<String> result = new ArrayList<String>();
		FlowPlugin flowPlugin=repository.findOne(id);
		if (flowPlugin!=null) {
			deactivate(id);
			String fileName=FileSystemUtil.getBaseDir()+flowPlugin.getJarLocation()+flowPlugin.getJarName();
    		File file = new File(fileName);
    		file.delete();
			repository.delete(id);
		}
		return result;
	}

	
	
}
