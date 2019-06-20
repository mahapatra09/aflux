

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

package de.tum.in.aflux.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import de.tum.in.aflux.model.FlowJob;

/**
 * Job  Mongo Repository interface 
 * @author Tanmaya Mahapatra
 *
 */
public interface FlowJobMongoRepository extends MongoRepository<FlowJob, String> ,QueryDslPredicateExecutor<FlowJob> {
	/**
	 * Find unique job given the name
	 * @param name
	 * @return
	 */
	public FlowJob findByName(String name);

	/**
	 * Get from jobs all activities marked as subflows as a list of SubFlows
	 * @return
	 */
	@Query(value = "{\"activities.properties\":{$elemMatch:{name:\"subFlow\",value:\"true\"}}}")
	public List<FlowJob> findFlowJobsContainingSubFlows();	
}
