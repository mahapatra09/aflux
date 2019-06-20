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
