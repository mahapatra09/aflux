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

package de.tum.in.aflux.component.mongodb;


import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.component.mongodb.actor.WriteMongoDBActor;
import de.tum.in.aflux.tools.core.AbstractMainBaseTool;
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

public class WriteMongoDB extends AbstractMainExecutor {
	
	public static final String NAME="write Mongo DB";

	static public ToolProperty[] connectionProperties={
				new ToolProperty(MongoDBConstants.HOST,"localhost",PropertyInputType.TEXT,null,"Mongo db Host","",false),
				new ToolProperty(MongoDBConstants.PORT,"27017",PropertyInputType.TEXT,null,"Numeric value of port of MongoDb connection","",false),
				new ToolProperty(MongoDBConstants.DATABASE,"",PropertyInputType.TEXT,null,"Database name","",false),
				new ToolProperty(MongoDBConstants.COLLECTION,"",PropertyInputType.TEXT,null,"Collection name","",false)};
	public WriteMongoDB() {
		super(NAME,
				WriteMongoDBActor.class.getCanonicalName(),
				WriteMongoDB.class.getName(),
				1, 
				2,
				NodeLaunchType.LAUNCHED_BY_SIGNAL,
				true,
				connectionProperties);
		this.setColor(MongoDBConstants.COLOR);

	}
	  
}
