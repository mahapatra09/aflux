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

package de.tum.in.aflux.component.mongodb.actor;


import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.tum.in.aflux.component.mongodb.MongoDBConstants;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;


/**
 * Component to read from a db
 * It reads an SQL query that returns a String[] as a result each one representing a json value and gets no inputs
 * It has properties to configure a Database connection
 * It creates and close the connection on each call
 * 
 * @author Tanmaya Mahapatra
 *
 */

public class WriteMongoDBActor extends AbstractAFluxActor {

	
	
	public WriteMongoDBActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,2);
	}

	
	public WriteMongoDBActor() {
		this("-1",null,null,null);
	}
	
	
	// Sending each string
	@Override
	protected void runCore(Object message) throws Exception {
		MongoClient mongoClient = new MongoClient( this.getProperty(MongoDBConstants.HOST) , Integer.valueOf(this.getProperty(MongoDBConstants.PORT)) );
		MongoDatabase database = mongoClient.getDatabase(this.getProperty(MongoDBConstants.DATABASE));
		MongoCollection<Document> collection=database.getCollection(this.getProperty(MongoDBConstants.COLLECTION));
		
		if (message instanceof String) {
			// insert new documents
			Document document= new Document("name","LogSample")
						.append("message", (String ) message);
			collection.insertOne(document);
		} else if (message instanceof List<?>) {
			for (String messageElement:(List<String>) message) {
				Document document= new Document("name","LogSample")
						.append("message", (String ) messageElement);
				collection.insertOne(document);
			}
		} else {
			this.sendOutput("Incorrect Type for WriteMongoDB");
			this.sendOutput(message.toString());
		} 
		
		this.setOutput(1, message);
	}

}
