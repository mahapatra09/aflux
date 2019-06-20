

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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// removed import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.tum.in.aflux.dao.FlowSettingRepository;
import de.tum.in.aflux.model.FlowSetting;
import de.tum.in.aflux.model.NodeElementSetting;

/**
 * 
 * 
 * Settings initialization
 * 
 * @author Tanmaya Mahapatra
 *
 */
@Component
public class FlowSettingDatabaseLoader // removed implements CommandLineRunner,Ordered
{
	
	private final FlowSettingRepository repository;	
	//private final NodeElementSettingRepository nodeElementRepository;
	
	@Autowired
	public FlowSettingDatabaseLoader(FlowSettingRepository repository) {
		this.repository=repository;
		// this.nodeElementRepository=nodeElementRepository;
	}	
	
	// removed @Override
	public int getOrder() {
		return 10; 
	}
	

	
	
	// removed @Override
	public void run(String... arg0) throws Exception {
		System.out.println("To initialize Database set FlowSetting.initializeDatabase in true in mongodb database: THIS OPTION WILL ERASE ALL SAVED DATA");
		List<FlowSetting> settings=this.repository.findAll();
		boolean initializeDatabase=true;
		if (settings!=null && settings.size()>0) {
			initializeDatabase=settings.get(0).isInitalizeDatabase();
		}
		if (initializeDatabase) {
			this.repository.deleteAll();
			FlowSetting newDocument=new FlowSetting(new NodeElementSetting(120,28,10,10,10,10,15,12));
			newDocument.setInitalizeDatabase(true);
			this.repository.save(newDocument);
		}
	}

}
