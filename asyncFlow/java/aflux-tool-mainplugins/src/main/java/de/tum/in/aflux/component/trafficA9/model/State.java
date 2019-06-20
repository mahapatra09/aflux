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
