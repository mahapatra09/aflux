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

import java.util.ArrayList;
import java.util.List;


public class HiveExecutionPlan {
	@Override
	public String toString() {
		return "HiveExecutionPlan [script=" + script + ", steps=" + steps + "]";
	}



	String script;
	List<HiveExecutionStep> steps=new ArrayList<HiveExecutionStep>();

	public List<HiveExecutionSentence> getSentences() {
		List<HiveExecutionSentence> result=new ArrayList<HiveExecutionSentence>();
		if (this.script!=null) {
			result.add(new HiveExecutionSentence(this.script,HiveSentenceType.UPDATE,null));
		}
		if (this.steps!=null) {
			for (HiveExecutionStep step:steps) {
				String jdbcSentence=step.getSentence().getSentence();
				String semicolon=";";
				while (jdbcSentence.endsWith(";")) {
					jdbcSentence=jdbcSentence.substring(0, jdbcSentence.length()-1);
				}
				if (jdbcSentence.length()>0) {
					result.add(new HiveExecutionSentence(jdbcSentence,step.getSentence().getType(),step.getSentence().getNewTableName()));
				}
				
			}
		}
		return result;
	}
	
	
	
	public void setScript(String script) {
		this.script = script;
	}
	public List<HiveExecutionStep> getSteps() {
		return steps;
	}
	public void setSteps(List<HiveExecutionStep> steps) {
		this.steps = steps;
	}
	public void add(String scriptSourceCode) {
		this.steps.add(new HiveExecutionStep("script","",new HiveExecutionSentence(scriptSourceCode,HiveSentenceType.UPDATE,null),null,null));
	}
	public void add(HiveExecutionStep step) {
		this.steps.add(step);
	}



	public static void printExecutionPlan(HiveExecutionPlan executionPlan) {
		for (HiveExecutionSentence sentence:executionPlan.getSentences()) {
			System.out.println(sentence.toString());
		}
		
	}
	
	

}
