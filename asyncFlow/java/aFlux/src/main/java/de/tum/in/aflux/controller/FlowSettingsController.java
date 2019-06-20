
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
