

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

package de.tum.in.aflux.dao;
import java.util.List;

/**
 * Tool Mongo Repository interface 
 * @author Tanmaya Mahapatra
 */
import org.springframework.data.mongodb.repository.MongoRepository;

import de.tum.in.aflux.model.FlowElementType;
import de.tum.in.aflux.tools.core.ToolImplementationType;

public interface FlowElementTypeRepository extends MongoRepository<FlowElementType, String>{
	public FlowElementType findByName(String name);

	public FlowElementType findByClassName(String className);
	
	public FlowElementType findByJarLocationAndJarNameAndClassName(String jarLocation,String jarName,String className);
	
	public List<FlowElementType> findByType(ToolImplementationType type);

	public List<FlowElementType> findByJobName(String jobName);
}
