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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.data.hadoop.pig.PigCallback;

import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.backend.executionengine.ExecJob;
import org.apache.pig.backend.executionengine.ExecJob.JOB_STATUS;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.newplan.Operator;


public class PigExecutor {

	public static String execute(PigExecutionPlan executionPlan, AbstractAFluxActor actor,Logger log) throws ExecException {
		PigExecutionStep lastStep=null;
		int stepsSize=executionPlan.getSteps().size();
		String result="";
		if (stepsSize>0) {
			lastStep=executionPlan.getSteps().get(stepsSize-1);
			if (lastStep.getSentence().substring(0,9).equals("DESCRIBE ")) {
				result=executeDescribe(executionPlan,actor,log);
			} else if  (lastStep.getSentence().substring(0,11).equals("ILLUSTRATE ")) {
				result=executeIllustrate(executionPlan,actor,log);
			} else if  (lastStep.getSentence().substring(0,8).equals("EXPLAIN ")) {
				result=executeExplain(executionPlan,actor,log);
			} else {
				result=executeByPigTemplate(executionPlan, actor, log);
			 }
		}
		
		return result;
		
		
		
		/*
		Set<String> result1=actor.getFluxEnvironment().getPigTemplate().execute(new PigCallback<Set<String>>() {
	          public Set<String> doInPig(PigServer pig) throws ExecException, IOException {
	        	  return pig.getAliasKeySet();
	          }
	       });
		log.debug("tesult1 from PigExecutor");
		log.debug(result1.toString());
*/

	}

	
	
	
	
	
	private static String executeByPigTemplate(PigExecutionPlan executionPlan,AbstractAFluxActor actor,Logger log) throws ExecException {
		String finalScript=executionPlan.getScript();
		log.debug("aflux Pig Executor----------------------");
		log.debug(finalScript);
		List<ExecJob> jobs=	null;
		jobs=actor.getFluxEnvironment().getPigTemplate().executeScript(finalScript);
		String result= convertJobResults2String(jobs);
		log.debug(result);
		return result;
		
	}
	
	
	private static String executeDescribe(PigExecutionPlan executionPlan,AbstractAFluxActor actor,Logger log) {
		final List<String> finalScriptList=executionPlan.getScriptAsList();
		final String lastAlias=executionPlan.getSteps().get(executionPlan.getSteps().size()-1).getAlias();
		String result="";
			Schema resultDescribe=actor.getFluxEnvironment().getPigTemplate().execute(new PigCallback<Schema>() {
		          public Schema doInPig(PigServer pig) throws ExecException, IOException {
		        	  pig.setBatchOn();
		        	  System.out.println("resultDescribe----");
		        	  for (int i=0;i<finalScriptList.size()-1;i++) {
		        		  pig.registerQuery(finalScriptList.get(i));
		        	  }
		        	  pig.executeBatch();
		        	  return pig.dumpSchema(lastAlias);
		          }
		       });
			log.debug("resultDescribe from PigExecutor");
			result=resultDescribe.toString();
		return result;
		}

	

	private static String executeExplain(PigExecutionPlan executionPlan,AbstractAFluxActor actor,Logger log) {
		final List<String> finalScriptList=executionPlan.getScriptAsList();
		final String lastAlias=executionPlan.getSteps().get(executionPlan.getSteps().size()-1).getAlias();
		String result="";
			String resultExplain=actor.getFluxEnvironment().getPigTemplate().execute(new PigCallback<String>() {
		          public String doInPig(PigServer pig) throws ExecException, IOException {
		        	  pig.setBatchOn();
		        	  System.out.println("resultExplain----");
		        	  for (int i=0;i<finalScriptList.size()-1;i++) {
		        		  pig.registerQuery(finalScriptList.get(i));
		        	  }
		        	  pig.executeBatch();
		        	  
		        	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        	  PrintStream ps = new PrintStream(baos);
		        	  pig.explain(lastAlias,ps);
		        	  String result = new String(baos.toByteArray(), StandardCharsets.UTF_8);
		        	  return result;
		          }
		       });
			log.debug("resultExplain from PigExecutor");
		return resultExplain;
	}

	
	
	private static String executeIllustrate(PigExecutionPlan executionPlan,AbstractAFluxActor actor,Logger log) {
		final List<String> finalScriptList=executionPlan.getScriptAsList();
		final String lastAlias=executionPlan.getSteps().get(executionPlan.getSteps().size()-1).getAlias();
		String result="";
		 Map<Operator, DataBag>  resultIllustrate=actor.getFluxEnvironment().getPigTemplate().execute(new PigCallback< Map<Operator, DataBag> >() {
	          public  Map<Operator, DataBag>  doInPig(PigServer pig) throws ExecException, IOException {
	        	  pig.setBatchOn();
	        	  System.out.println("resultIllustrate----");
	        	  for (int i=0;i<finalScriptList.size()-1;i++) {
	        		  pig.registerQuery(finalScriptList.get(i));
	        	  }
	        	  pig.executeBatch();
	        	  return pig.getExamples(null);
	          }
	       });
		 log.debug("illustrate by PigExecutor");
			result=resultIllustrate.toString();
		
		
		
		return result;
	}
	
	
	
	
	private static String convertJobResults2String(List<ExecJob> jobs) throws ExecException {
		String result="";
		for (int i=0;i<jobs.size();i++) {
			ExecJob job=jobs.get(i);
			
			int j=0;
			while (job.getStatus()!=JOB_STATUS.COMPLETED && j<10000) {
				j++;
			}
			if (job.getStatus()==JOB_STATUS.COMPLETED) {
				System.out.println(job.getStatus());
				Iterator<Tuple> tuples=job.getResults();
				while (tuples.hasNext()) {
					Tuple t=tuples.next();
					result=result.concat(t.toString());
				}
				
			}
		}
		return result;

	}
	
	
}
