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

package de.tum.in.aflux.flux_engine.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.KafkaException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.flux_engine.kafka.KafkaAfluxConsumer;
import de.tum.in.aflux.flux_engine.kafka.KafkaAfluxProducer;
import de.tum.in.aflux.flux_engine.launcher.FluxCompleteEnvironment;
import de.tum.in.aflux.flux_engine.launcher.FluxRunnerLauncher;
import de.tum.in.aflux.flux_engine.mqtt.MqttAfluxPublisher;
import de.tum.in.aflux.flux_engine.mqtt.MqttAfluxSubscription;
import de.tum.in.aflux.kafka.model.KafkaAfluxReconnectSignal;
import de.tum.in.aflux.model.FlowActivity;
import de.tum.in.aflux.model.FlowExecutionConnector;
import de.tum.in.aflux.model.FlowExecutionModel;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.mqtt.model.MqttReconnectSignal;
import de.tum.in.aflux.service.FluxJobService;
import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.util.AFluxUtils;
import de.tum.in.aflux.util.FileSystemUtil;

/**
 * 
 * Main executor of single activities
 * It should be created an instance of this class for each running activity
 * It creates an AkkaActorSystem and runs those elements that has no predecessors
 * When the elements finish send it output to the target actors via broadcast methods
 * Async connections are like dummy messages sent initially by the actors
 * 
 * @author Tanmaya Mahapatra
 *
 */
public class FluxRunnerImpl implements FluxRunner,FluxRunnerLauncher{
	

	private final Logger log = LoggerFactory.getLogger(this.getClass());	

	/**
	 * Shared context of all executions
	 * 
	 */
	private FluxCompleteEnvironment environment;
	
	/**
	 * Current running job
	 */
	private FlowJob job;
	/**
	 * Current running activity
	 */
	private FlowActivity activity;

	/**
	 * Element that takes into account running processes in order to know if an activity is finished
	 */
	private ExecutionProcessManager executionProcessManager;
	
	/**
	 * Main framework of execution where all running activities are launched
	 * In this class Is used to force stop
	 */
	FluxRunnerEnvironmentImpl caller;

	
	/**
	 * Akka actor System
	 */
	ActorSystem actorSystem;

	
	/**
	 * Data defition of the main flow arranged to be executed including a map of all nodes (included nodes present in subflows) and connectors
	 * Connectors are rearranged to deal with the amp of nodes
	 */
	private FlowExecutionModel executionModel;
	
	// mqtt
	private Map<String,MqttAfluxPublisher> mqttPublishers=new HashMap<String,MqttAfluxPublisher>();
	
	private Map<String,MqttAfluxSubscription> mqttSubscriptions=new HashMap<String,MqttAfluxSubscription>();

	// kafka
	private Map<String,KafkaAfluxProducer> kafkaProducers=new HashMap<String,KafkaAfluxProducer>();
	
	private Map<String,KafkaAfluxConsumer> kafkaConsumers=new HashMap<String,KafkaAfluxConsumer>();
	
	public FluxRunnerImpl() {
		
	}
	
	/**
	 * 
	 * @param fluxEnvironment
	 * @param jobService
	 * @param jobName
	 * @param activityId
	 * @param fluxRunnerEnvironment
	 * 
	 * @author Tanmaya Mahapatra
	 */
	public FluxRunnerImpl(FluxCompleteEnvironment fluxEnvironment,
				FluxJobService jobService,
				String jobName,
				Long activityId,
				FluxRunnerEnvironmentImpl fluxRunnerEnvironment) {
		this.job=jobService.getByName(jobName);
		this.activity=jobService.getActivity(job,activityId);
		// retrieve activity
		this.environment=fluxEnvironment;
		//CheckCycle checkCycle=new CheckCycle();
		//this.isCyclic=checkCycle.checkCycle(this.activity.getElements(), this.activity.getConnectors());
		// this.environment.sendMessage(-1L, "jobName:"+jobName+" activity:"+activityName+" cyclic:"+this.isCyclic);
		this.executionProcessManager=new ExecutionProcessManager();
		this.caller=fluxRunnerEnvironment;
	}
	

	
	/**
	 * Start all actors that have no predecessors
	 * Launches the first elements to be executed
	 * Elements and connectors that can be executed are those that doess not need input data (LAUNCHED_BY_SIGNAL) and doesnt have predecessors
	 * @param executionModel
	 * @throws Exception
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	private void runExecutors(FlowExecutionModel executionModel) throws Exception {
		// main cycle
		AFluxExtensionImpl springExtension=new AFluxExtensionImpl();
		this.actorSystem=ActorSystem.create(this.activity.getName().replace(' ','_')+"_"+AFluxUtils.getNowId());
		// start all executors
		// makes all executors begin to listen to messages
		int idInstanceIndex=1;
		for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
			executorEntry.getValue().instantiate(actorSystem, springExtension,String.valueOf(idInstanceIndex++),this);
		}
		// launch all initial executors
		for (Entry<String, AbstractMainExecutor> executorEntry:executionModel.getExecutors().entrySet()) {
			if (executorEntry.getValue().getLaunchedBy()==NodeLaunchType.LAUNCHED_BY_SIGNAL && hasNoPredecessors(executorEntry.getKey())) {
				this.executionProcessManager.run(executorEntry.getValue());
				executorEntry.getValue().start();
			}
			
		}
	}
	
	
	/**
	 * Method to know if an element has no predecessors in the current execution Model
	 * The goal is to identify those nodes that does not need any previous node to start
	 * It was used initially to know if an element can be started at beggining
	 * It should be deprecated
	 * @param executorKey
	 * @return
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	private boolean hasNoPredecessors(String executorKey) {
		List<FlowExecutionConnector> connectors=this.executionModel.getConnectors();
		boolean result=true;
		boolean found=false;
		for (int i=0;i<connectors.size() && !found;i++) {
			found=connectors.get(i).getTargetExecutionId().equals(executorKey);
		}
		result=!found;
		return result;
	}

	
	/**
	 * Main run exectuion method
	 * Instantiates and runs the first actors
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public void run() {
		try {
			this.environment.setStatus(FluxCompleteEnvironment.RUNNING_STATUS);
			this.executionModel=
					FlowExecutorModelManager.generateExecutionModel(
							new FlowExecutionModel(),"",this.activity.getElements(),this.activity.getConnectors(),this.environment);
			log.info("ExecutionModel:"+this.executionModel);
			runExecutors(this.executionModel);
		} catch (Exception e) {
			this.environment.showMessage(
					"0", 
					"FluxRunner.run(): Finish run with errors:"+
							this.activity==null?
									"null":
									this.activity.getName()+" exception "+e.getMessage()+" "+
									e.getCause()!=null?e.getCause().toString():"");
			if (e.getCause()!=null) {
				this.environment.getOutput().sendMessage("0",e.getCause().toString());
			}
			// this.caller.stop(this.job.getName(),this.activity.getName());
		} finally {
			this.environment.setStatus(FluxCompleteEnvironment.INACTIVE_STATUS);
			
		}
	}

	
	/**
	 * Method used by actors to send messages to each others
	 * in runCore of actors should call setOutput that internally run this method... sending the message to all connected target elements
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public void broadcast(String fluxId, int outputIndex, Object value) throws Exception {
		this.broadcast(fluxId,outputIndex,value,0);
	}

	
	/**
	 * Method used by actors to send messages to each others
	 * in runCore of actors should call setOutput that internally run this method... sending the message to all connected target elements
	 * 
	 * 
	 * @duration
	 * 		time to begin expressed in milliseconds
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public void broadcast(String fluxId, int outputIndex, Object value,int duration) throws Exception {
		List<FlowExecutionConnector> connectors=this.executionModel.getConnectors();
		AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
		if ((outputIndex==-1 && executor.getAsyncInterface()) || (outputIndex>0 && outputIndex<=executor.getOutputInterfaces())) {
			for (FlowExecutionConnector thisConnector:connectors) {
				if (thisConnector.getSourceExecutionId().equals(executor.getFlowId()) && thisConnector.getSourceIndex()==outputIndex) {
					AbstractMainExecutor target=this.executionModel.getExecutors().get(thisConnector.getTargetExecutionId());
					this.send(executor, target, value, duration);
				}
			}
		} 
	}

	
	public void send(String sourceId,String targetId,Object value,int duration) throws Exception {
		AbstractMainExecutor source=null;
		if (sourceId!=null) {
			source=this.executionModel.getExecutors().get(sourceId);
		}
		AbstractMainExecutor target=null;
		if (targetId!=null) {
			target=this.executionModel.getExecutors().get(targetId);
		}
		this.send(source,target,value,duration);
		
	}

	/**
	 * Sends a message to a node
	 * @param source
	 * @param target
	 * @param value
	 * @param duration
	 * @throws Exception
	 */
	public void send(AbstractMainExecutor source,AbstractMainExecutor target,Object value,int duration) throws Exception {
		this.executionProcessManager.run(target);
		target.send(value,source,duration);
	}
	
	
	/**
	 * Stop current activity
	 * This method kill all actors and actorSystem of current activity
	 * 
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	public void stop() {
		try {
			for (Entry<String,AbstractMainExecutor> executorEntry:this.executionModel.getExecutors().entrySet()) {
				AbstractMainExecutor executor=executorEntry.getValue();
				ActorRef currentActor=executor.getActorInstance();
				this.actorSystem.stop(currentActor);
			}
			for (Map.Entry<String, MqttAfluxSubscription> entry:this.mqttSubscriptions.entrySet()) {
				entry.getValue().disconnect();
				this.executionProcessManager.finish(entry.getValue().getFluxId());
			}
			// empty mqtt Subscriptions
			this.mqttSubscriptions=new HashMap<String,MqttAfluxSubscription>();
			for (Map.Entry<String, KafkaAfluxConsumer> entry:this.kafkaConsumers.entrySet()) {
				entry.getValue().disconnect();
				this.executionProcessManager.finish(entry.getValue().getFluxId());
			}
			// empty kafka consumers
			this.kafkaConsumers=new HashMap<String,KafkaAfluxConsumer>();
			this.actorSystem.terminate(); // TODO: Its a future- confirma termination
		} catch (Exception e) {
			this.environment.showMessage("0", "FluxRunner.run(): Finish run with errors:"+this.activity==null?"null":this.activity.getName()+" exception "+e.getMessage());
			if (e.getCause()!=null) {
				this.environment.showMessage("0", "FluxRunner.run(): Finish run with errors:"+" exception "+e.getCause().toString());
			}
		} finally {
			this.environment.setStatus(FluxCompleteEnvironment.INACTIVE_STATUS);
			
		}
	}

	
	/**
	 * Notifies to execution environment every finish of each actor execution instance
	 * Each actor can be run many times and finish many times during an activity execution
	 * 
	 * @author Tanmaya Mahapatra
	 * 
	 */
	@Override
	public void finish(String fluxId) {
		this.executionProcessManager.finish(fluxId);
		if (this.executionProcessManager.hasFinished()) {
				log.debug("FluxRunnerImpl.finish.hasFinished()");
				this.caller.stop(this.job.getName(),this.activity.getId());
		}
		
	}

	@Override
	public void addMqttSubscription(
				String fluxId,
				String host, 
				String clientId, 
				MqttConnectOptions options, 
				String topic,
				int times,
				int timeLimit,
				int reconnectionInterval) throws MqttException {
		if (!mqttSubscriptions.containsKey(fluxId)) {
			AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
			MqttAfluxSubscription subscription=new MqttAfluxSubscription(executor,this, host,clientId,options,topic,times,timeLimit,reconnectionInterval);
			mqttSubscriptions.put(fluxId, subscription);
			this.executionProcessManager.addMqttSubscription();
		}
	}
	
	
	@Override
	public void addMqttPublisher(String fluxId,String host, String clientId,MqttConnectOptions options,String topic,int times,int timeLimit) throws MqttException {
		if (!mqttPublishers.containsKey(fluxId)) {
			AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
			MqttAfluxPublisher publisher=new MqttAfluxPublisher(executor,this, host,clientId,options,topic,times,timeLimit);
			mqttPublishers.put(fluxId, publisher);
		}
	}

	@Override
	public void removeMqttPublisher(String fluxId) throws MqttException {
		if (mqttPublishers.containsKey(fluxId)) {
			MqttAfluxPublisher publisher=mqttPublishers.get(fluxId);
			publisher.disconnect();
			mqttPublishers.remove(fluxId);
		}
	}


	@Override
	public void removeMqttSubscription(String fluxId) throws MqttException {
		if (mqttSubscriptions.containsKey(fluxId)) {
			MqttAfluxSubscription subscription=mqttSubscriptions.get(fluxId);
			subscription.disconnect();
			mqttSubscriptions.remove(fluxId);
			this.executionProcessManager.finish(fluxId);
			this.executionProcessManager.removeMqttSubscription();
		}
	}

	@Override
	public void mqttPublish(String fluxId, String message) throws MqttException {
		// TODO Auto-generated method stub
		MqttAfluxPublisher publisher=this.mqttPublishers.get(fluxId);
		publisher.publish(message);
	}


	public void mqttReconnectSubscription(String fluxId) throws Exception {
		boolean finished=true;
		System.out.println("mqtt:runner:mqttReconnectSubscription");
		if (mqttSubscriptions.containsKey(fluxId)) {
			MqttAfluxSubscription subscription=mqttSubscriptions.get(fluxId);
			finished=subscription.isFinished();
			if (!finished) {
				subscription.reconnect();
				this.send(fluxId, fluxId, new MqttReconnectSignal(), subscription.getReconnectionInterval()*1000);
			} else {
				subscription.disconnect();
				mqttSubscriptions.remove(fluxId);
			}
		}
	}


	// kafka
	

	@Override
	public boolean isKafkaConsumerRegistered(String fluxId) {
		return this.kafkaConsumers.containsKey(fluxId);
	}
	
	
	/**
	 * Adds a kafka consumer if it does not already exist
	 */
	@Override
	public void addKafkaConsumer(
				String fluxId,
				KafkaConsumer<String,String> consumer,
				String topic,
				int reconnectionInterval,
				String kafkaAfluxDataClass) throws KafkaException {
		if (!this.kafkaConsumers.containsKey(fluxId)) {
			AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
			String bootstrapHost="localhost:9092";
			KafkaAfluxConsumer subscription=new KafkaAfluxConsumer(executor,this, consumer,topic,reconnectionInterval,kafkaAfluxDataClass);
			kafkaConsumers.put(fluxId, subscription);
			this.executionProcessManager.addKafkaConsumer();
		}
	}
	
	
	@Override
	public void addKafkaProducer(String fluxId,String host, String clientId,ProducerConfig options,String topic,int times,int timeLimit) throws KafkaException {
		if (!this.kafkaProducers.containsKey(fluxId)) {
			AbstractMainExecutor executor=this.executionModel.getExecutors().get(fluxId);
			KafkaAfluxProducer publisher=new KafkaAfluxProducer(executor,this, host,clientId,options,topic,times,timeLimit);
			kafkaProducers.put(fluxId, publisher);
		}
	}

	@Override
	public void removeKafkaProducer(String fluxId) throws KafkaException {
		if (kafkaProducers.containsKey(fluxId)) {
			KafkaAfluxProducer publisher=kafkaProducers.get(fluxId);
			publisher.disconnect();
			kafkaProducers.remove(fluxId);
		}
	}


	@Override
	public void removeKafkaConsumer(String fluxId) throws KafkaException {
		if (kafkaConsumers.containsKey(fluxId)) {
			KafkaAfluxConsumer subscription=kafkaConsumers.get(fluxId);
			subscription.disconnect();
			kafkaConsumers.remove(fluxId);
			this.executionProcessManager.finish(fluxId);
			this.executionProcessManager.removeKafkaConsumer();
		}
	}

	@Override
	public void kafkaProduce(String fluxId, String message) throws KafkaException {
		// TODO Auto-generated method stub
		KafkaAfluxProducer publisher=this.kafkaProducers.get(fluxId);
		publisher.produce(message);
	}


	public void kafkaReconnectConsumer(String fluxId) throws Exception {
		boolean finished=true;
		System.out.println("kafka:runner:kafkaReconnectConsumer");
		if (this.kafkaConsumers.containsKey(fluxId)) {
			KafkaAfluxConsumer subscription=kafkaConsumers.get(fluxId);
			finished=subscription.isFinished();
			if (!finished) {
				subscription.reconnect();
				this.send(fluxId, fluxId, new KafkaAfluxReconnectSignal(), subscription.getReconnectionInterval());
			} else {
				subscription.disconnect();
				this.kafkaConsumers.remove(fluxId);
			}
		}
	}

	@Override
	public void kafkaConsume(String fluxId,long pollTimeLimit) throws Exception {
		KafkaAfluxConsumer consumer=this.kafkaConsumers.get(fluxId);
		consumer.consume(pollTimeLimit);
	}

	@Override
	public void setVariable(String fluxId, String scope, String key, Object value) {
		this.environment.setVariable(this.job.getId(),this.activity.getId(),fluxId,scope,key,value);
	}

	@Override
	public Object getVariable(String fluxId, String scope, String key) {
		return this.environment.getVariable(this.job.getId(),this.activity.getId(),fluxId,scope,key);
	}

	@Override
	public void removeVariable(String fluxId, String scope, String key) {
		this.environment.removeVariable(this.job.getId(),this.activity.getId(),fluxId,scope,key);
	}

	@Override
	public String getProposedJobFolder() {
		String jobName=this.job.getName();
		String result =AFluxUtils.getProposedJobFolderName(jobName); 
		return result;
	}

	@Override
	public void appendToFile(String mainFolder, String jobFolder, String fileName, String value) throws IOException {
		String baseJobDir=FileSystemUtil.getJobsBaseSubDir(mainFolder, jobFolder);
		String completeFileName=baseJobDir+"/"+fileName;
		System.out.println("Filename:"+completeFileName);
		File f=new File(completeFileName);
		if (!f.exists()) {
			f.createNewFile();
		}
		FileWriter fileWriter=new FileWriter(f,true);
		BufferedWriter bw=new BufferedWriter(fileWriter);
		bw.write(value);
		bw.close();
		fileWriter.close();
	} 

	@Override
	public void generateChart(String content) throws FileNotFoundException{
		String baseDir=FileSystemUtil.getJobsBaseDir("charts");
		String fullFileName=baseDir+"/"+this.activity.getId().toString()+".html";
		
		PrintWriter out = new PrintWriter(fullFileName);
		out.println(content);
		out.close();
	};
	
	@Override
	public void generateChart(String mainFolder,String jobFolder,String fileName,String content) throws FileNotFoundException {
		String baseJobDir=FileSystemUtil.getJobsBaseSubDir(mainFolder, jobFolder);
		String completeFileName=baseJobDir+"/"+fileName;
		System.out.println("Chart Filename:"+completeFileName);
		PrintWriter out = new PrintWriter(completeFileName);
		out.println(content);
		out.close();
		// in order to have last graph based on current activity
		generateChart(content);
		
	}
	
	public String[] readTextFile(String mainFolder, String jobFolder, String fileName) throws IOException {
		String baseJobDir=FileSystemUtil.getJobsBaseSubDir(mainFolder, jobFolder);
		String fullFileName=baseJobDir+"/"+fileName;
		String contents = new String(Files.readAllBytes(Paths.get(fullFileName)));
		String[] result=contents.split("\n");
		return result;
	};
	

}
 