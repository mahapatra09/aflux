

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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import de.tum.in.aflux.tools.core.AbstractAFluxActor;
/**
 * 
 * Class to help building the syntax of pig commands
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class PigBuilder {
	
	private static Set<String> aliasCommands=new HashSet<String>(Arrays.asList("load","group","cogroup","limit","cross","distinct","filter","foreach",
				"join-inner","order","join-outer","sample","union","stream"));

	public static PigExecutionPlan buildExecutionPlan(Object message) {
		PigExecutionPlan executionPlan=new PigExecutionPlan();
		if (message!=null) {
			if (message instanceof PigExecutionPlan) {
				executionPlan=(PigExecutionPlan) message;
			}
		}
		return executionPlan;
	}

	
	public static String buildAlias(PigExecutionPlan executionPlan,String alias) {
		String result=alias;
		if (result==null || result.length()==0) {
			result=getLastAlias(executionPlan);
		}
		return result;
	}
	
	public static String getLastAlias(PigExecutionPlan executionPlan) {
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
	
	public static String buildFunction(String functionDeclaration) {
		String result="";
		if (functionDeclaration!=null && functionDeclaration.length()>2 && functionDeclaration.indexOf('(')>=1 && functionDeclaration.indexOf(')')>=3) {
			result=" USING "+functionDeclaration;
		}
		return result;
	}


	public static void deliverPlan(
			String commandType,
			String baseSentence,
			String alias,
			Logger log,
			PigExecutionPlan baseExecutionPlan,
			AbstractAFluxActor actor) 
					throws Exception {
		deliverPlan(commandType,baseSentence,alias,log,baseExecutionPlan,actor,null,"");
	}
	
	public static String buildByExpression(String alias,String bySource) {
		String by=bySource;
		if (by==null || by.length()==0) {
			by="ALL";
		} else {
			by="BY "+by;
		};
		return alias+" "+by;
	}

	public static String buildOrderExpression(String initialField,String initialMode) {
		String mode;
		if (initialMode==null) {
			mode="ASC";
		} else {
			mode=initialMode.toUpperCase();
		}
		if (!mode.equals("ASC") && !mode.equals("DESC")) {
			mode="ASC";
		}
		
		String field=initialField;
		if (initialField==null) {
			field="*";
		}
		return field+" "+mode;
	}

	
	public static String buildIfExpression(String alias,String expression) {
		return alias+" IF "+expression;
	}
	
	
	
	public static String buildParalellClause(String value) {
		return buildIfPresentClause("PARALLEL", value);
	}

	
	private static String rebuildRepetitiveSentence(String initialSentence,List<String> byGroupList) {
		int i=initialSentence.indexOf(byGroupList.get(0));
		String newSentence=initialSentence.substring(0, i);
		boolean firstTime=true;
		for (String s:byGroupList) {
			if (!firstTime) {
				newSentence+=",";
			} else {
				firstTime=false;
			}
			newSentence+=s;
		}
		return newSentence;
	}

	public static void deliverPlan(
				String commandType,
				String baseSentence,
				String alias,
				Logger log,
				PigExecutionPlan baseExecutionPlan,
				AbstractAFluxActor actor, 
				String repetitiveExpression,
				String modifiers) throws Exception {
		String sentence=baseSentence;
		boolean addStep=true;
  		// add sentence to plan
		PigExecutionPlan executionPlan=baseExecutionPlan;
		List<String> expressionList=null;
		if (commandType.equals("group") || commandType.equals("join-inner") || commandType.equals("split") || commandType.equals("order")) {
			expressionList=new ArrayList<String>();
			expressionList.add(repetitiveExpression);
		} else if (commandType.equals("cogroup")) {
			addStep=false;
			baseExecutionPlan.setSteps(addByClause(baseExecutionPlan.getSteps(),"cogroup","group",repetitiveExpression,log));
		} else if (commandType.equals("cojoin-inner")) {
			addStep=false;
			baseExecutionPlan.setSteps(addByClause(baseExecutionPlan.getSteps(),"cojoin-inner","join-inner",repetitiveExpression,log));
		} else if (commandType.equals("cosplit")) {
			addStep=false;
			baseExecutionPlan.setSteps(addIfClause(baseExecutionPlan.getSteps(),"cosplit","split",repetitiveExpression,log));
		} else if (commandType.equals("coorder")) {
			addStep=false;
			baseExecutionPlan.setSteps(addOrderClause(baseExecutionPlan.getSteps(),"coorder","order",repetitiveExpression,log));
		}
		if (addStep) {
			PigExecutionStep step=new PigExecutionStep(commandType,alias,sentence,modifiers,expressionList);
			log.info(step.getFinalSentence());
			executionPlan.add(step);
		}
		actor.setOutput(1,executionPlan);
		if (executionPlan.getSteps().size()>0) {
			PigExecutionStep lastStep=executionPlan.getSteps().get(executionPlan.getSteps().size()-1);
			actor.sendOutput(lastStep.getFinalSentence());
		} else {
			actor.sendOutput("sentence=null");
		}
	}
	
	private static List<PigExecutionStep> addByClause(List<PigExecutionStep> sourceStepList,String elementType,String predecessorType,String byExpression,Logger log) {
		if (sourceStepList.size()>0) {
			PigExecutionStep lastStep=sourceStepList.get(sourceStepList.size()-1);
			if (lastStep.getType().equals(predecessorType)) {
				List<String> byList=lastStep.getRepetitiveExpressionList();
				byList.add(byExpression);
				String newSentence=rebuildRepetitiveSentence(lastStep.getSentence(),byList);
				lastStep=new PigExecutionStep(lastStep.getType(),lastStep.getAlias(),newSentence,lastStep.getModifiers(),byList);
				sourceStepList.set(sourceStepList.size()-1, lastStep);
			} else {
				log.info("Warning: "+elementType+" after no "+predecessorType+" nor "+elementType+" node");
			}
		}
		return sourceStepList;
	}

	private static List<PigExecutionStep> addIfClause(
				List<PigExecutionStep> sourceStepList,
				String elementType,
				String predecessorType,
				String ifExpression,
				Logger log) {
		if (sourceStepList.size()>0) {
			PigExecutionStep lastStep=sourceStepList.get(sourceStepList.size()-1);
			if (lastStep.getType().equals(predecessorType)) {
				List<String> ifList=lastStep.getRepetitiveExpressionList();
				ifList.add(ifExpression);
				String newSentence=rebuildRepetitiveSentence(lastStep.getSentence(),ifList);
				lastStep=new PigExecutionStep(lastStep.getType(),lastStep.getAlias(),newSentence,lastStep.getModifiers(),ifList);
				sourceStepList.set(sourceStepList.size()-1, lastStep);
			} else {
				log.info("Warning: "+elementType+" after no "+predecessorType+" nor "+elementType+" node");
			}
		}
		return sourceStepList;
	}
	

	private static List<PigExecutionStep> addOrderClause(
			List<PigExecutionStep> sourceStepList,
			String elementType,
			String predecessorType,
			String ifExpression,
			Logger log) {
	if (sourceStepList.size()>0) {
		PigExecutionStep lastStep=sourceStepList.get(sourceStepList.size()-1);
		if (lastStep.getType().equals(predecessorType)) {
			List<String> ifList=lastStep.getRepetitiveExpressionList();
			ifList.add(ifExpression);
			String newSentence=rebuildRepetitiveSentence(lastStep.getSentence(),ifList);
			lastStep=new PigExecutionStep(lastStep.getType(),lastStep.getAlias(),newSentence,lastStep.getModifiers(),ifList);
			sourceStepList.set(sourceStepList.size()-1, lastStep);
		} else {
			log.info("Warning: "+elementType+" after no "+predecessorType+" nor "+elementType+" node");
		}
	}
	return sourceStepList;
}

	
	
	public static String buildGenerateClause(String generateExpression) {
		return " GENERATE "+generateExpression;
	}
	
	public static String buildSchemaClause(String schemaExpression) {
		String result="";
		String schema=schemaExpression;
		if (schema!=null && schema.length()>0) {
			result=" AS ("+schema+")";
		}
		return result;
	}
	
	public static String buildCollectedClause(String externalValue) {
		return buildBooleanClause(externalValue," USING 'collected'");
	}

	
	
	private static String buildBooleanClause(String value,String keyword) {
		String result="";
		if (value!=null) {
			if (value.toUpperCase().equals("TRUE") || value.toUpperCase().equals("YES")) {
				result=" "+keyword;
			}
		}
		return result;
	}


	public static String buildOuterClause(String value) {
		return buildBooleanClause(value," OUTER ");
	}

	private static String buildIfPresentClause(String clause,String value) {
		String result="";
		if (value!=null && value.length()>0) {
			result=" "+clause+" "+value+" ";
		}
		return result;
	}

	
}
