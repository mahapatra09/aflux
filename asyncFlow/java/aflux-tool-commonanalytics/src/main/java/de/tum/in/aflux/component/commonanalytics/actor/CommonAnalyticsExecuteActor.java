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

package de.tum.in.aflux.component.commonanalytics.actor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tum.in.aflux.bigdata.hive.model.HiveExecutionPlan;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutionSentence;
import de.tum.in.aflux.bigdata.hive.model.HiveExecutor;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.bigdata.pig.model.PigExecutor;
import de.tum.in.aflux.component.commonanalytics.CommonAnalyticsConstants;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsLexer;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsParser;
import de.tum.in.aflux.component.commonanalytics.model.CommonAnalyticsExecutionPlan;
import de.tum.in.aflux.component.commonanalytics.model.CommonAnalyticsExecutionStep;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
import de.tum.in.aflux.component.commonanalytics.util.CommonAnalyticsHiveListener;
import de.tum.in.aflux.component.commonanalytics.util.CommonAnalyticsPigListener;
/**
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class CommonAnalyticsExecuteActor extends AbstractAFluxActor {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	

	public CommonAnalyticsExecuteActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,4);
	}

	/**
	 * main running execution algorithm
	 * 
	 * dependint on the target language it trasnaltes the execution plag to Hive / Pig or Spark
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	@Override
	protected void runCore(Object message) throws Exception {
		CommonAnalyticsExecutionPlan executionPlan=new CommonAnalyticsExecutionPlan();
		if (message!=null) {
			if (message instanceof CommonAnalyticsExecutionPlan) {
				executionPlan=(CommonAnalyticsExecutionPlan) message;
			}
		}
		String executorType = this.getProperty(CommonAnalyticsConstants.EXECUTOR);
		String result;
		String runScript=this.getProperty(CommonAnalyticsConstants.RUN);
		boolean shouldRun=runScript==null || runScript.toUpperCase().equals("TRUE") || runScript.toUpperCase().equals("YES");
		if (executorType.toUpperCase().equals("SPARK")) {
			// executePig(executionPlan);
		} else if (executorType.toUpperCase().equals("HIVE")) {
			HiveExecutionPlan hivePlan=prepareHivePlan(executionPlan);
			execute(hivePlan,shouldRun);
		} else if (executorType.toUpperCase().equals("PIG")) {
			PigExecutionPlan pigPlan=preparePigPlan(executionPlan);
			execute(pigPlan,shouldRun);
		}
		
		String finalScript=executionPlan.getScript();
		log.info(finalScript);
		
		result=null;
		this.setOutput(1, result);
		this.setOutput(2, finalScript);
		this.setOutput(3,executionPlan);

	}

	
	/**
	 * 
	 * 
	 * Generates the script in commoon analytics language
	 * @param executionPlan
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	private String extractScript(CommonAnalyticsExecutionPlan executionPlan) {
		String result="";
		for (CommonAnalyticsExecutionStep step:executionPlan.getSteps()) {
			result+=step.getSentence();
		}
		return result;
	}
	
	private CommonAnalyticsParser genearteParser(String script) throws IOException {
		  //Reading the DSL script
		  InputStream is = new ByteArrayInputStream( script.getBytes(  ) );
		  //Loading the DSL script into the ANTLR stream.
		  CharStream cs = new ANTLRInputStream(is);
		  
		  //Passing the input to the lexer to create tokens
		  CommonAnalyticsLexer lexer = new CommonAnalyticsLexer(cs);
		  
		  CommonTokenStream tokens = new CommonTokenStream(lexer);
		  
		  //Passing the tokens to the parser to create the parse trea. 
		  CommonAnalyticsParser parser = new CommonAnalyticsParser(tokens);
		  

		  return parser;
	}

	
	/**
	 * 
	 * 
	 * Generates a HiveExecutionPlan based on the CAExecutionPlan
	 * @param executionPlan
	 * @return
	 * @throws IOException
	 * 
	 * @author Tanmaya Mahapatra
	 */
	private HiveExecutionPlan prepareHivePlan(CommonAnalyticsExecutionPlan executionPlan) throws IOException  {
		String script=extractScript(executionPlan);
		CommonAnalyticsParser parser=genearteParser(script);

		
		  //Semantic model to be populated
		  HiveExecutionPlan hiveExecutionPlan = new HiveExecutionPlan();
		  
		  //Adding the listener to facilitate walking through parse tree. 
		  parser.addParseListener(new CommonAnalyticsHiveListener(hiveExecutionPlan));
		
		//invoking the parser. 
		  log.debug("parsing CA2Hive");
		  parser.script();
		  log.debug("finish parsing CA2Hive");
		  
		  // HiveCAExecutionPlan.printExecutionPlan(executionPlan);
		
		  return hiveExecutionPlan;
	}
	

	
	
	
	private void execute(HiveExecutionPlan executionPlan,boolean run) throws Exception {
		List<Map<String, Object>> rows;
		if (run) {
			rows=HiveExecutor.executeHivePlan(executionPlan, this, log);
		} else {
			rows= null;
		}
		this.setOutput(1, rows.toString());
		
		
		
		List<String> stringSentences=new ArrayList<String>();
		List<HiveExecutionSentence> sentences=executionPlan.getSentences();
		for (HiveExecutionSentence s:sentences) {
			stringSentences.add(s.toString());
		}
		this.setOutput(2, stringSentences);
		this.setOutput(3, executionPlan);
		
	}

	
	
	private PigExecutionPlan preparePigPlan(CommonAnalyticsExecutionPlan executionPlan) throws IOException  {
		String script=extractScript(executionPlan);
		CommonAnalyticsParser parser=genearteParser(script);

		
		  //Semantic model to be populated
		  PigExecutionPlan pigExecutionPlan = new PigExecutionPlan();
		  
		  //Adding the listener to facilitate walking through parse tree. 
		  parser.addParseListener(new CommonAnalyticsPigListener(pigExecutionPlan));
		
		//invoking the parser. 
		  log.debug("parsing CA2Pig");
		  parser.script();
		  log.debug("end parsing CA2Pig");
		  // HiveCAExecutionPlan.printExecutionPlan(executionPlan);
		
		  return pigExecutionPlan;
	}
	

	
	
	
	private void execute(PigExecutionPlan executionPlan,boolean run) throws Exception {
		
		
		
		
		String result=run?PigExecutor.execute(executionPlan, this, log):"";
		
		
		String finalScript=executionPlan.getScript();
		
		this.setOutput(1, result);
		this.setOutput(2, finalScript);
		this.setOutput(3,executionPlan);
		
	}
	
	
	
}


