

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
