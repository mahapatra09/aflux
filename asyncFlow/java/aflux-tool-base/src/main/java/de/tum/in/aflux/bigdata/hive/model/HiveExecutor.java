

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

package de.tum.in.aflux.bigdata.hive.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class HiveExecutor {

	public static List<Map<String, Object>> executeHivePlan(HiveExecutionPlan executionPlan,AbstractAFluxActor actor, Logger log) {
		List<Map<String, Object>> rows=new ArrayList<Map<String,Object>>();
		log.info("aflux Hive Executor----------------------");
		for (HiveExecutionSentence sentence:executionPlan.getSentences()) {
			actor.sendOutput(sentence.getSentence());
			log.debug(sentence.toString());
			if (sentence.getType().equals(HiveSentenceType.QUERY)) {
				if (sentence.getNewTableName()==null || sentence.getNewTableName().trim().length()==0) {
					rows=actor.getFluxEnvironment().getHiveTemplate().queryForList(sentence.getSentence());
				} else {
					actor.getFluxEnvironment().getHiveTemplate().update(
							HiveBuilder.generateCreationSentence(sentence.getNewTableName(),sentence.getSentence()));
				}
			} else {
				actor.getFluxEnvironment().getHiveTemplate().update(sentence.getSentence());
			}
		}
		return rows;
		
	}
	
	
}
