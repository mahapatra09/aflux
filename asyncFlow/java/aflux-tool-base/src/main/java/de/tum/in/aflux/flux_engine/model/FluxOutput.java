
/*
 * aFlux: JVM based IoT Mashup Tool
 * Copyright [2019] [Tanmaya Mahapatra]
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

package de.tum.in.aflux.flux_engine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Class to expose output of running actors
 * messages to outpout can be sent by calling sendMessage()
 * @author Tanmaya Mahapatra
 *
 */
public class FluxOutput {
	
	
	private ConcurrentLinkedQueue<PrintItem> outputList=new ConcurrentLinkedQueue<PrintItem>();

	public ConcurrentLinkedQueue<PrintItem> getOutputList() {
		return outputList;
	}

	public void sendMessage(String fluxId,String message) {
		outputList.add(new PrintItem(fluxId,message));
	} 
	
	
	public List<String> getStringMessages() {
			List<String> result=new ArrayList<String>();
			while (!getOutputList().isEmpty()) {
				result.add(getOutputList().poll().toString());
			}
			return result;
	}
}
