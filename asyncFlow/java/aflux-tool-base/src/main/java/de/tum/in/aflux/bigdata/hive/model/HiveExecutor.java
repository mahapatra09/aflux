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
