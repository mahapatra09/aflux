

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

package de.tum.in.aflux.component.pig.actor;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.backend.executionengine.ExecJob;
import org.apache.pig.backend.executionengine.ExecJob.JOB_STATUS;
import org.apache.pig.data.Tuple;

import de.tum.in.aflux.component.hdfs.HDFSConstants;
import de.tum.in.aflux.component.pig.PigConstants;
import de.tum.in.aflux.bigdata.pig.model.PigExecutionPlan;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;
/**
 * 
 * 
 * Expected inputs for pig script
 * 1.- Is launched by signal but it can receive a previous script to be attached
 * 
 * 
 * Outputs
 * 1.- Results as String
 * 2.- Script as String
 * 
 * 
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class PigScriptActor extends AbstractAFluxActor {
	public PigScriptActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,-1);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		String previousScripts="";
		PigExecutionPlan executionPlan=new PigExecutionPlan();
		if (message!=null) {
			if (message instanceof PigExecutionPlan) {
				executionPlan=(PigExecutionPlan) message;
				previousScripts=executionPlan.getScript();
			}
		}
		
		String scriptSourceCode = previousScripts+this.getProperty(PigConstants.SCRIPT);
		executionPlan.add(scriptSourceCode);
		
		
//		List<ExecJob> jobs=	this.getFluxEnvironment().getPigTemplate().executeScript(scriptSourceCode);
// 		String result= convertJobResults2String(jobs);
		this.setOutput(1,scriptSourceCode);
		this.setOutput(2,executionPlan);
	}




private String convertJobResults2String(List<ExecJob> jobs) throws ExecException {
	String result="";
	for (int i=0;i<jobs.size();i++) {
		ExecJob job=jobs.get(i);
		int j=0;
		while (job.getStatus()!=JOB_STATUS.COMPLETED && j<10000) {
			j++;
		}
		if (job.getStatus()==JOB_STATUS.COMPLETED) {
			System.out.println(job.getStatus());
			Iterator<Tuple> tuples=job.getResults();
			while (tuples.hasNext()) {
				Tuple t=tuples.next();
				result=result.concat(t.toString());
			}
			
		}
	}
	return result;

}
}


