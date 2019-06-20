

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

package de.tum.in.aflux.component.commonanalytics.util;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import de.tum.in.aflux.bigdata.hive.model.HiveExecutionPlan;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsLexer;
import de.tum.in.aflux.component.commonanalytics.grammar.CommonAnalyticsParser;


//Please resolve the imports for the classes used.
public class CommonAnalyticsSample {
public static void main(String[] args) throws IOException {
  //Reading the DSL script
  InputStream is = 
          ClassLoader.getSystemResourceAsStream("resources/afluxSentencesSample.gr");
  
  //Loading the DSL script into the ANTLR stream.
  CharStream cs = new ANTLRInputStream(is);
  
  //Passing the input to the lexer to create tokens
  CommonAnalyticsLexer lexer = new CommonAnalyticsLexer(cs);
  
  CommonTokenStream tokens = new CommonTokenStream(lexer);
  
  //Passing the tokens to the parser to create the parse trea. 
  CommonAnalyticsParser parser = new CommonAnalyticsParser(tokens);
  
  //Semantic model to be populated
  HiveExecutionPlan executionPlan = new HiveExecutionPlan();
  PigExecutionPlan pigExecutionPlan = new PigExecutionPlan();
  //Adding the listener to facilitate walking through parse tree. 
  parser.addParseListener(new CommonAnalyticsHiveListener(executionPlan));
  parser.addParseListener(new CommonAnalyticsPigListener(pigExecutionPlan));
  
  
  //invoking the parser. 
  parser.script();
  
  HiveExecutionPlan.printExecutionPlan(executionPlan);
  PigExecutionPlan.printExecutionPlan(pigExecutionPlan);
}
}
