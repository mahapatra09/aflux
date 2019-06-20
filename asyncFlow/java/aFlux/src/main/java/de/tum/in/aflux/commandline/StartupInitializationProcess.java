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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class StartupInitializationProcess {
	
	@Autowired
	FlowElementTypeDatabaseLoader fetLoader;
	@Autowired
	FlowJobMongoDatabaseLoader mongoLoader;
	@Autowired
	FlowSettingDatabaseLoader	fsLoader;
	@Autowired
	HadoopTestLoader	hadoopLoader;
	


	@PostConstruct
	public void runInitProcess() throws Exception {
	 	hadoopLoader.run();
		fsLoader.run(null);
		fetLoader.run(null);
		mongoLoader.run(null);
	}
	
	
}
