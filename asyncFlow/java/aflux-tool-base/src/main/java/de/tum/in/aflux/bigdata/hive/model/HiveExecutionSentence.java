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

package de.tum.in.aflux.bigdata.hive.model;

public class HiveExecutionSentence {
	private String sentence;
	private HiveSentenceType type;
	/**
	 * Property that indicates it should be created a new table as a result of the sentence execution
	 */
	private String newTableName; 
	
	
	public HiveExecutionSentence(String sentence, HiveSentenceType type,String newTableName) {
		super();
		this.sentence = sentence;
		this.type = type;
		this.newTableName=newTableName;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public HiveSentenceType getType() {
		return type;
	}
	public void setType(HiveSentenceType type) {
		this.type = type;
	}
	
	
	public String getNewTableName() {
		return newTableName;
	}
	public void setNewTableName(String newTableName) {
		this.newTableName = newTableName;
	}
	@Override
	public String toString() {
		return "HiveExecutionSentence [sentence=" + sentence + ", type=" + type + "newTableName="+newTableName+"]";
	}
	
	
	
	
}
