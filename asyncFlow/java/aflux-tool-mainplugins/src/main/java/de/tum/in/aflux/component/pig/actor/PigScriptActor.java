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


