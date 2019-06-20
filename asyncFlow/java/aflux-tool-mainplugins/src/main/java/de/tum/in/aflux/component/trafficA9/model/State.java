

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

public class State {
	int overallTickCount=0;
	int tickCount=0;
	Occurrences occurrences=new Occurrences();
	Occurrences occurrencesCount = new Occurrences();
	
	int speedsCount=0;
	float speedsAvg=0;
	
	public State() {
		this.occurrences.setLower(0);
		this.occurrencesCount.setLower(0);
	}

	public int getOverallTickCount() {
		return overallTickCount;
	}

	public void setOverallTickCount(int overallTickCount) {
		this.overallTickCount = overallTickCount;
	}

	public int getTickCount() {
		return tickCount;
	}

	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

	public Occurrences getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Occurrences occurrences) {
		this.occurrences = occurrences;
	}

	public Occurrences getOccurrencesCount() {
		return occurrencesCount;
	}

	public void setOccurrencesCount(Occurrences occurrencesCount) {
		this.occurrencesCount = occurrencesCount;
	}

	public int getSpeedsCount() {
		return speedsCount;
	}

	public void setSpeedsCount(int speedsCount) {
		this.speedsCount = speedsCount;
	}

	public float getSpeedsAvg() {
		return speedsAvg;
	}

	public void setSpeedsAvg(float speedsAvg) {
		this.speedsAvg = speedsAvg;
	}

	@Override
	public String toString() {
		return "State [overallTickCount=" + overallTickCount + ", tickCount=" + tickCount + ", occurrences="
				+ occurrences + ", occurrencesCount=" + occurrencesCount  
				+ ", speedsCount=" + speedsCount + ", speedsAvg=" + speedsAvg + "]";
	}
	
	

	
	
	
}
