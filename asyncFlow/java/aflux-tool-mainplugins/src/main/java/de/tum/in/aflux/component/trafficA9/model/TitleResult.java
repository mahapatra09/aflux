

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

package de.tum.in.aflux.component.trafficA9.model;

public class TitleResult {
	String firstColumn="time";
	String secondColumn="result";
	String mainFolder="";
	String jobFolder="";
	
	
	
	
	public TitleResult(String mainFolder,String jobFolder) {
		super();
		this.mainFolder=mainFolder;
		this.jobFolder=jobFolder;
	}
	public String getFirstColumn() {
		return firstColumn;
	}
	public void setFirstColumn(String firstColumn) {
		this.firstColumn = firstColumn;
	}
	public String getSecondColumn() {
		return secondColumn;
	}
	public void setSecondColumn(String secondColumn) {
		this.secondColumn = secondColumn;
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
	
	
}
