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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import de.tum.in.aflux.tools.core.AbstractAFluxActor;
/**
 * 
 * Class to help building the syntax of Common Analytics commands
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsBuilder {
	
	private static Set<String> aliasCommands=new HashSet<String>(Arrays.asList("load","select","join","summarize"));

	public static CommonAnalyticsExecutionPlan buildExecutionPlan(Object message) {
		CommonAnalyticsExecutionPlan executionPlan=new CommonAnalyticsExecutionPlan();
		if (message!=null) {
			if (message instanceof CommonAnalyticsExecutionPlan) {
				executionPlan=(CommonAnalyticsExecutionPlan) message;
			}
		}
		return executionPlan;
	}

	
	public static String buildAlias(CommonAnalyticsExecutionPlan executionPlan,String alias) {
		String result=alias;
		if (result==null || result.length()==0) {
			result=getLastAlias(executionPlan);
		}
		return result;
	}
	
	public static String getLastAlias(CommonAnalyticsExecutionPlan executionPlan) {
		String result="";
		boolean found=false;
		for (int i=executionPlan.steps.size()-1;i>=0 && !found;i--) {
			String commandType=executionPlan.steps.get(i).getType();
			if (aliasCommands.contains(commandType) ) {
				found=true;
				result=executionPlan.steps.get(i).getAlias();
			}
		}
		return result;
	}
	
	public static void deliverPlan(
			String commandType,
			String baseSentence,
			String alias,
			Logger log,
			CommonAnalyticsExecutionPlan baseExecutionPlan,
			AbstractAFluxActor actor) 
					throws Exception {
		deliverPlan(commandType,baseSentence,alias,log,baseExecutionPlan,actor,null,"");
	}
	
	
	public static void deliverPlan(
				String commandType,
				String baseSentence,
				String alias,
				Logger log,
				CommonAnalyticsExecutionPlan baseExecutionPlan,
				AbstractAFluxActor actor, 
				String repetitiveExpression,
				String modifiers) throws Exception {
		String sentence=baseSentence+";";
		boolean addStep=true;
  		// add sentence to plan
		CommonAnalyticsExecutionPlan executionPlan=baseExecutionPlan;
		List<String> expressionList=null;
		if (addStep) {
			CommonAnalyticsExecutionStep step=new CommonAnalyticsExecutionStep(commandType,alias,sentence,modifiers,expressionList);
			log.info(sentence);
			executionPlan.add(step);
		}
		actor.setOutput(1,executionPlan);
		if (executionPlan.getSteps().size()>0) {
			CommonAnalyticsExecutionStep lastStep=executionPlan.getSteps().get(executionPlan.getSteps().size()-1);
			actor.sendOutput(lastStep.getSentence()+" "+lastStep.getModifiers());
		} else {
			actor.sendOutput("sentence=null");
		}
	}
	
	
}
