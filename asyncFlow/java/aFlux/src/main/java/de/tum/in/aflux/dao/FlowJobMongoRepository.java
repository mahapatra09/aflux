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
