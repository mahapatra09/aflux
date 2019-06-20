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

import org.slf4j.Logger;

import de.tum.in.aflux.tools.core.AbstractAFluxActor;
import de.tum.in.aflux.util.AFluxUtils;

public class HiveBuilder {

	public static final String CREATE_TABLE_PRE="CREATE TABLE IF NOT EXISTS ";
	public static final String CREATE_TABLE_POST=" row format delimited fields terminated by '|' STORED AS RCFile";
	public static final String CREATE_TABLE_EXTERNAL_PRE="CREATE EXTERNAL TABLE IF NOT EXISTS ";
	public static final String CREATE_TABLE_CSV_POST=" "+"ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde' WITH SERDEPROPERTIES ("+
		  	"\"separatorChar\" = \",\","+
		  	"\"quoteChar\" = \"'\","+
		  	"\"escapeChar\" = \"\\\\\") "+
		  	"STORED AS TEXTFILE";
	
	
	
	public static HiveExecutionPlan buildExecutionPlan(Object message) {
		HiveExecutionPlan executionPlan=new HiveExecutionPlan();
		if (message!=null) {
			if (message instanceof HiveExecutionPlan) {
				executionPlan=(HiveExecutionPlan) message;
			}
		}
		return executionPlan;
	}

	public static void deliverPlan(
				String commandType,
				String baseSentence,
				String alias,Logger log,
				HiveExecutionPlan baseExecutionPlan,
				AbstractAFluxActor actor,
				String newTableName) 
						throws Exception {
		deliverPlan(commandType,baseSentence,alias,log,baseExecutionPlan,actor,null,"",newTableName);
		
	}

	public static void deliverPlan(String commandType, String baseSentence, String alias, Logger log,
			HiveExecutionPlan baseExecutionPlan, AbstractAFluxActor actor, String repetitiveExpression, String modifiers,String newTableName) 
					throws Exception {
		String sentence=baseSentence;
		boolean addStep=true;
  		// add sentence to plan
		HiveExecutionPlan executionPlan=baseExecutionPlan;
		List<String> expressionList=null;
		HiveSentenceType sentenceType=HiveSentenceType.QUERY;
		if (commandType.equals("select") ) {
			expressionList=new ArrayList<String>();
		} else if (commandType.equals("join-inner") || commandType.equals("join-outer") || commandType.equals("join-cross") ) {
			addStep=false;
			baseExecutionPlan.setSteps(addJoinClause(baseExecutionPlan.getSteps(),commandType,"select",repetitiveExpression,log));
		} else if (commandType.equals("union")) {
			addStep=false;
			baseExecutionPlan.setSteps(addUnionClause(baseExecutionPlan.getSteps(),commandType,"select",repetitiveExpression,modifiers, log));
		} else {
			sentenceType=HiveSentenceType.UPDATE;
		}
		
		// adding step
		if (addStep) {
			HiveExecutionStep step=
					new HiveExecutionStep(commandType,alias,new HiveExecutionSentence(sentence,sentenceType,newTableName),modifiers,expressionList);
			log.info(sentence);
			executionPlan.add(step);
		}
		actor.setOutput(1,executionPlan);
		if (executionPlan.getSteps().size()>0) {
			HiveExecutionStep lastStep=executionPlan.getSteps().get(executionPlan.getSteps().size()-1);
			actor.sendOutput(lastStep.getSentence()+" "+lastStep.getModifiers());
		} else {
			actor.sendOutput("sentence=null");
		}
	}
	
	
	private static String rebuildRepetitiveSentence(String initialSentence,List<String> byGroupList) {
		String newSentence=initialSentence+" "+byGroupList.get(byGroupList.size()-1);
		return newSentence;
	}
	
	private static List<HiveExecutionStep> addJoinClause(
			List<HiveExecutionStep> sourceStepList,
			String elementType,
			String predecessorType,
			String joinExpression,
			Logger log) {
		if (sourceStepList.size()>0) {
			HiveExecutionStep lastStep=sourceStepList.get(sourceStepList.size()-1);
			if (lastStep.getType().equals(predecessorType)) {
				List<String> byList=lastStep.getRepetitiveExpressionList();
				byList.add(joinExpression);
				String newSentence=rebuildRepetitiveSentence(lastStep.getSentence().getSentence(),byList);
				lastStep=new HiveExecutionStep(
						lastStep.getType(),
						lastStep.getAlias(),
						new HiveExecutionSentence(newSentence,lastStep.getSentence().getType(),null),
						lastStep.getModifiers(),
						byList);
				sourceStepList.set(sourceStepList.size()-1, lastStep);
			} else {
				log.info("Warning: "+elementType+" after no "+predecessorType+" nor "+elementType+" node");
			}
		}
		return sourceStepList;
	}

	private static List<HiveExecutionStep> addUnionClause(
			List<HiveExecutionStep> sourceStepList,
			String elementType,
			String predecessorType,
			String unionExpression,
			String modifiers,
			Logger log) {
		if (sourceStepList.size()>0) {
			HiveExecutionStep lastStep=sourceStepList.get(sourceStepList.size()-1);
			if (lastStep.getType().equals(predecessorType)) {
				String newSentence=lastStep.sentence+" "+lastStep.getModifiers()+" "+unionExpression;
				lastStep=new HiveExecutionStep(
						lastStep.getType(),
						lastStep.getAlias(),
						new HiveExecutionSentence(newSentence,lastStep.getSentence().getType(),null),
						modifiers,
						new ArrayList<String>());
				sourceStepList.set(sourceStepList.size()-1, lastStep);
			} else {
				log.info("Warning: "+elementType+" after no "+predecessorType+" nor "+elementType+" node");
			}
		}
		return sourceStepList;
	}
	
	
	public static String buildTemporaryClause(String temporaryValue) {
		return buildBooleanClause(temporaryValue,"TEMPORARY");
	}

	public static String buildExternalClause(String externalValue) {
		return buildBooleanClause(externalValue,"EXTERNAL");
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

	private static String buildIfPresentClause(String clause,String value) {
		String result="";
		if (value!=null && value.length()>0) {
			result=" "+clause+" "+value+" ";
		}
		return result;
	}
	
	public static String buildCommentClause(String commentValue) {
		String result="";
		if (commentValue!=null && commentValue.length()>0) {
			result=buildIfPresentClause("COMMENT","'"+commentValue+"'");
		}
		return result;
	}

	public static String buildPartitionedClause(String partitionedValue) {
		String result="";
		if (partitionedValue!=null && partitionedValue.length()>0) {
			result=" PARTITIONED BY ("+partitionedValue+")";
		}
		return result;
	}

	public static String buildRowFormatClause(String value) {
		return buildIfPresentClause("ROW FORMAT",value);
	}

	
	
	public static String buildStoredAsClause(String storedValue) {
		String result="";
		if (storedValue!=null && storedValue.length()>0) {
			storedValue=storedValue.toUpperCase();
			if (storedValue.equals("TEXTFILE") || 
					storedValue.equals("SEQUENCEFILE") || 
					storedValue.equals("ORC") || 
					storedValue.equals("PARQUET") || 
					storedValue.equals("AVRO") || 
					storedValue.equals("RCFILE")) {
				result=" STORED AS "+storedValue;
			} 
		}
		return result;
	}
	
	public static String buildLocationClause(String value) {
		String result="";
		if (value!=null && value.length()>0) {
			result=" LOCATION '"+value.trim()+"'";
		} 
		return result;
	}

	public static String buildPurgueClause(String value) {
		return buildBooleanClause(value,"PURGE");
	}

	public static String buildPartitionClause(String value) {
		return buildIfPresentClause("PARTITION",value);
	}

	public static String buildOverwriteClause(String value) {
		return buildBooleanClause(value,"OVERWRITE");
	}

	public static String buildTableClause(String value) {
		return buildBooleanClause(value,"TABLE");
	}
	
	
	public static String buildIntoOverwriteClause(String value) {
		String result="";
		if (value!=null && (value.toUpperCase().equals("OVERWRITE") || value.toUpperCase().equals("INTO"))) {
			result=" "+value.toUpperCase();
		}
		return result;
	}

	public static String buildColumnListClause(String value) {
		String result="";
		if (value!=null && value.length()>0) {
			result=" ("+value+") ";
		}
		return result;
	}

	public static String buildSelectClause(String hint, String select) {
		String result="";
		if (select!=null && select.length()>0) {
			if (hint!=null &&  (hint.toUpperCase().equals("SHUFFLE") || hint.toUpperCase().equals("NOSHUFFLE"))) {
				result+="{"+hint.toUpperCase()+"}";
			}
			result+=" "+select;
		}
		return result;
	}

	public static String buildValuesClause(String values) {
		String result="";
		if (values!=null && values.length()>0) {
			result+=" VALUES ("+values+") ";
		}
		return result;
	}

	public static String buildALLDistinctClause(String value) {
		String result="";
		if (value!=null && (value.toUpperCase().equals("ALL") || value.toUpperCase().equals("DISTINCT"))) {
			result=" "+value.toUpperCase();
		}
		return result;
	}

	public static String buildWhereClause(String value) {
		return " "+buildIfPresentClause("WHERE",value)+" ";
	}

	public static String buildGroupByClause(String groupBy, String having) {
		String result="";
		if (groupBy!=null && groupBy.length()>0) {
			result+=" GROUP BY "+groupBy;
			if (having!=null && having.length()>0) {
				result+=" HAVING "+having;
			}
		}
		return result;
	}

	public static String buildLimitClause(String value) {
		return buildIfPresentClause("LIMIT",value);
	}

	public static String buildInnerClause(String value) {
		return buildBooleanClause(value,"INNER");
	}

	public static String buildSideExpression(String value) {
		String result="";
		if (value!=null && 
				(value.toUpperCase().equals("LEFT") || 
					value.toUpperCase().equals("RIGHT") || 
					value.toUpperCase().equals("FULL"))) {
			result=" "+value.toUpperCase();
		}
		return result;
	}

	public static String buildOuterClause(String value) {
		return buildBooleanClause(value,"OUTER");
	}

	public static String buildTableName(String name,String addTimestampString) {
		String result = name;
		
		
		boolean addTimestamp=false;
		if (addTimestampString!=null) {
			addTimestampString=addTimestampString.toUpperCase().trim();
			addTimestamp=addTimestampString.equals("TRUE") || addTimestampString.equals("YES");
		}
		if (addTimestamp) {
			result+=AFluxUtils.getNowId();
		}

		return result;
	}
	
	
	public static String generateCreationSentence(String targetAlias,String sentence) {
		  return CREATE_TABLE_PRE+targetAlias+CREATE_TABLE_POST+" AS "+sentence;
	}

	
	
}
