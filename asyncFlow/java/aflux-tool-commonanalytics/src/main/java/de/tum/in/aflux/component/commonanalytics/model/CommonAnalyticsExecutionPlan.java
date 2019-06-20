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
