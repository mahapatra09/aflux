
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

package de.tum.in.aflux.commandline;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// removed import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import de.tum.in.aflux.dao.FlowElementTypeRepository;
import de.tum.in.aflux.dao.FlowSettingRepository;
import de.tum.in.aflux.model.FlowPlugin;
import de.tum.in.aflux.model.FlowSetting;
import de.tum.in.aflux.service.FluxElementTypeService;
import de.tum.in.aflux.service.FluxPluginService;

/**
 * 
 * Class to load initially tools for aFlux
 * 
 * @author Tanmaya Mahapatra
 *
 */
@Component
public class FlowElementTypeDatabaseLoader // remove implements CommandLineRunner,Ordered  
{
	

	private final FlowElementTypeRepository repository;
	private final FlowSettingRepository settingsRepository;	
	private final FluxPluginService pluginService;
	private final FluxElementTypeService toolService;
	
	@Autowired
	public FlowElementTypeDatabaseLoader(
			FlowElementTypeRepository repository,
			FlowSettingRepository settingsRepository,
			FluxPluginService pluginService,
			FluxElementTypeService toolService) {
		this.repository=repository;
		this.settingsRepository=settingsRepository;
		this.pluginService=pluginService;
		this.toolService=toolService;
	}

	/**
	 * Initially load tools specified in plugins in database
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws URISyntaxException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private void loadTools() 
			throws ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				URISyntaxException, 
				NoSuchMethodException, 
				SecurityException, 
				IllegalArgumentException, 
				InvocationTargetException, 
				IOException {
		List<FlowPlugin> plugins=pluginService.findAll();
		for (FlowPlugin plugin:plugins) {
			pluginService.activateOnInit(plugin.getId());
		}
		toolService.updateGlobalSubFlowsTools();
		
		
	}
	
	/**
	 * 
	 * checks if should empty database and regenerate information to initialize all
	 * @return true if database should be initialized and false if not
	 */
	private boolean checkDatabaseInitialization() {
		List<FlowSetting> settings=this.settingsRepository.findAll();
		boolean initializeDatabase=false;
		if (settings!=null && settings.size()>0) {
			initializeDatabase=settings.get(0).isInitalizeDatabase();
		}
		return initializeDatabase;
	}
	
	
	// removed @Override
	public void run(String... args) throws Exception {
		boolean initializeDatabase=checkDatabaseInitialization();
		if (initializeDatabase) {
			this.repository.deleteAll();
		}	
		loadTools();
	}

	// removed @Override
	public int getOrder() {
		return 20;
	}

}
