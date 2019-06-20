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
