

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
