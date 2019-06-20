

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

package de.tum.in.aflux.tools.core.akkaSpring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

import java.util.Map;

import org.springframework.context.ApplicationContext;
/**
 * Class to generate actors from spring beans
 * To be deprecated as spring prototype beans doesnt monage destory and error prone for memory leaks in this context
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class SpringActorProducer implements IndirectActorProducer {

    final private ApplicationContext applicationContext;
    final private String actorBeanName;
    final private Long fluxId;
    final private FluxEnvironment fluxEnvironment;
    final private FluxRunner fluxRunner;
    final private Map<String,String> properties;
    final int durationOutputConnector;

    public SpringActorProducer(
    		ApplicationContext applicationContext, 
    		String actorBeanName,
    		Long fluxId,
    		FluxEnvironment fluxEnvironment,
    		FluxRunner fluxRunner,
    		Map<String,String> properties,
    		int durationOutputConnector) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
        this.fluxId=fluxId;
        this.fluxEnvironment=fluxEnvironment;
        this.fluxRunner=fluxRunner;
        this.properties=properties;
        this.durationOutputConnector=durationOutputConnector;
    }

    @Override
    public AbstractAFluxActor produce() {
    	AbstractAFluxActor result;
    	if (this.fluxId==null) {
    		result = (AbstractAFluxActor) applicationContext.getBean(actorBeanName);
    	} else {
    		result = 
    				(AbstractAFluxActor) applicationContext.getBean(
    						actorBeanName,
    						new Object[] {this.fluxId,this.fluxEnvironment,this.fluxRunner,this.properties,this.durationOutputConnector});
    	}
        return result; 
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }

	public Long getFluxId() {
		return fluxId;
	}

	public FluxEnvironment getFluxEnvironment() {
		return fluxEnvironment;
	}

	public FluxRunner getFluxRunner() {
		return fluxRunner;
	}

	public int getDurationOutputConnector() {
		return durationOutputConnector;
	}


}
