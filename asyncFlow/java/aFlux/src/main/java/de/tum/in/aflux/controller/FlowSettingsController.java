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
package de.tum.in.aflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import de.tum.in.aflux.model.FlowSetting;
import de.tum.in.aflux.service.FluxSettingsService;

/**
 * Class to get requests related with setting values
 * @author Tanmaya Mahapatra
 *
 */
@EnableWebMvc
@CrossOrigin(origins="http://localhost:3000")
@Controller
public class FlowSettingsController {
	
	@Autowired
	public FluxSettingsService service;
	
    @RequestMapping(value = "/settings")
    public  @ResponseBody FlowSetting getSettings() {
		FlowSetting result=service.getSettings();
		return result;
    }	
	

}
