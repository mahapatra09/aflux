

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

package de.tum.in.aflux.component.twitter;

import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.component.twitter.actor.SearchTwitterActor;
import de.tum.in.aflux.tools.core.ToolProperty;

public class SearchTwitter extends AbstractMainExecutor {

	public static final String NAME="search Twitter";
	static public final ToolProperty[] connectionProperties={
			new ToolProperty("consumer key","",PropertyInputType.TEXT,null,"Consumer key given by twittr","",false),
			new ToolProperty("consumer secret","",PropertyInputType.TEXT,null,"Consumer secret string associated with customer key","",false),
			new ToolProperty("token key","",PropertyInputType.TEXT,null,"Token key associated to consumer key and account given by twitter","",false),
			new ToolProperty("token secret","",PropertyInputType.TEXT,null,"String secret corresponding to token key","",false)};
	public SearchTwitter() {
		super(NAME,
				SearchTwitterActor.class.getCanonicalName(),
				SearchTwitter.class.getName(),
				1, 
				1, 
				NodeLaunchType.LAUNCHED_BY_DATA,
				true,
				connectionProperties);
		this.setColor("#C5E1A5");
	}
	
	
    
    
}
