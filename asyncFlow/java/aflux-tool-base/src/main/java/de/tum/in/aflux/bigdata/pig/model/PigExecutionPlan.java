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
