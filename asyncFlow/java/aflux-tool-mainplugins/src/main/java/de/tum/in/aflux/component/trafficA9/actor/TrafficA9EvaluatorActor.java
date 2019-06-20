

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

package de.tum.in.aflux.component.trafficA9.actor;

import java.io.IOException;
import java.util.Map;

import de.tum.in.aflux.component.trafficA9.TrafficA9Constants;
import de.tum.in.aflux.component.trafficA9.model.EvaluatorResult;
import de.tum.in.aflux.component.trafficA9.model.State;
import de.tum.in.aflux.component.trafficA9.model.TitleResult;
import de.tum.in.aflux.component.trafficA9.model.TrafficA9Definition;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class TrafficA9EvaluatorActor extends AbstractAFluxActor {
	public TrafficA9EvaluatorActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,
			Map<String, String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner, properties, 0);
	}

	public TrafficA9EvaluatorActor() {
		this("-1", null, null, null);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		if (message instanceof TitleResult) { // initi results.csv
			this.setOutput(TrafficA9Constants.REPORT_OUTPUT, (TitleResult) message);
		} else {
			if (message instanceof TrafficA9Definition) {
				experimentFunctionFinish((TrafficA9Definition) message);
				this.setVariable("activity","currentProcessState","toRestart");
			} else {
				if (message instanceof String) { // publish kafka message
					System.out.println("publishing:"+message);
					this.setOutput(TrafficA9Constants.SHOULDER_CONTROL_OUTPUT, (String) message); 
				}
			}
		} 
	}
	
	
	
	private void experimentFunctionFinish(TrafficA9Definition wf) throws Exception {
		System.out.println("traffica9:experimentFunctionFinish");
		State state=(State) this.getVariable("activity", "state");
		System.out.println("traffica9:experimentFunctionFinish got state:"+state);
		long startTime = (Long) this.getVariable("activity","start_time");
		System.out.println("traffica9:experimentFunctionFinish got startTime:"+startTime);
	    EvaluatorResult result = evaluator(state,wf);
		System.out.println("traffica9:experimentFunctionFinish finish evaluator:"+result);
		wf.incrementExperimentCounter();
		long duration=  System.currentTimeMillis() - startTime;
		if (wf.getTotalExperiments()>0) {
			// never happens as is forever and does not have a totalExperiments to fulfill
			info("> Statistics     | " + wf.getExperimentCounter() + "/" + wf.getTotalExperiments()
            + " took " + duration + "ms" + " - remaining ~" + 
           (wf.getTotalExperiments() - wf.getExperimentCounter()) * duration / 1000 + "sec");
		}
		info("> FullState  | "+state.toString());
		info("> ResultValue  | "+result.toString());
		this.setOutput(TrafficA9Constants.REPORT_OUTPUT, result);
		System.out.println("Duration:"+duration);
		System.out.println("Result="+result);
	}

	
	/**
	 * Name based on rtx python source code
	 * @param resultState
	 * @param wf
	 * @return
	 * @throws Exception
	 */
	private EvaluatorResult evaluator(State resultState, TrafficA9Definition wf) throws Exception {
		System.out.println("TrafficA9ExecutionActor:evaluator:"+resultState);
		float occurrences = resultState.getOccurrences().getLower();
		float occurrencesCount = resultState.getOccurrencesCount().getLower();
		if (occurrencesCount==0) {
			occurrencesCount=1;
		}
		float product = occurrences / occurrencesCount;
		int tickCount = resultState.getTickCount();
		boolean hardShoulderOpen;
		EvaluatorResult evaluatorResult=(EvaluatorResult) this.getVariable("activity", "evaluatorResult");
		int opens=evaluatorResult.getOpens();
		int closes=evaluatorResult.getCloses();
		float productDiscriminator=new Float(this.getProperty(TrafficA9Constants.PRODUCT_DISCRIMINATOR_LABEL));
		if (product>=productDiscriminator) {
			System.out.println("TrafficA9ExecutionActor:evaluator:sending output 1");
			info("Command to open hard shoulder sent...","Fore.CYAN");
			this.setOutput(TrafficA9Constants.SHOULDER_CONTROL_OUTPUT,"{\"hard_shoulder\": 1}");
			opens++;
			hardShoulderOpen=true;
		} else { // product < 0.2
			System.out.println("TrafficA9ExecutionActor:evaluator:sending output 0");
			info("Command to close hard shoulder sent...","Fore.CYAN");
			this.setOutput(TrafficA9Constants.SHOULDER_CONTROL_OUTPUT,"{\"hard_shoulder\": 0}");
			hardShoulderOpen=false;
			closes++;
		}
		String mainFolder=(String) this.getVariable("activity", "mainFolder");
		String jobFolder=(String) this.getVariable("activity", "jobFolder");
		long overallTicks=evaluatorResult.getOverallTicks()+tickCount;
		evaluatorResult.setOverallTicks(overallTicks);
		evaluatorResult=new EvaluatorResult(mainFolder,jobFolder,evaluatorResult.getOverallTicks(),product,opens,closes);
		this.setVariable("activity", "evaluatorResult",evaluatorResult);
		return evaluatorResult;
	}

	
	private void info(String s,String color) throws IOException {
		info(s);
		System.out.println(s);
	}
	
	
	private void info(String s) throws IOException {
		String mainFolder=(String) this.getVariable("activity", "mainFolder");
		String jobFolder=(String) this.getVariable("activity", "jobFolder");
		this.sendOutput(s);
		this.appendToFile(mainFolder,jobFolder,"execution.log",s+"\n");
	}


}
