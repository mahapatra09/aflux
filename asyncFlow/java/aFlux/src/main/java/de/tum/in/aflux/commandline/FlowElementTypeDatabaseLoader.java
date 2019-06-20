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
