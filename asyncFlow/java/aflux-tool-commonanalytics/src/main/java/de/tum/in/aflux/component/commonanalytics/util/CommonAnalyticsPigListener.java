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

package de.tum.in.aflux.component.commonanalytics.util;


import java.util.ArrayList;
import java.util.List;

import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionStep;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsBaseListener;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsParser;
import de.tum.in.aflux.util.AFluxUtils;


/**
 * Listener used for walking through the parse tree.
 * Generates Pig execution plan based on common analytics sentences
 *  
 *  * 
 * @author Tanmaya Mahapatra
 * 
 * 
 */
public class CommonAnalyticsPigListener extends CommonAnalyticsBaseListener {

	
	private PigExecutionPlan executionPlan;
	private List<String> matchingCluasesLeft;
	private List<String> matchingCluasesRight;
	private String currentDataType;
	private String currentStructureDefinitionField;
	private String currentAliasName;
	private List<String> structureDefinitionList;
	private List<String> summaryExpressionList;
	
	
	public void exitAlias_name(CommonAnalyticsParser.Alias_nameContext ctx) { 
		this.currentAliasName=ctx.getText();
	}
	
	
	public void enterStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx) { 
		this.currentDataType="UNKNOWN";
		this.currentAliasName="";
	}
	

	public void exitStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx) {
		this.structureDefinitionList.add(this.currentAliasName+":"+this.currentDataType);
	}
	
	
	public void exitData_type(CommonAnalyticsParser.Data_typeContext ctx) {
		String dataType=ctx.getText();
		if (dataType.equals("STRING")) {
			this.currentDataType="chararray";
		} else if (dataType.equals("BOOLEAN")) {
			this.currentDataType="UNKNOWN";
		} else if (dataType.equals("INT")) {
			this.currentDataType="int";
		} else {
			this.currentDataType="UNKNNOWN";
		}
	}

	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 * 
	 * 
	 * Example LOAD ‘/user/root/links.csv’ function= PigStorage(‘,’) schema=‘movieId:int,imdbId:int,tmdbId:int’;
	 */
	 @Override
	 public void exitLoad_sentence(CommonAnalyticsParser.Load_sentenceContext ctx) { 
		  System.out.println("load sentence");
		  
		  String fileName=ctx.file_name().getText();
		  String collectionName=ctx.collection_name().getText();
		  boolean firstTime=true;;
		  String structureDefinition="";
		  for (String s:this.structureDefinitionList) {
			  if (firstTime) {
				  structureDefinition+=s;
				  firstTime=false;
			  } else {
				  structureDefinition+=","+s;
			  }
		  }
		  String sentence = ctx.getText();
		  

		  String pigLoadSentence= collectionName+" = LOAD "+fileName+" USING PigStorage(',') AS ("+structureDefinition+")";

		  PigExecutionStep step=new PigExecutionStep("load",collectionName,pigLoadSentence,null,null);
		  
		  this.executionPlan.add(step);
		  
	 }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	 @Override
	 public void exitSelect_sentence(CommonAnalyticsParser.Select_sentenceContext ctx) { 
		 
		  System.out.println("PIG select sentence");
		  System.out.println(ctx.getText());
		  String targetAlias = ctx.collection_name().get(0).getText();
		  String sourceAlias = ctx.collection_name().get(1).getText();
		  String columns = ctx.expression_list().getText();
		  String filter = ctx.condition_list()==null?"":ctx.condition_list().getText();
		  filter=filter==null?"":filter;
		  columns=columns==null?"":columns.trim();
		  // to show all columns is not need the FOREACH SENTENCE
		  columns=columns.equals("*")?"":columns;
		  String tmpTargetAlias = "";
		  String pigSentence="";
		  if (filter.length()>0) {
			  if (columns.length()>0) {
				  tmpTargetAlias=targetAlias+"_tmp_"+AFluxUtils.getNowId();
			  } else {
				  tmpTargetAlias=targetAlias;
			  }
			  pigSentence=tmpTargetAlias+" = FILTER "+sourceAlias+" BY "+filter;
			  
			  PigExecutionStep step=new PigExecutionStep("filter",tmpTargetAlias,pigSentence,null,null);

			  
			  this.executionPlan.add(step);
		  }
		  if (columns.length()>0) {
			  if (tmpTargetAlias.length()>0) {
				  sourceAlias=tmpTargetAlias;
			  }
			  pigSentence= targetAlias+" = FOREACH "+sourceAlias+" GENERATE "+columns;
			  PigExecutionStep step=new PigExecutionStep("foreach",tmpTargetAlias,pigSentence,null,null);
			  this.executionPlan.add(step);
		  }
	 }
	 
	 
		public void enterSummary_expression_list(CommonAnalyticsParser.Summary_expression_listContext ctx) { 
				this.summaryExpressionList=new ArrayList<String>();
		}

	 
		
		public void exitSummary_expression_and_alias(CommonAnalyticsParser.Summary_expression_and_aliasContext ctx) { 
				this.summaryExpressionList.add(ctx.summary_expression().getText()+" AS "+ctx.alias_name().getText());
		}
		
	 
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	 @Override
	 public void exitSummarize_sentence(CommonAnalyticsParser.Summarize_sentenceContext ctx) { 

		  String targetAlias=ctx.collection_name().get(0).getText();
		  String sourceAlias=ctx.collection_name().get(1).getText();
		  String expressions="";
		  if (this.summaryExpressionList!=null) {
			  for (int i=0;i<this.summaryExpressionList.size();i++) {
				  if (i>0) {
					  expressions+=",";
				  }
				  String thisSummaryClause=this.summaryExpressionList.get(i);
				  thisSummaryClause=thisSummaryClause.replace("COUNT()", "COUNT("+sourceAlias+")");
				  expressions+=thisSummaryClause;
			  }
		  }
		  String groupBy=ctx.groupby_list().getText();

		  String tmpAlias=targetAlias+"_tmp_"+AFluxUtils.getNowId();
		  String groupSentence = tmpAlias+" = GROUP "+sourceAlias+ " BY "+groupBy;
		  
		  PigExecutionStep step=new PigExecutionStep("group",tmpAlias,groupSentence,null,null);
		  
		  this.executionPlan.add(step);
		  String summarizeSentence = targetAlias+ " = FOREACH "+tmpAlias+" GENERATE group AS "+groupBy+","+expressions;
		  PigExecutionStep step2=new PigExecutionStep("foreach",targetAlias,summarizeSentence,null,null);
		  this.executionPlan.add(step2);
		  
	 }
	 
		public void enterLoad_sentence(CommonAnalyticsParser.Load_sentenceContext ctx) { 
			this.structureDefinitionList=new ArrayList<String>();
		}
	 
	 
	 
	 @Override 
	 public void enterJoin_sentence(CommonAnalyticsParser.Join_sentenceContext ctx) { 
		 this.matchingCluasesLeft=new ArrayList<String>();
		 this.matchingCluasesRight=new ArrayList<String>();
		 
	 }
	 
		public void exitMatching_clause(CommonAnalyticsParser.Matching_clauseContext ctx) {
			this.matchingCluasesLeft.add(ctx.collection_name().get(0).getText());
			this.matchingCluasesRight.add(ctx.collection_name().get(1).getText());
		}
	 
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	 @Override
	 public void exitJoin_sentence(CommonAnalyticsParser.Join_sentenceContext ctx) { 
		 
		  System.out.println("join sentence");
		  String targetAlias=ctx.collection_name().get(0).getText();
		  String alias1=ctx.collection_name().get(1).getText();
		  String alias2=ctx.collection_name().get(2).getText();
		  
		  String expressions=ctx.expression_list().getText();
		  
		  String matchLeft="";
		  String matchRight="";
		  String separator="";
		  for (int i=0;i<this.matchingCluasesLeft.size();i++) {
			  if (i>0) {
				  separator=",";
			  }
			  matchLeft+=separator+this.matchingCluasesLeft.get(i);
			  matchRight+=separator+this.matchingCluasesRight.get(i);
		  }

		  String pigSentence = targetAlias+" = JOIN "+alias1+" BY "+matchLeft+", "+alias2+" BY "+matchRight;
		  
		  PigExecutionStep step=new PigExecutionStep("join",targetAlias,pigSentence,null,null);
		  
		  this.executionPlan.add(step);
		  
		  
		  
	 }
	
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation does nothing.</p>
		 */
		public void exitShow_sentence(CommonAnalyticsParser.Show_sentenceContext ctx) { 
			  System.out.println("join sentence");
			  String alias=ctx.collection_name().getText();
			  
			  String tmpAlias=alias+"_tmp_"+AFluxUtils.getNowId();
			  String pigSentence="STORE "+alias+" INTO '"+tmpAlias+"' USING PigStorage(',')";
			  PigExecutionStep step=new PigExecutionStep("store",null,pigSentence,null,null);
			  
			  this.executionPlan.add(step);
		}
	 
	 
	 
	public CommonAnalyticsPigListener(PigExecutionPlan executionPlan) {
		this.executionPlan = executionPlan;
	}

	
	public PigExecutionPlan getExecutionPlan() {
		return executionPlan;
	}

	
	

}