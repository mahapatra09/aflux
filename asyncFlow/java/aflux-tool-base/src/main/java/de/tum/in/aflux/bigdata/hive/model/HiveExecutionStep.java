

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

package de.tum.in.aflux.bigdata.hive.model;

import java.util.List;

public class HiveExecutionStep {
	String type;
	String alias;
	HiveExecutionSentence sentence;
	String modifiers;
	List<String> repetitiveExpressionList;
	

	public HiveExecutionStep(String type, String alias,HiveExecutionSentence sentence,String modifiers,List<String> repetitiveExpressionList) {
		this.type=type;
		this.alias=alias;
		this.sentence=sentence;
		this.modifiers=modifiers;
		this.repetitiveExpressionList=repetitiveExpressionList;
	}

	public HiveExecutionSentence getSentence() {
		return sentence;
	}

	public void setSentence(HiveExecutionSentence sentence) {
		this.sentence = sentence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}



	public List<String> getRepetitiveExpressionList() {
		return repetitiveExpressionList;
	}

	public void setRepetitiveExpressionList(List<String> repetitiveExpressionList) {
		this.repetitiveExpressionList = repetitiveExpressionList;
	}

	public String getModifiers() {
		return modifiers==null?"":modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers==null?"":modifiers;
	}

	@Override
	public String toString() {
		return "HiveExecutionStep [type=" + type + ", alias=" + alias + ", sentence=" + sentence + ", modifiers="
				+ modifiers + ", repetitiveExpressionList=" + repetitiveExpressionList + "]";
	}


	public String getFinalSentence() {
		String finalSentence=sentence.getSentence();
		if (modifiers!=null && modifiers.length()>0) {
			finalSentence+=modifiers;
		}
		finalSentence+=";";
		return finalSentence;
	}


}
