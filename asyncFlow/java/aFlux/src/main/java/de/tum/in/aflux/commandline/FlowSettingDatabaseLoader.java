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
