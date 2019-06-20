

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

package de.tum.in.aflux.bigdata.pig.model;

import java.util.ArrayList;
import java.util.List;


public class PigExecutionPlan {
	String script;
	List<PigExecutionStep> steps=new ArrayList<PigExecutionStep>();
	
	public String getScript() {
		List<String> resultList=getScriptAsList();
		String result="";
		for (String s:resultList) {
			result+=s+"\n";
		}
		return result;
	}
	
	public void setScript(String script) {
		this.script = script;
	}
	public List<PigExecutionStep> getSteps() {
		return steps;
	}
	public void setSteps(List<PigExecutionStep> steps) {
		this.steps = steps;
	}
	public void add(String scriptSourceCode) {
		this.steps.add(new PigExecutionStep("script","",scriptSourceCode,null,null));
	}
	public void add(PigExecutionStep step) {
		this.steps.add(step);
	}

	public static void printExecutionPlan(PigExecutionPlan executionPlan) {
		for (PigExecutionStep step:executionPlan.getSteps()) {
			System.out.println(step.toString());
		}
	}

	@Override
	public String toString() {
		return "PigExecutionPlan [script=" + script + ", steps=" + steps + "]";
	}

	public List<String> getScriptAsList() {
		List<String> result=new ArrayList<String>();
		if (script!=null && script.length()>0) {
			//result.add(script);
		}
		if (this.steps!=null) {
			for (PigExecutionStep step:steps) {
				result.add(step.getFinalSentence());
			}
		}
		return result;
	}
	
	
	
	
	
}
