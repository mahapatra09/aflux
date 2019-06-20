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

public class TrafficA9Definition {
	int experimentCounter=0;
	int totalExperiments=0;
	


	public State stateInitializer(State state) {
		if (state==null) {
			state=new State();
		}
		state.setOverallTickCount(0);
		state.setTickCount(0);
		state.setOccurrences(new Occurrences());
		state.getOccurrences().setLower(0);
		state.setOccurrencesCount(new Occurrences());
		state.getOccurrences().setLower(0);
		return state;
	}
	
	public void incrementExperimentCounter() {
		this.experimentCounter++;
	}

	public int getTotalExperiments() {
		return totalExperiments;
	}

	public void setTotalExperiments(int totalExperiments) {
		this.totalExperiments = totalExperiments;
	}

	public int getExperimentCounter() {
		return experimentCounter;
	}

	public void setExperimentCounter(int experimentCounter) {
		this.experimentCounter = experimentCounter;
	}
	
	
	
}
