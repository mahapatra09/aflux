

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

package de.tum.in.aflux.flux_engine.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.data.hadoop.pig.PigOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

// import de.tum.in.aflux.bigdata.service.HDFSOperationsService;
import de.tum.in.aflux.flux_engine.launcher.FluxCompleteEnvironment;
import de.tum.in.aflux.flux_engine.model.FluxOutput;
import de.tum.in.aflux.model.FluxLog;


/**
 * Environment Accessible for the runner.
 * This environment can have any shared resources among executions and actors.
 * This environment has initially an output where all actors can send messages
 * It could be extended
 * 
 * 
 * @author  Tanmaya Mahapatra
 *
 */
@Component
public class FluxEnvironmentImpl implements FluxCompleteEnvironment{
	

	private FluxOutput output=new FluxOutput();
	private FluxOutput stdError=new FluxOutput();
	
	// TODO: Change variable schema for a unique string key formed by an id based on job - activity - flux
	private Map<String,VariableValue> jobVariables=new HashMap<String,VariableValue>();
	private Map<String,Map<Long,Map<String,VariableValue>>> activityVariables=new HashMap<String,Map<Long,Map<String,VariableValue>>>();
	private Map<String,Map<Long,Map<String,Map<String,VariableValue>>>> actorVariables=new HashMap<String,Map<Long,Map<String,Map<String,VariableValue>>>>();
	
	@Autowired
	private FluxLog log;

	private class VariableValue {
		public String jobId;
		public Long activityId;
		public String fluxId;
		public Object value;
		
		public VariableValue(String jobId, Long activityId, String fluxId, Object value) {
			super();
			this.jobId = jobId;
			this.activityId = activityId;
			this.fluxId = fluxId;
			this.value = value;
		}
		
		
	}
	
	// TODO: To be reactivated - replaced by hadoopConfiguration more general
	// @Autowired
	// private HDFSOperationsService hdfs_operations;
	
	@Autowired
	private org.apache.hadoop.conf.Configuration hadoopConfiguration;
	
	
	@Autowired
	private FsShell fsShell;

	
	@Autowired
	private PigOperations pigTemplate;
	
	@Autowired
    private JdbcTemplate hiveTemplate;

	/**
	 * valid status : INACTIVE / RUNNING
	 */
	private String status=INACTIVE_STATUS;
	
	
	public void showMessage(String fluxId,String message) {
		this.getOutput().sendMessage(fluxId, message);
	}	

	public FluxOutput getOutput() {
		return output;
	}

	public void setOutput(FluxOutput output) {
		this.output = output;
	}

	public FluxLog getLog() {
		return log;
	}

	public void setLog(FluxLog log) {
		this.log = log;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FluxOutput getStdError() {
		return stdError;
	}

	public void setStdError(FluxOutput stdError) {
		this.stdError = stdError;
	}

	@Override
	public void setHadoopConfiguration(Configuration hadoopConfiguration) {
		this.hadoopConfiguration=hadoopConfiguration;
	}
	@Override
	public Configuration getHadoopConfiguration() {
		return this.hadoopConfiguration;
	}
	public FsShell getFsShell() {
		return fsShell;
	}
	public PigOperations getPigTemplate() {
		return pigTemplate;
	}
	public JdbcTemplate getHiveTemplate() {
		return hiveTemplate;
	}

	@Override
	public void setVariable(String jobId, Long activityId, String fluxId, String scope, String key, Object value) {
		if (scope.equals("job")) {
			this.jobVariables.put(key,new VariableValue(jobId,activityId,fluxId,value));
		} else if (scope.equals("activity")) {
			if (!this.activityVariables.containsKey(jobId)) {
				this.activityVariables.put(jobId,new HashMap<Long,Map<String,VariableValue>>());
			};
			if (!this.activityVariables.get(jobId).containsKey(activityId)) {
				this.activityVariables.get(jobId).put(activityId,new HashMap<String,VariableValue>());
			}
			this.activityVariables.get(jobId).get(activityId).put(key,new VariableValue(jobId,activityId,fluxId,value));
		} else if (scope.equals("local")) {
			if (!this.actorVariables.containsKey(jobId)) {
				this.actorVariables.put(jobId,new HashMap<Long,Map<String,Map<String,VariableValue>>>());
			};
			if (!this.actorVariables.get(jobId).containsKey(activityId)) {
				this.actorVariables.get(jobId).put(activityId,new HashMap<String,Map<String,VariableValue>>());
			};
			if (!this.actorVariables.get(jobId).get(activityId).containsKey(fluxId)) {
				this.actorVariables.get(jobId).get(activityId).put(fluxId,new HashMap<String,VariableValue>());
			};
			this.actorVariables.get(jobId).get(activityId).get(fluxId).put(key,new VariableValue(jobId,activityId,fluxId,value));
		}
	}

	@Override
	public Object getVariable(String jobId, Long activityId, String fluxId, String scope, String key) {
		Object returnValue=null;
		if (scope.equals("job")) {
			returnValue=this.jobVariables.get(key);
		} else if (scope.equals("activity")) {
			Map<Long, Map<String,VariableValue>> myJobVariables=this.activityVariables.get(jobId);
			if (myJobVariables!=null) {
				Map<String,VariableValue> myActivityVariables=myJobVariables.get(activityId);
				if (myActivityVariables!=null) {
					returnValue=myActivityVariables.get(key);
				}
			}
		} else if (scope.equals("local")) {
			Map<Long, Map<String,Map<String,VariableValue>>> myJobVariables=this.actorVariables.get(jobId);
			if (myJobVariables!=null) {
				Map<String, Map<String,VariableValue>> myActivityVariables=myJobVariables.get(activityId);
				if (myActivityVariables!=null) {
					Map<String,VariableValue> fluxVariables=myActivityVariables.get(fluxId);
					if (fluxVariables!=null) {
						returnValue=fluxVariables.get(key);
					}
				}
			}
		}
		if (returnValue!=null) {
			returnValue=((VariableValue) returnValue).value;
		}
		return returnValue;
	}

	@Override
	public void removeVariable(String jobId, Long activityId, String fluxId, String scope, String key) {
		if (scope.equals("job")) {
			this.jobVariables.remove(key);
		} else if (scope.equals("activity")) {
			Map<Long, Map<String,VariableValue>> myJobVariables=this.activityVariables.get(jobId);
			if (myJobVariables!=null) {
				Map<String,VariableValue> myActivityVariables=myJobVariables.get(activityId);
				if (myActivityVariables!=null) {
					myActivityVariables.remove(key);
				}
			}
		} else if (scope.equals("local")) {
			Map<Long, Map<String,Map<String,VariableValue>>> myJobVariables=this.actorVariables.get(jobId);
			if (myJobVariables!=null) {
				Map<String, Map<String, VariableValue>> myActivityVariables=myJobVariables.get(activityId);
				if (myActivityVariables!=null) {
					Map<String,VariableValue> fluxVariables=myActivityVariables.get(fluxId);
					if (fluxVariables!=null) {
						fluxVariables.remove(key);
					}
				}
			}
		}
	}


}
