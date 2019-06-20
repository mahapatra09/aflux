

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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tum.in.aflux.model.FlowElementBaseProperties;
import de.tum.in.aflux.model.FlowElementProperty;
import de.tum.in.aflux.model.FlowElementType;
import de.tum.in.aflux.service.FluxElementTypeService;
/**
 * Class to manage request related with tools
 * @author Tanmaya Mahapatra
 *
 */
@CrossOrigin(origins="http://localhost:3000")
@Controller
public class FluxElementTypeController {

	@Autowired
	private FluxElementTypeService service;	
	

	
    @RequestMapping(value = "/elementTypes")
    public  @ResponseBody List<FlowElementType> getAll() {
		List<FlowElementType> result=service.findAll();
		return result;
    }	
    
    @RequestMapping(value = "/elementTypes/{name}")
    public  @ResponseBody FlowElementType getByName(@PathVariable String name) {
    	FlowElementType elementType=service.getByName(name);    	
		return elementType;
    }
    
    @RequestMapping(value="/elementBaseProperties")
    public @ResponseBody FlowElementProperty[] getElementBaseProperties() {
    	return service.getElementBaseProperties();
    }
    
    
    
	
}
