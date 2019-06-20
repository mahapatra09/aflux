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
