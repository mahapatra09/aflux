

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

package de.tum.in.aflux.component.strings.actor;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class ConcatenateStringsActor extends AbstractAFluxActor{

	public ConcatenateStringsActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	public ConcatenateStringsActor() {
		this("-1",null,null,null);
	} 
	private String string1=null;
	private String string2=null;
	
	
	@Override
	protected void runCore(Object value) throws Exception {
		if (string1==null) {
			this.string1=(String) value;
		} else {
			this.string2=(String) value;
			String result=string1.concat(string2);
			this.string1=null;
			this.setOutput(1, result);
		}
	}

	
}
