

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
