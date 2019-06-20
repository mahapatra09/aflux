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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tum.in.aflux.dao.FlowSettingRepository;
import de.tum.in.aflux.model.FlowSetting;

/**
 * Settings related operations
 * @author Tanmaya Mahapatra
 * 
 *
 */
@Service
public class FluxSettingsService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	@Autowired
	private FlowSettingRepository repository;


	
    public FlowSetting save(FlowSetting settings) {
    	FlowSetting result = repository.save(settings);
		return result;
    }
	
	
	
    public  FlowSetting getSettings() {
    	List<FlowSetting> settingsList=repository.findAll();
    	return settingsList.get(0); 
    }	
	
}
