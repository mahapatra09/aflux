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

package de.tum.in.aflux.component.twitter.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SearchTwitterActor extends AbstractAFluxActor {
	

	
	public SearchTwitterActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,
			Map<String, String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner, properties,-1);
	}

	public SearchTwitterActor() {
		this("-1",null,null,null);
	} 		

	@Override
	protected void runCore(Object message) throws Exception {
		if (message instanceof String) {
			List<String> twitterMessages=this.searchTwitter((String) message);
			this.setOutput(1,twitterMessages);
		} else {
			this.sendOutput("Invalid type message: "+message.getClass().getName());
		}
	}
	
	
	private Twitter getTwitterConnection() throws Exception {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(this.getProperty("consumer key"))
		  .setOAuthConsumerSecret(this.getProperty("consumer secret"))
		  .setOAuthAccessToken(this.getProperty("token key"))
		  .setOAuthAccessTokenSecret(this.getProperty("token secret"));
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();		
		return twitter;
	}
	
	private List<String> searchTwitter(String searchString) throws Exception {
	    // The factory instance is re-useable and thread safe.
	    Twitter twitter = this.getTwitterConnection();
	    Query query = new Query(searchString);
	    QueryResult result = twitter.search(query);
	    List<String> stringResult=new ArrayList<String>();
	    for (Status status : result.getTweets()) {
	    	stringResult.add("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }		
	    return stringResult;
	}


}
