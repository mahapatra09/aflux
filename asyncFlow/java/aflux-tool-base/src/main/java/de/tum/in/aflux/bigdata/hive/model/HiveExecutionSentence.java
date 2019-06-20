

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
