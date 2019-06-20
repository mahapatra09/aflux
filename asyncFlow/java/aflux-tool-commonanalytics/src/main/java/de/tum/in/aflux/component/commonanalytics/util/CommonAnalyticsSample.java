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
