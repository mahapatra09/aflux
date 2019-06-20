

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

package de.tum.in.aflux.component.commonanalytics.util;


import java.util.ArrayList;
import java.util.List;

import de.tum.in.aflux.bigdata.hive.model.HiveBuilder;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionPlan;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionSentence;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionStep;
import de.tum.in.aflux.bigdata.hive.model.HiveSentenceType;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsBaseListener;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsParser;


/**
 * Listener used for walking through the parse tree.
 * 
 * Generates Hive execution plan based on common analytics sentences
 * 
 * @author Tanmaya Mahapatra
 * 
 * 
 */
public class CommonAnalyticsHiveListener extends CommonAnalyticsBaseListener {

	
	
	private HiveExecutionPlan executionPlan;
	private String currentDataType;
	private String currentAliasName;
	private List<String> structureDefinitionList;
	private List<String> summaryExpressionList;
	private List<String> leftMatchingList,rightMatchingList;
	
	
	public void exitAlias_name(CommonAnalyticsParser.Alias_nameContext ctx) { 
		this.currentAliasName=ctx.getText();
	}
	
	
	public void enterStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx) { 
		this.currentDataType="UNKNOWN";
		this.currentAliasName="";
	}
	

	public void exitStructure_definition_field(CommonAnalyticsParser.Structure_definition_fieldContext ctx) {
		this.structureDefinitionList.add(this.currentAliasName+" "+this.currentDataType);
	}
	
	
	public void exitData_type(CommonAnalyticsParser.Data_typeContext ctx) {
		String dataType=ctx.getText();
		if (dataType.equals("STRING")) {
			this.currentDataType="STRING";
		} else if (dataType.equals("BOOLEAN")) {
			this.currentDataType="BOOLEAN";
		} else if (dataType.equals("INT")) {
			this.currentDataType="INT";
		} else {
			this.currentDataType="UNKNNOWN";
		}
	}

	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 * 
	 * Example:
	 * CREATE EXTERNAL TABLE IF NOT EXISTS MOVIES (movieId INT,title STRING,genres STRING) ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde' WITH SERDEPROPERTIES ("separatorChar" = ",","quoteChar" = "'","escapeChar" = "\\") STORED AS TEXTFILE, type=UPDATE]
LOAD DATA INPATH '/user/root/pig_data/movies.csv' OVERWRITE INTO TABLE MOVIES, type=UPDATE]

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
		  
		  String hiveCreateSentence = HiveBuilder.CREATE_TABLE_EXTERNAL_PRE+collectionName+" ("+structureDefinition+")"+HiveBuilder.CREATE_TABLE_CSV_POST+" LOCATION "+fileName;
		  
		  /*
		  String tmpFileName;
		  int dotPosition=fileName.lastIndexOf('.');
		  String stringToAdd="_tmp_"+AFluxUtils.getNowId();
		  if (dotPosition>=0) {
			  tmpFileName=fileName.substring(0, dotPosition)+stringToAdd+fileName.substring(dotPosition);
		  } else {
			  tmpFileName=fileName+dotPosition;
		  }
		  
		  String copySentence = "DFS -cp "+fileName+" "+tmpFileName;
		  */
		  
		  

		  this.executionPlan.add(
				  new HiveExecutionStep(
						  "create",
						  collectionName,
						  new HiveExecutionSentence(hiveCreateSentence,HiveSentenceType.UPDATE,null),
						  null,
						  null));

		  /*
		  String hiveLoadSentence= "LOAD DATA INPATH "+tmpFileName+" OVERWRITE INTO TABLE "+collectionName;

		  this.executionPlan.add(
				  new HiveExecutionStep(
						  "hdfs-copy",
						  "",
						  new HiveExecutionSentence(copySentence,HiveSentenceType.UPDATE),
						  null,
						  null));
		  
		  this.executionPlan.add(
				  new HiveExecutionStep(
						  "load",
						  collectionName,
						  new HiveExecutionSentence(hiveLoadSentence,HiveSentenceType.UPDATE),
						  null,
						  null));
		  */
		  
	 }

	 
	 
	 
	 /**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	 @Override
	 public void exitSelect_sentence(CommonAnalyticsParser.Select_sentenceContext ctx) { 
		 
		  System.out.println("HIVE select sentence");
		  System.out.println(ctx.getText());
		  String targetAlias = ctx.collection_name().get(0).getText();
		  String sourceAlias = ctx.collection_name().get(1).getText();
		  String columns = ctx.expression_list().getText();
		  String filter = ctx.condition_list()==null?"":ctx.condition_list().getText();
		  filter=filter.replace("==", "=");
		  
		  
		  String hiveSelectSentence = "SELECT ALL "+columns+" FROM "+sourceAlias+" WHERE "+filter;
		  
		  this.executionPlan.add(
				  new HiveExecutionStep("select",targetAlias,
						  new HiveExecutionSentence(hiveSelectSentence,HiveSentenceType.QUERY,targetAlias),
						  null,null));
		  
				  
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
				  thisSummaryClause=thisSummaryClause.replace("COUNT()", "COUNT(*)");
				  expressions+=thisSummaryClause;
			  }
		  }
		  String groupBy=ctx.groupby_list().getText();
		  
		  
		  
		  
		  groupBy=groupBy==null?"":groupBy;
		  expressions=expressions==null?"":expressions;
		  String separator="";
		  String hiveSelectSentence;
		  if (groupBy.length()>0 && expressions.length()>0) {
			  separator=",";
		  };
		  
		  hiveSelectSentence = "SELECT ALL "+groupBy+separator+expressions+" FROM "+sourceAlias+" GROUP BY "+groupBy;
		  
		  this.executionPlan.add(new HiveExecutionStep(
				  "create",targetAlias,
				  new HiveExecutionSentence(hiveSelectSentence,HiveSentenceType.QUERY,targetAlias),
				  null,null));
		  
		 
	 }
	 
	 
		public void enterLoad_sentence(CommonAnalyticsParser.Load_sentenceContext ctx) { 
			this.structureDefinitionList=new ArrayList<String>();
		}
	 

		public void enterMatching_list(CommonAnalyticsParser.Matching_listContext ctx) { 
			this.leftMatchingList=new ArrayList<String>();
			this.rightMatchingList=new ArrayList<String>();
		}
		
		
		public void exitMatching_clause(CommonAnalyticsParser.Matching_clauseContext ctx) { 
			// add translated matching_cluase to matching_list
			
			this.leftMatchingList.add(ctx.collection_name(0).getText());
			this.rightMatchingList.add(ctx.collection_name(1).getText());
		}

		
		
		
		
	/**
	 * {@inheritDoc}
	 *SELECT k1, v1, k2, v2
		FROM a JOIN b ON k1 = k2; 
	 * <p>The default implementation does nothing.</p>
	 */
	 @Override
	 public void exitJoin_sentence(CommonAnalyticsParser.Join_sentenceContext ctx) { 
		 
		  System.out.println("join sentence");
		  String targetAlias=ctx.collection_name().get(0).getText();
		  String alias1=ctx.collection_name().get(1).getText();
		  String alias2=ctx.collection_name().get(2).getText();
		  
		  String expressions=ctx.expression_list().getText();
		  String match="(";

		  
		  if (this.leftMatchingList!=null) {
			  for (int i=0;i<this.leftMatchingList.size();i++) {
				  if (i>0) {
					  match+=" AND ";
				  };
				  match+=alias1+"."+this.leftMatchingList.get(i)+"="+alias2+"."+this.rightMatchingList.get(i);
			  }
		  }
		  match+=")";
		  
		  
		  String hiveSelectSentence = "SELECT ALL "+expressions+" FROM "+alias1+" JOIN "+alias2+" ON "+match;
		  
		  this.executionPlan.add(new HiveExecutionStep(
				  "select",targetAlias,
				  new HiveExecutionSentence(hiveSelectSentence,HiveSentenceType.QUERY,targetAlias),
				  null,null));
		  
		  
	 }
	
		/**
		 * {@inheritDoc}
		 *
		 * <p>The default implementation does nothing.</p>
		 */
		public void exitShow_sentence(CommonAnalyticsParser.Show_sentenceContext ctx) { 
			  System.out.println("show sentence");
			  String alias=ctx.collection_name().getText();

			  String hiveSelectSentence = "SELECT ALL * FROM "+alias;
			  this.executionPlan.add(new HiveExecutionStep(
					  "select",alias,
					  new HiveExecutionSentence(hiveSelectSentence,HiveSentenceType.QUERY,null),
					  null,null));

		}
	 
		public CommonAnalyticsHiveListener(HiveExecutionPlan executionPlan) {
			this.executionPlan = executionPlan;
		}


	 
		public HiveExecutionPlan getExecutionPlan() {
			return executionPlan;
		}


}