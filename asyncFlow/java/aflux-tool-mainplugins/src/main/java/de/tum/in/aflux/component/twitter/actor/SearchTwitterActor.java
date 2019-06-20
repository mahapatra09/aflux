

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
