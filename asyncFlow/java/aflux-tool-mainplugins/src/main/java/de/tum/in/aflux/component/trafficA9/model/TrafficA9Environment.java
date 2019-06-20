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

package de.tum.in.aflux.component.trafficA9.model;

import java.util.AbstractMap;
import java.util.Map;

public class TrafficA9Environment {
	Map.Entry<String,Boolean> knobs=new AbstractMap.SimpleEntry<String,Boolean>("forever",true);
	int ignoreFirstNResults=0;
	int sampleSize=60;
	public Map.Entry<String, Boolean> getKnobs() {
		return knobs;
	}
	public void setKnobs(Map.Entry<String, Boolean> knobs) {
		this.knobs = knobs;
	}
	public int getIgnoreFirstNResults() {
		return ignoreFirstNResults;
	}
	public void setIgnoreFirstNResults(int ignoreFirstNResults) {
		this.ignoreFirstNResults = ignoreFirstNResults;
	}
	public int getSampleSize() {
		return sampleSize;
	}
	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	
	
	
}
