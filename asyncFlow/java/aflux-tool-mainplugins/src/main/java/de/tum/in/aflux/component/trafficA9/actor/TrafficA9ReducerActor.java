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

package de.tum.in.aflux.component.trafficA9.actor;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import de.tum.in.aflux.component.trafficA9.TrafficA9Constants;
import de.tum.in.aflux.component.trafficA9.model.EvaluatorResult;
import de.tum.in.aflux.component.trafficA9.model.Occupancy;
import de.tum.in.aflux.component.trafficA9.model.Speed;
import de.tum.in.aflux.component.trafficA9.model.State;
import de.tum.in.aflux.component.trafficA9.model.Tick;
import de.tum.in.aflux.component.trafficA9.model.TitleResult;
import de.tum.in.aflux.component.trafficA9.model.TrafficA9Definition;
import de.tum.in.aflux.component.trafficA9.model.TrafficA9Environment;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class TrafficA9ReducerActor extends AbstractAFluxActor {
	public TrafficA9ReducerActor(String fluxId, FluxEnvironment fluxEnvironment, FluxRunner fluxRunner,
			Map<String, String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner, properties, 0);
	}

	public TrafficA9ReducerActor() {
		this("-1", null, null, null);
	}

	@Override
	protected void runCore(Object message) throws Exception {
		String currentProcessState =null;
		Object currentProcessStateObject =this.getVariable("activity", "currentProcessState");
		if (currentProcessStateObject!=null) {
			currentProcessState=(String) currentProcessStateObject;
		} else {
			currentProcessState="not-initiated";
		}
		if (message instanceof TrafficA9Definition) {
			this.setVariable("activity", "currentProcessState", "initiating");
			// start execution
			TrafficA9Definition wf = (TrafficA9Definition) message;
			Map.Entry<String, Boolean> knobs = new AbstractMap.SimpleEntry<String, Boolean>("forever", true);
			TrafficA9Environment trafficA9Environment = new TrafficA9Environment();
			trafficA9Environment.setKnobs(knobs);
			int ignoreFirstNResults=new Integer(this.getProperty(TrafficA9Constants.IGNORE_FIRST_RESULTS));
			int sampleSize=new Integer(this.getProperty(TrafficA9Constants.SAMPLE_SIZE));
			trafficA9Environment.setIgnoreFirstNResults(ignoreFirstNResults);
			trafficA9Environment.setSampleSize(sampleSize);
			experimentFunctionInit(wf, trafficA9Environment);
			this.setVariable("activity", "currentProcessState", "initiated");
		} else if (currentProcessState.equals("toRestart")) {
			if (message instanceof Tick) {
				this.setVariable("activity", "currentProcessState", "initiating");
				TrafficA9Definition wf = (TrafficA9Definition) this.getVariable("local", "wf");
				TrafficA9Environment trafficA9Environment=(TrafficA9Environment) this.getVariable("local", "exp");
				experimentFunctionInit(wf,trafficA9Environment);
				this.setVariable("activity", "currentProcessState", "initiated");
			}
			
		} else if (currentProcessState.equals("initiated")) {
			if (message instanceof Tick) {
				TrafficA9Environment exp=(TrafficA9Environment) this.getVariable("local", "exp");
				int toIgnore = exp.getIgnoreFirstNResults();
				if (toIgnore > 0) {
					int i = (Integer) this.getVariable("local", "to_ignore:i");
					this.setVariable("local", "to_ignore:i", i + 1);
					process("IgnoreSamples | ", i, toIgnore);
				} else {
					int sampleSize = exp.getSampleSize();
					int i = (Integer) this.getVariable("local", "sample_size:i");
					if (i < sampleSize) {
						System.out.println("TrafficA9ExecutionActor: i<sampleSize : "+i);
						Tick newData = (Tick) message;

						this.setVariable(
								"activity", 
								"state", 
								ticksDataReducer(
										(State) this.getVariable("activity", "state"),
										newData, 
										(TrafficA9Definition) this.getVariable("local", "wf")));
						i++;
						this.setVariable("local", "sample_size:i", i);
						process("CollectSamples | ", i, sampleSize);
					} else {
						System.out.println("TrafficA9ExecutionActor: i>=sampleSize");
						this.setVariable("activity", "currentProcessState", "summary");
						TrafficA9Definition wf=(TrafficA9Definition) this.getVariable("local", "wf");
						System.out.println("output wf:"+wf);
						this.setOutput(1,wf);
					}
				}
			} else if (message instanceof Occupancy) {
				State state=(State) this.getVariable("activity", "state");
				System.out.println("state before occupancy:"+state);
				
				this.setVariable(
						"activity", 
						"state", 
						occupanciesDataReducer(
								state,
								(Occupancy) message, 
								(TrafficA9Definition) this.getVariable("local", "wf")));
				state=(State) this.getVariable("activity", "state");
				System.out.println("state after occupancy:"+state);
			} else if (message instanceof Speed) {
				this.setVariable("activity", "state", speedsDataReducer((State) this.getVariable("activity", "state"),
						(Speed) message, (TrafficA9Definition) this.getVariable("local", "wf")));
			}
		}

	}

	private State speedsDataReducer(State state, Speed newData, TrafficA9Definition wf) {
		int cnt = state.getSpeedsCount();
		state.setSpeedsAvg((state.getSpeedsAvg() * cnt + newData.getSpeed()) / (cnt + 1));
		state.setSpeedsCount(state.getSpeedsCount() + 1);
		return state;
	}

	private State occupanciesDataReducer(State state, Occupancy newData, TrafficA9Definition wf) throws NumberFormatException, Exception {
		System.out.println("occupanciesDataReducer");
		float occupancyFilter=new Float(this.getProperty(TrafficA9Constants.OCCUPANCY_FILTER));
		System.out.println("occupancyFilter"+occupancyFilter);
		System.out.println("newData.getOccupancy()"+newData.getOccupancy());
		
		if (newData.getOccupancy() != occupancyFilter) {
			System.out.println("adding occupancy data:"+newData.getOccupancy());
			state.getOccurrences().setLower(state.getOccurrences().getLower() + newData.getOccupancy());
			state.getOccurrencesCount().setLower(state.getOccurrencesCount().getLower() + 1);
		} else {
			System.out.println("occupancy="+newData.getOccupancy());
		}
		return state;
	}

	private State ticksDataReducer(State state, Tick newData, TrafficA9Definition wf) {
		state.setTickCount(state.getTickCount() + 1);
		return state;
	}

	private void experimentFunctionInit(TrafficA9Definition wf, TrafficA9Environment exp) throws Exception {
		boolean firstTime=true;
		String mainFolder=(String) this.getVariable("activity", "mainFolder");
		String jobFolder=(String) this.getVariable("activity", "jobFolder");
		if (mainFolder!=null) {
			firstTime=false;
		}
		Long startTime = System.currentTimeMillis();
		if (firstTime) {
			mainFolder="trafficA9";
			jobFolder=this.getProposedJobFolder();
			this.setVariable("activity", "mainFolder", mainFolder);
			this.setVariable("activity", "jobFolder", jobFolder);
		}
		
		
		
		// # start
		info(">");
		info("> KnobValues     | " + exp.getKnobs().toString());
		// # create new state
		State state = wf.stateInitializer(null);

		// # apply changes to system
		// wf.change_provider["instance"].applyChange(change_creator(exp["knobs"],wf))
		
		
		System.out.println("TrafficA9ExecutionActor publish:");
		String message="{\"forever\": "+(exp.getKnobs().getValue()?"true":"false")+"}";
		this.setOutput(1, message);
		this.setVariable("activity", "start_time", startTime);
		this.setVariable("local", "wf", wf);
		this.setVariable("activity", "state", state);
		this.setVariable("local", "exp", exp);
		this.setVariable("local", "to_ignore:i", 0);
		this.setVariable("local", "sample_size:i", 0);
		EvaluatorResult evaluatorResult=(EvaluatorResult) this.getVariable("activity", "evaluatorResult");
		long overallTicks=0;
		int opens=0;
		int closes=0;
		if (evaluatorResult!=null) {
			overallTicks=evaluatorResult.getOverallTicks();
			opens=evaluatorResult.getOpens();
			closes=evaluatorResult.getCloses();
		}
		this.setVariable("activity","evaluatorResult",new EvaluatorResult(mainFolder,jobFolder,overallTicks,0,opens,closes));
		if (firstTime) {
			this.setOutput(1, new TitleResult(mainFolder,jobFolder));
		}
	}

	
	private void print(String s) {
		System.out.println(s);
	}

	private void info(String s,String color) throws IOException {
		info(s);
	}

	
	private void info(String s) throws IOException {
		String mainFolder=(String) this.getVariable("activity", "mainFolder");
		String jobFolder=(String) this.getVariable("activity", "jobFolder");
		this.sendOutput(s);
		this.appendToFile(mainFolder,jobFolder,"execution.log",s+"\n");
	}


	private void process(String preText, int i, int total) {
		// TODO Auto-generated method stub
		// method to show progress
		System.out.print("\r");
		String sizeStr="> "+preText+"[";
		int percentage = (int) 30.0*i /total;
		for (int j=0;j<percentage;j++) {
			sizeStr=sizeStr+"#";
		}
		for (int k=percentage;k<percentage;k++) {
			sizeStr=sizeStr+".";
		}
		sizeStr=sizeStr+"] Target: "+total+" | Done: "+i;
		System.out.println(sizeStr+"\r");
	}
	
	

}
