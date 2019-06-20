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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.tum.in.aflux.model.FlowPlugin;
import de.tum.in.aflux.service.FluxPluginService;

/**
 * class to manage plugin related requests
 * @author Tanmaya Mahapatra
 *
 */
@CrossOrigin(origins="http://localhost:3000")
@Controller
public class FluxPluginController {

	@Autowired
	private FluxPluginService service;
	
	@GetMapping(value="/plugins")	
    public  @ResponseBody List<FlowPlugin> getAll() {
		List<FlowPlugin> result=service.findAll();
		return result;
    }
	
	@PostMapping(value = "/plugin/activate/{id}")
    public  @ResponseBody List<String> activate(@PathVariable String id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, URISyntaxException, IOException {
    	List<String> result=service.activate(id);
		return result;
    }
    
    @PostMapping(value = "/plugin/deactivate/{id}")
    public  @ResponseBody List<String> deactivate(@PathVariable String id) {
		List<String> result=service.deactivate(id);    	
		return result;
    }

	
    @PostMapping(value = "/plugin/remove/{id}")
    public @ResponseBody List<String> remove(@PathVariable String id) {
    	List<String> result = service.remove(id);
		return result;
    }
    
    
    @PostMapping("/plugin/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {

        service.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }   
    
   
	
}
