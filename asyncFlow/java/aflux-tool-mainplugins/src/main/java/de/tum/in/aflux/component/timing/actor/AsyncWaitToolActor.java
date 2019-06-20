

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

package de.tum.in.aflux.component.timing.actor;

import java.util.Map;

import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class AsyncWaitToolActor extends AbstractAFluxActor {

	public AsyncWaitToolActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,
			Map<String, String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner, properties,-1);
	}
	
	public AsyncWaitToolActor() {
		this("-1",null,null,null);
	}
	
	@Override
	protected void runCore(Object message) throws Exception {
		this.sendOutput("Begin async wait:"+this.getProperty("value")+"");
		Thread.sleep(new Long(this.getProperty("value")));
		this.sendOutput("End async wait:"+this.getProperty("value")+"");
		this.setOutput(1, message);
	}

}
