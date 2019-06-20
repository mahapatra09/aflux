

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

package de.tum.in.aflux.component.commonanalytics.model;

import java.util.ArrayList;
import java.util.List;



public class CommonAnalyticsExecutionPlan {
	String script;
	List<CommonAnalyticsExecutionStep> steps=new ArrayList<CommonAnalyticsExecutionStep>();
	
	public String getScript() {
		String result=this.script;
		if (result==null) {
			result="";
		}
		if (this.steps!=null) {
			for (CommonAnalyticsExecutionStep step:steps) {
				String semicolon=";";
				if (step.getSentence().endsWith(";")) {
					semicolon="";
				}
				result=result+step.getSentence()+semicolon+"\n";
			}
		}
		return result;
	}
	
	public void setScript(String script) {
		this.script = script;
	}
	public List<CommonAnalyticsExecutionStep> getSteps() {
		return steps;
	}
	public void setSteps(List<CommonAnalyticsExecutionStep> steps) {
		this.steps = steps;
	}
	public void add(String scriptSourceCode) {
		this.steps.add(new CommonAnalyticsExecutionStep("script","",scriptSourceCode,null,null));
	}
	public void add(CommonAnalyticsExecutionStep step) {
		this.steps.add(step);
	}

	public static void printExecutionPlan(CommonAnalyticsExecutionPlan executionPlan) {
			    System.out.println("Sentences...");
			    for (CommonAnalyticsExecutionStep step : executionPlan.getSteps()) {
			      System.out.print(step.getSentence() + " ");
			    }
			    System.out.println("");
			  }

	@Override
	public String toString() {
		return "CommonAnalyticsExecutionPlan [script=\n" + script + "\n, steps=" + steps + "]";
	}
	
	
	
	
}
