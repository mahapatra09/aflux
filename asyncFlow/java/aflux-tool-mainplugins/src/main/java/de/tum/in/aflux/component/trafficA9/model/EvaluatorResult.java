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

public class EvaluatorResult {
	long overallTicks=0;
	float product=0;
	String mainFolder="";
	String jobFolder="";
	int opens=0;
	int closes=0;
	
	
	
	public EvaluatorResult(String mainFolder,String jobFolder,long overallTicks, float product,int opens, int closes) {
		super();
		this.overallTicks = overallTicks;
		this.product = product;
		this.mainFolder=mainFolder;
		this.jobFolder=jobFolder;
		this.opens=opens;
		this.closes=closes;
	}
	
	public long getOverallTicks() {
		return overallTicks;
	}
	public void setOverallTicks(long overallTicks) {
		this.overallTicks = overallTicks;
	}
	public float getProduct() {
		return product;
	}
	public void setProduct(float product) {
		this.product = product;
	}

	
	
	
	public String getMainFolder() {
		return mainFolder;
	}

	public void setMainFolder(String mainFolder) {
		this.mainFolder = mainFolder;
	}

	public String getJobFolder() {
		return jobFolder;
	}

	public void setJobFolder(String jobFolder) {
		this.jobFolder = jobFolder;
	}

	
	
	public int getOpens() {
		return opens;
	}

	public void setOpens(int opens) {
		this.opens = opens;
	}

	public int getCloses() {
		return closes;
	}

	public void setCloses(int closes) {
		this.closes = closes;
	}

	@Override
	public String toString() {
		return "EvaluatorResult [overallTicks=" + overallTicks + ", product=" + product + "]";
	}
	
	
	
	
}
