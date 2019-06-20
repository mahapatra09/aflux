

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

package de.tum.in.aflux.component.mongodb;


import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.component.mongodb.actor.ReadMongoDBActor;
import de.tum.in.aflux.tools.core.ToolProperty;

/**
 * Component to read from a db
 * It reads an SQL query that returns a String[] as a result each one representing a json value and gets no inputs
 * It has properties to configure a Database connection
 * It creates and close the connection on each call
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class ReadMongoDB extends AbstractMainExecutor {
	
	public static final String NAME="read Mongo DB";

	static public ToolProperty[] connectionProperties={
				new ToolProperty(MongoDBConstants.HOST,"localhost",PropertyInputType.TEXT,null,"Mongo db Host","",false),
				new ToolProperty(MongoDBConstants.PORT,"27017",PropertyInputType.TEXT,null,"Numeric value of port of MongoDb connection","",false),
				new ToolProperty(MongoDBConstants.DATABASE,"",PropertyInputType.TEXT,null,"Database name","",false),
				new ToolProperty(MongoDBConstants.COLLECTION,"",PropertyInputType.TEXT,null,"Collection name","",false)};

	public ReadMongoDB() {
		super(NAME,
				ReadMongoDBActor.class.getCanonicalName(),
				ReadMongoDB.class.getName(),
				1, 
				2,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true,
				connectionProperties);
		this.setColor(MongoDBConstants.COLOR);
	}
	  
}
