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
