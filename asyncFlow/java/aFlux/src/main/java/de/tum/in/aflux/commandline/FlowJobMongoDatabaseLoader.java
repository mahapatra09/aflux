

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

package de.tum.in.aflux.commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.tum.in.aflux.dao.FlowElementTypeRepository;
import de.tum.in.aflux.dao.FlowSettingRepository;
import de.tum.in.aflux.dao.FlowJobMongoRepository;
import de.tum.in.aflux.model.FlowActivity;
import de.tum.in.aflux.model.FlowConnector;
import de.tum.in.aflux.model.FlowElement;
import de.tum.in.aflux.model.FlowElementProperty;
import de.tum.in.aflux.model.FlowElementType;
import de.tum.in.aflux.model.FlowElementTypeProperty;
import de.tum.in.aflux.model.FlowJob;
import de.tum.in.aflux.model.FlowSetting;
import de.tum.in.aflux.model.NodeElementSetting;
import de.tum.in.aflux.model.SubFlow;


/**
 * Class used initially to generate new sample flows on startup . Now is to be deprecated
 * @author Tanmaya Mahapatra
 *
 */
@Component
@PropertySource("classpath:/application.properties")
public class FlowJobMongoDatabaseLoader // removed implements CommandLineRunner,Ordered 
			{
	
	final private static String CA_EXECUTE_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsExecute";
	final private static String CA_JOIN_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsJoin";
	final private static String CA_LOAD_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsLoad";
	final private static String CA_SHOW_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsShow";
	final private static String CA_SUMMARIZE_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsSummarize";
	final private static String CA_SELECT_TOOL_CLASS="de.tum.in.aflux.component.commonanalytics.CommonAnalyticsSelect";
	final private static String SHOW_STRING_VALUE_TOOL_CLASS="de.tum.in.aflux.component.strings.ShowStringValue";
	final private static String PIG_LOAD_TOOL_CLASS="de.tum.in.aflux.component.pig.PigLoad";
	final private static String PIG_GROUP_TOOL_CLASS="de.tum.in.aflux.component.pig.PigGroup";
	final private static String PIG_INNER_JOIN_TOOL_CLASS="de.tum.in.aflux.component.pig.PigInnerJoin";
	final private static String PIG_EXECUTE_TOOL_CLASS="de.tum.in.aflux.component.pig.PigExecute";
	final private static String PIG_FOR_EACH_TOOL_CLASS="de.tum.in.aflux.component.pig.PigForEach";
	final private static String PIG_INNER_JOIN_ADD_ALIAS_TOOL_CLASS="de.tum.in.aflux.component.pig.PigInnerJoinAddAlias";
	final private static String PIG_STORE_TOOL_CLASS="de.tum.in.aflux.component.pig.PigStore";
	final private static String PIG_FILTER_TOOL_CLASS="de.tum.in.aflux.component.pig.PigFilter";
	final private static String HIVE_CREATE_TOOL_CLASS="de.tum.in.aflux.component.hive.HiveCreate";
	final private static String HIVE_INNER_JOIN_TOOL_CLASS="de.tum.in.aflux.component.hive.HiveInnerJoin";
	final private static String HIVE_EXECUTE_TOOL_CLASS="de.tum.in.aflux.component.hive.HiveExecute";
	final private static String HIVE_SELECT_TOOL_CLASS="de.tum.in.aflux.component.hive.HiveSelect";
	final private static String RANDOM_TOOL_CLASS = "de.tum.in.aflux.component.strings.RandomStringGeneratorTool";
	final private static String OUTPUT_TOOL_CLASS = "de.tum.in.aflux.component.strings.OutputTool";
	final private static String READ_MONGODB_TOOL_CLASS = "de.tum.in.aflux.component.mongodb.ReadMongoDB";
	final private static String SEARCH_TWITTER_TOOL_CLASS = "de.tum.in.aflux.component.twitter.SearchTwitter";
	final private static String WAIT_TOOL_CLASS = "de.tum.in.aflux.component.timing.WaitTool";
	final private static String ASYNC_WAIT_TOOL_CLASS = "de.tum.in.aflux.component.timing.AsyncWaitTool";
	final private static String START_TOOL_CLASS = "de.tum.in.aflux.component.timing.StartTool";
	final private static String WRITE_MONGODB_TOOL_CLASS = "de.tum.in.aflux.component.mongodb.WriteMongoDB";
	final private static String CONCATENATE_STRINGS_TOOL_CLASS = "de.tum.in.aflux.component.strings.ConcatenateStrings";
	final private static String SUBFLOW_TOOL_CLASS="de.tum.in.aflux.component.subflow.SubFlowTool";
	
	
	final private static String RUN_N_TIMES_CLASS="de.tum.in.aflux.component.control.RunNTimesTool";
	final private static String KAFKA_CONSUMER_CLASS="de.tum.in.aflux.flux_engine.kafka.KafkaAfluxConsumer";
	final private static String KAFKA_PRODUCER_CLASS="de.tum.in.aflux.flux_engine.kafka.KafkaAfluxProducer";
	final private static String TRAFFICA9_RTX_CLASS="de.tum.in.aflux.component.trafficA9.TrafficA9RTX";
	final private static String TRAFFICA9_EXECUTION_CLASS="de.tum.in.aflux.component.trafficA9.TrafficA9Execution";
	final private static String TRAFFICA9_REPORT_CLASS="de.tum.in.aflux.component.trafficA9.TrafficA9Report";
	
	@Autowired
	Environment env;
	
	private final FlowJobMongoRepository repository;	
	private final FlowElementTypeRepository flowElementTypeRepository;
	private final FlowSettingRepository settingsRepository;

	FlowElementType concatenateTool;
	FlowElementType outputTool;
	FlowElementType randomTool;
	FlowElementType showValueTool;
    
	FlowElementType readMongoDBTool;
	FlowElementType searchTwitterTool;

	FlowElementType waitTool;
	FlowElementType asyncWaitTool;
	
	FlowElementType startTool;
	FlowElementType writeMongoDBTool;

	FlowElementType pigLoadTool;
	FlowElementType pigInnerJoinTool;
	FlowElementType pigExecuteTool;
	FlowElementType pigForEachTool;
	FlowElementType pigInnerJoinAddAliasTool;
	FlowElementType pigStoreTool;
	FlowElementType pigFilterTool;
	FlowElementType showStringTool;
	FlowElementType pigGroupTool;

	FlowElementType hiveCreateTool;
	FlowElementType hiveInnerJoinTool;
	FlowElementType hiveExecuteTool;
	FlowElementType hiveSelectTool;

	FlowElementType CAExecuteTool;
	FlowElementType CAJoinTool;
	FlowElementType CALoadTool;
	FlowElementType CAShowTool;
	FlowElementType CASummarizeTool;
	FlowElementType CASelectTool;
	FlowElementType showStringValueTool;
	
	FlowElementType subflowTool;
	
	
	FlowElementType runNTimesTool;
	FlowElementType kafkaAfluxConsumerTool;
	FlowElementType kafkaAfluxProducerTool;
	FlowElementType trafficA9RTXTool;
	FlowElementType trafficA9ExecutionTool;
	FlowElementType trafficA9ReportTool;

	
	@Autowired
	public FlowJobMongoDatabaseLoader(FlowJobMongoRepository repository,
				FlowElementTypeRepository flowElementTypeRepository,
				FlowSettingRepository settingsRepository) {
		this.repository=repository;
		this.flowElementTypeRepository=flowElementTypeRepository;
		this.settingsRepository=settingsRepository;
		
		// load tools
		concatenateTool = this.flowElementTypeRepository.findByClassName(CONCATENATE_STRINGS_TOOL_CLASS);
		outputTool = this.flowElementTypeRepository.findByClassName( OUTPUT_TOOL_CLASS);
		randomTool = this.flowElementTypeRepository.findByClassName( RANDOM_TOOL_CLASS);
		showValueTool = this.flowElementTypeRepository.findByClassName( SHOW_STRING_VALUE_TOOL_CLASS);
		
		
		readMongoDBTool = this.flowElementTypeRepository.findByClassName( READ_MONGODB_TOOL_CLASS);
		searchTwitterTool = this.flowElementTypeRepository.findByClassName( SEARCH_TWITTER_TOOL_CLASS);
		
		waitTool = this.flowElementTypeRepository.findByClassName( WAIT_TOOL_CLASS);
		asyncWaitTool = this.flowElementTypeRepository.findByClassName(ASYNC_WAIT_TOOL_CLASS);
		
		
		startTool = this.flowElementTypeRepository.findByClassName(START_TOOL_CLASS);
		writeMongoDBTool = this.flowElementTypeRepository.findByClassName(WRITE_MONGODB_TOOL_CLASS);

		pigLoadTool = this.flowElementTypeRepository.findByClassName(PIG_LOAD_TOOL_CLASS);
		pigInnerJoinTool = this.flowElementTypeRepository.findByClassName( PIG_INNER_JOIN_TOOL_CLASS);
		pigExecuteTool = this.flowElementTypeRepository.findByClassName( PIG_EXECUTE_TOOL_CLASS);
		pigForEachTool = this.flowElementTypeRepository.findByClassName( PIG_FOR_EACH_TOOL_CLASS);
		pigInnerJoinAddAliasTool = this.flowElementTypeRepository.findByClassName( PIG_INNER_JOIN_ADD_ALIAS_TOOL_CLASS);
		pigStoreTool = this.flowElementTypeRepository.findByClassName( PIG_STORE_TOOL_CLASS);
		pigFilterTool = this.flowElementTypeRepository.findByClassName( PIG_FILTER_TOOL_CLASS);
		showStringTool = this.flowElementTypeRepository.findByClassName( SHOW_STRING_VALUE_TOOL_CLASS);
		pigGroupTool = this.flowElementTypeRepository.findByClassName( PIG_GROUP_TOOL_CLASS);
	

		hiveCreateTool = this.flowElementTypeRepository.findByClassName(HIVE_CREATE_TOOL_CLASS);
		hiveInnerJoinTool = this.flowElementTypeRepository.findByClassName( HIVE_INNER_JOIN_TOOL_CLASS);
		hiveExecuteTool = this.flowElementTypeRepository.findByClassName( HIVE_EXECUTE_TOOL_CLASS);
		hiveSelectTool = this.flowElementTypeRepository.findByClassName( HIVE_SELECT_TOOL_CLASS);
		

		
		CAExecuteTool = this.flowElementTypeRepository.findByClassName(CA_EXECUTE_TOOL_CLASS);
		CAJoinTool = this.flowElementTypeRepository.findByClassName( CA_JOIN_TOOL_CLASS);
		CALoadTool = this.flowElementTypeRepository.findByClassName( CA_LOAD_TOOL_CLASS);
		CAShowTool = this.flowElementTypeRepository.findByClassName( CA_SHOW_TOOL_CLASS);
		CASummarizeTool = this.flowElementTypeRepository.findByClassName( CA_SUMMARIZE_TOOL_CLASS);
		CASelectTool = this.flowElementTypeRepository.findByClassName( CA_SELECT_TOOL_CLASS);
		showStringValueTool = this.flowElementTypeRepository.findByClassName( SHOW_STRING_VALUE_TOOL_CLASS);
		
		subflowTool = this.flowElementTypeRepository.findByClassName( SUBFLOW_TOOL_CLASS);
		

	
		runNTimesTool = this.flowElementTypeRepository.findByClassName(RUN_N_TIMES_CLASS);
		kafkaAfluxConsumerTool = this.flowElementTypeRepository.findByClassName(KAFKA_CONSUMER_CLASS);
		kafkaAfluxProducerTool = this.flowElementTypeRepository.findByClassName(KAFKA_PRODUCER_CLASS);
		trafficA9RTXTool = this.flowElementTypeRepository.findByClassName(TRAFFICA9_RTX_CLASS);
		trafficA9ExecutionTool = this.flowElementTypeRepository.findByClassName(TRAFFICA9_EXECUTION_CLASS);
		trafficA9ReportTool = this.flowElementTypeRepository.findByClassName(TRAFFICA9_REPORT_CLASS);
	
	
	}
	
	
	// removed @Override
	public int getOrder() {
		return 30;
	}
	
	
	private FlowActivity prepareCase1FirstWorkingSample() {

		FlowActivity activity=new FlowActivity(2L,2,"ConcatRandomSample");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (concatenateTool!=null && outputTool!=null && randomTool!=null && showValueTool!=null) {
			
			flowElements=addGeneralElement(
					randomTool.getName(),
					flowElements,1L,randomTool,20,40,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(
					randomTool.getName(),flowElements,2L,randomTool,23,95,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(
					concatenateTool.getName(),flowElements,3L,concatenateTool,250,120,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(
					showValueTool.getName(),flowElements,4L,showValueTool,520,74,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(outputTool.getName(),
					flowElements,5L,outputTool,340,74,nodeElementSetting,1,
					new FlowElementProperty[]{
							new FlowElementProperty(
									FlowElementTypeProperty.toToolProperty(showValueTool.getProperties()[0]),"Hello World")
					}
			,null);


			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 3L,flowElements.get(2),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			
			
			
		}
		activity.setElements(flowElements);
		activity.setConnectors(flowConnectors);
		
		return activity;
	}
	
	
	private static final String MONGO_DB_SAMPLE_DATABASE="dbsample2";
	private static final String MONGO_DB_SAMPLE_COLLECTION="wordsCollection";
	


	
	private static final String TWITTER_CONSUMER_KEY="xxxxxxxxxxxx";
	private static final String TWITTER_CONSUMER_SECRET="xxxxxxxxxx";
	private static final String TWITTER_TOKEN_KEY="zzzzzzzzzzzz";
	private static final String TWITTER_TOKEN_SECRET="zzzzzzzzzzzz";
	
	
	private FlowActivity prepareCase2MongoTweetSample() {

		FlowActivity activity=new FlowActivity(3L,3,"MongoTweetSample");

		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();
		

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (outputTool!=null && showValueTool!=null && readMongoDBTool!=null && searchTwitterTool!=null) {
			
			flowElements=addGeneralElement(readMongoDBTool.getName(),flowElements,1L,readMongoDBTool,22,40,nodeElementSetting,1,
					new FlowElementProperty[]{
							new FlowElementProperty(FlowElementProperty.toToolProperty(readMongoDBTool.getProperties()[0]),env.getProperty("mongodb.host")),
							new FlowElementProperty(FlowElementProperty.toToolProperty(readMongoDBTool.getProperties()[1]),env.getProperty("mongodb.port")),
							new FlowElementProperty(FlowElementProperty.toToolProperty(readMongoDBTool.getProperties()[2]),MONGO_DB_SAMPLE_DATABASE),
							new FlowElementProperty(FlowElementProperty.toToolProperty(readMongoDBTool.getProperties()[3]),MONGO_DB_SAMPLE_COLLECTION)},null);
			
			
			flowElements=addGeneralElement(searchTwitterTool.getName(),flowElements,2L,searchTwitterTool,250,110,nodeElementSetting,1,
					new FlowElementProperty[]{
							new FlowElementProperty(FlowElementProperty.toToolProperty(searchTwitterTool.getProperties()[0]),TWITTER_CONSUMER_KEY),
							new FlowElementProperty(FlowElementProperty.toToolProperty(searchTwitterTool.getProperties()[1]),TWITTER_CONSUMER_SECRET),
							new FlowElementProperty(FlowElementProperty.toToolProperty(searchTwitterTool.getProperties()[2]),TWITTER_TOKEN_KEY),
							new FlowElementProperty(FlowElementProperty.toToolProperty(searchTwitterTool.getProperties()[3]),TWITTER_TOKEN_SECRET)},null);
			flowElements=addGeneralElement(showValueTool.getName(),flowElements,3L,showValueTool,435,50,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(outputTool.getName(),flowElements,4L,outputTool,21,190,nodeElementSetting,1,
					new FlowElementProperty[]{
							new FlowElementProperty(FlowElementProperty.toToolProperty(outputTool.getProperties()[0]),"Java Development")},null);
			flowElements=addGeneralElement(outputTool.getName(),flowElements,5L,outputTool,29,97,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(outputTool.getProperties()[0]),"Akka Actors")},null);
			
			
			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			FlowConnector flowConnector2=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector2);
			FlowConnector flowConnector3=new FlowConnector( 3L,flowElements.get(3),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector3);
			FlowConnector flowConnector4=new FlowConnector( 4L,flowElements.get(4),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector4);
			
		}
		
		
		
		activity.setElements(flowElements);

		activity.setConnectors(flowConnectors);
		
		return activity;
	}
	
	private FlowJob prepareSyncFlow1Sample() {
		
		FlowJob result=new FlowJob("SyncFlow1Sample-2");

		FlowActivity activity=new FlowActivity(3L,3,"WaitSample");
		
		
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();
		

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (waitTool!=null && asyncWaitTool!=null && outputTool!=null) {

			
			flowElements=addGeneralElement(waitTool.getName(),flowElements,1L,waitTool,30,140,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"3000")},null);
			flowElements=addGeneralElement(waitTool.getName(),flowElements,2L,waitTool,250,162,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"3000")},null);
			flowElements=addGeneralElement(waitTool.getName(),flowElements,3L,waitTool,450,142,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"3000")},null);
			flowElements=addGeneralElement(waitTool.getName(),flowElements,4L,waitTool,38,290,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"4000")},null);
			flowElements=addGeneralElement(waitTool.getName(),flowElements,5L,waitTool,270,320,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"4000")},null);
			flowElements=addGeneralElement(waitTool.getName(),flowElements,6L,waitTool,525,270,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(waitTool.getProperties()[0]),"4000")},null);
			flowElements=addGeneralElement(asyncWaitTool.getName(),flowElements,7L,asyncWaitTool,52,42,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(asyncWaitTool.getProperties()[0]),"4500")},null);
			flowElements=addGeneralElement(outputTool.getName(),flowElements,8L,outputTool,340,68,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(outputTool.getProperties()[0]),"Hello World")},null);
			
			
			
			// Add Connectors
			
			
			
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			FlowConnector flowConnector2=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector2);
			FlowConnector flowConnector3=new FlowConnector( 3L,flowElements.get(3),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector3);
			FlowConnector flowConnector4=new FlowConnector( 4L,flowElements.get(4),1,flowElements.get(5),1);
			flowConnectors.add(flowConnector4);
			FlowConnector flowConnector5=new FlowConnector( 5L,flowElements.get(6),1,flowElements.get(0),1);
			flowConnectors.add(flowConnector5);
			FlowConnector flowConnector6=new FlowConnector( 6L,flowElements.get(6),flowElements.get(7));
			flowConnectors.add(flowConnector6);
		}

		activity.setElements(flowElements);
		activity.setConnectors(flowConnectors);
		List<FlowActivity> activities=new ArrayList<FlowActivity>();
		activities.add(activity);
		result.setActivities(activities);
 		return result;
	}

	private FlowActivity prepareCase3Schedule() {

		FlowActivity activity=new FlowActivity(6L,6,"SchedulerSample");
		
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();

		
		if ( startTool!=null && outputTool!=null && writeMongoDBTool!=null && randomTool!=null ) {
			
			flowElements=addGeneralElement(startTool.getName(),flowElements,1L,startTool,10,40,nodeElementSetting,1,new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(outputTool.getName(),flowElements,2L,outputTool,340,50,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(outputTool.getProperties()[0]),"Launched by scheduler")},null);
			flowElements=addGeneralElement(writeMongoDBTool.getName(),flowElements,3L,writeMongoDBTool,550,160,nodeElementSetting,1,
					new FlowElementProperty[]{new FlowElementProperty(FlowElementProperty.toToolProperty(writeMongoDBTool.getProperties()[0]),env.getProperty("mongodb.host")),
					new FlowElementProperty(FlowElementProperty.toToolProperty(writeMongoDBTool.getProperties()[1]),env.getProperty("mongodb.port")),
					new FlowElementProperty(FlowElementProperty.toToolProperty(writeMongoDBTool.getProperties()[2]),MONGO_DB_SAMPLE_DATABASE),
					new FlowElementProperty(FlowElementProperty.toToolProperty(writeMongoDBTool.getProperties()[3]),MONGO_DB_SAMPLE_COLLECTION)},null);
			flowElements=addGeneralElement(randomTool.getName(),flowElements,4L,randomTool,340,108,nodeElementSetting,1,
					new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(outputTool.getName(),flowElements,5L,outputTool,780,240,nodeElementSetting,1,
					new FlowElementProperty[]{
							new FlowElementProperty(FlowElementProperty.toToolProperty(outputTool.getProperties()[0]),"Launched by WriteMongodb")},null);
			FlowConnector flowConnector2=new FlowConnector( 2L,flowElements.get(0),flowElements.get(3));
			flowConnectors.add(flowConnector2);
			FlowConnector flowConnector3=new FlowConnector( 3L,flowElements.get(3),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector3);
			FlowConnector flowConnector4=new FlowConnector( 4L,flowElements.get(2),flowElements.get(4));
			flowConnectors.add(flowConnector4);
		}
		
		
		activity.setElements(flowElements);

		// Add Connectors
		
		
		

		activity.setConnectors(flowConnectors);

 		
		return activity;
	}	
	
	
	
	
	private void generateMongoDBData() {
		MongoClient mongoClient = new MongoClient(env.getProperty("mongodb.host") , Integer.valueOf(env.getProperty("mongodb.port")) );
		MongoDatabase database = mongoClient.getDatabase(MONGO_DB_SAMPLE_DATABASE);
		MongoCollection<Document> collection=database.getCollection(MONGO_DB_SAMPLE_COLLECTION);
		
		// remove previous documents
		
		collection.drop();
		/*
		
		DeleteResult deleteResult =collection.deleteMany(baseDocument);
		System.out.println("Deleneted on samble mongo db:"+deleteResult.getDeletedCount());
		
		*/
		// insert new documents
		List<Document> documentList=new ArrayList<Document>();
		for (int i=0;i<50;i++) {
			Document document= new Document("name","Invoice")
					.append("_id",i)
					.append("phone", Arrays.asList("4321-1234","1231-9999",String.valueOf(i+4)))
					.append("customer", new Document("name","Henry"+String.valueOf(i)).
											append("address","nowhere all 22222"));
			documentList.add(document);
		}
		collection.insertMany(documentList);
		
	}
	
	private boolean checkDatabaseInitialization() {
		List<FlowSetting> settings=this.settingsRepository.findAll();
		boolean initializeDatabase=false;
		if (settings!=null && settings.size()>0) {
			initializeDatabase=settings.get(0).isInitalizeDatabase();
		}
		return initializeDatabase;
	}
	
	private void setInitizationSettings(boolean initialize) {
		List<FlowSetting> settings=this.settingsRepository.findAll();
		FlowSetting setting=settings.get(0);
		if (setting.isInitalizeDatabase()!=initialize) {
			setting.setInitalizeDatabase(initialize);
			this.settingsRepository.save(setting);
		}
	}


	
	private FlowActivity preparePigSampleActivity() {
		FlowActivity activity=new FlowActivity(2L,2,"PigSample");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (pigLoadTool!=null && 
				pigInnerJoinTool!=null && 
				pigExecuteTool!=null && 
				pigForEachTool!=null && 
				pigInnerJoinAddAliasTool!=null && 
				pigStoreTool!=null && 
				pigFilterTool!=null && 
				showStringTool!=null) {
			
			flowElements=addGeneralElement(pigLoadTool.getName(),flowElements,1L,pigLoadTool,34,21,nodeElementSetting,1,
						new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[4]),"/user/root/pig_data/movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[5]),"PigStorage(',')"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[6]),"movieId:int,title:chararray,genres:chararray"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[7]),"MOVIES"),
			},null);
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,2L,pigFilterTool,142,98,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"title<'D'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"MOVIES_D"),
			},null);
			flowElements=addGeneralElement(pigGroupTool.getName(),flowElements,3L,pigGroupTool,224,182,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[6]),"false"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_GROUP_D"),
					
			},null);
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,4L,pigFilterTool,362,90,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"title>'T'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"MOVIES_T"),
			},null);
			flowElements=addGeneralElement(pigGroupTool.getName(),flowElements,5L,pigGroupTool,496,180,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[6]),"false"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_GROUP_T"),
			},null);
			flowElements=addGeneralElement("Pig Execute",flowElements,7L,pigExecuteTool,835,271,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(showStringTool.getName(),flowElements,8L,showStringTool,1015,165,nodeElementSetting,1,
					new FlowElementProperty[]{},null);
			flowElements=addGeneralElement(pigForEachTool.getName(),flowElements,9L,pigForEachTool,342,249,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[6]),"group AS genres,COUNT(MOVIES_D) AS COUNT_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[7]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[8]),"GROUP_D"),
			},null);
			flowElements=addGeneralElement(pigForEachTool.getName(),flowElements,10L,pigForEachTool,625,248,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[6]),"group AS genres,COUNT(MOVIES_T) AS COUNT_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[7]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[8]),"GROUP_T"),
			},null);
			flowElements=addGeneralElement(
					pigInnerJoinAddAliasTool.getName(),flowElements,11L,pigInnerJoinAddAliasTool,780,37,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinAddAliasTool.getProperties()[4]),"GROUP_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinAddAliasTool.getProperties()[5]),"genres"),
			},null);
			flowElements=addGeneralElement(pigStoreTool.getName(),flowElements,12L,pigStoreTool,804,175,nodeElementSetting,1,new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[4]),"TMP_FINAL_MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[5]),"/user/root/pig_data/final_movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[7]),"false"),
			},null);
			flowElements=addGeneralElement(pigInnerJoinTool.getName(),flowElements,13L,pigInnerJoinTool,573,48,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[4]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_FINAL_MOVIES"),
			},null);
			
			
			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 4L,flowElements.get(3),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 7L,flowElements.get(5),1,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 8L,flowElements.get(5),4,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 9L,flowElements.get(2),1,flowElements.get(7),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 10L,flowElements.get(7),1,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 11L,flowElements.get(4),1,flowElements.get(8),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 15L,flowElements.get(11),1,flowElements.get(9),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 16L,flowElements.get(8),1,flowElements.get(11),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 17L,flowElements.get(9),1,flowElements.get(10),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 18L,flowElements.get(10),1,flowElements.get(5),1);
			flowConnectors.add(flowConnector);
			
			
			activity.setElements(flowElements);
			activity.setConnectors(flowConnectors);
			
			
			
		}

		return activity;
	}


	
	private FlowActivity preparePigLinksActivity() {
		FlowActivity activity=new FlowActivity(3L,3,"PigLinksActivity");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		
		
		
		if (pigExecuteTool!=null && 
				pigStoreTool!=null && 
				pigFilterTool!=null && 
				showStringTool!=null && 
				pigLoadTool!=null) {
			
			flowElements=addGeneralElement(pigLoadTool.getName(),flowElements,1L,pigLoadTool,74,218,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[4]),"/user/root/pig_data/links.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[5]),"PigStorage(',')"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[6]),"movieId:int,imdbId:int,tmdbId:int"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[7]),"LINKS"),
			},null);
			
			
			
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,2L,pigFilterTool,260,296,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),"LINKS"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"imdbId>5000"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"LINKS_5000"),
			},null);
			
			flowElements=addGeneralElement(pigStoreTool.getName(),flowElements,3L,pigStoreTool,458,384,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[4]),"LINKS_5000"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[5]),"/user/root/linksFinal8"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[7]),"false"),
					
			},null);
			flowElements=addGeneralElement(showStringTool.getName(),flowElements,4L,showStringTool,903,472,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement("Pig Execute",flowElements,5L,pigExecuteTool,649,332,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);

			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 3L,flowElements.get(4),1,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 4L,flowElements.get(4),4,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 5L,flowElements.get(2),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			activity.setElements(flowElements);
			activity.setConnectors(flowConnectors);

		}

		return activity;
		
	}
	

	
	
	private FlowActivity preparePigMoviesActivity() {
		FlowActivity activity=new FlowActivity(2L,2,"Pig Movies");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		
		
		
		if (pigExecuteTool!=null && 
				pigStoreTool!=null && 
				pigFilterTool!=null && 
				showStringTool!=null && 
				pigLoadTool!=null) {
			
			
			// TODO: Continuing here
			
			flowElements=addGeneralElement(pigLoadTool.getName(),flowElements,1L,pigLoadTool,112,162,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[4]),"/user/root/pig_data/movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[5]),"PigStorage(',')"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[6]),"movieId:int,title:chararray,genres:chararray"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[7]),"MOVIES"),
			},null);
			

			
			
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,2L,pigFilterTool,272,208,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"title>'G'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"MOVIES_G"),
			},null);
			
			flowElements=addGeneralElement(pigStoreTool.getName(),flowElements,3L,pigStoreTool,418,276,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[4]),"MOVIES_G"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[5]),"/user/root/moviesFinal8"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[7]),"false"),

			},null);
			flowElements=addGeneralElement(showStringTool.getName(),flowElements,4L,showStringTool,882,340,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(pigExecuteTool.getName(),flowElements,5L,pigExecuteTool,525,430,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);

			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 3L,flowElements.get(2),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 4L,flowElements.get(4),1,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 5L,flowElements.get(4),4,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			activity.setElements(flowElements);
			activity.setConnectors(flowConnectors);

		}

		return activity;
		
	}
	
	
	private FlowActivity prepareParallelSampleActivity(String jobName,FlowActivity pigMoviesActivity,FlowActivity pigLinksActivity) {
		FlowActivity activity=new FlowActivity(1L,1,"parallelSample3");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		if (subflowTool!=null && 
				asyncWaitTool!=null) {
			
			pigMoviesActivity.setParentActivityId(1L);
			pigMoviesActivity.setParentNodeId(1L);
			SubFlow subFlow=new SubFlow(jobName,pigMoviesActivity);
			flowElements=addGeneralElement("Pig Movies",flowElements,1L,subflowTool,332,120,nodeElementSetting,1,
					new FlowElementProperty[]{
			},subFlow);
			pigLinksActivity.setParentActivityId(1L);
			pigLinksActivity.setParentNodeId(2L);
			subFlow=new SubFlow(jobName,pigLinksActivity);
			flowElements=addGeneralElement("Pig Links",flowElements,2L,subflowTool,309,362,nodeElementSetting,1,new FlowElementProperty[]{
			},subFlow);
			flowElements=addGeneralElement(asyncWaitTool.getName(),flowElements,3L,asyncWaitTool,46,300,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(asyncWaitTool.getProperties()[4]),"1000"),
			},null);

			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(2),-1,flowElements.get(0),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(2),-1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			
			activity.setElements(flowElements);
			activity.setConnectors(flowConnectors);

			
			
		}

		return activity;
	}
	
	
	
	private FlowActivity prepareParallelSampleActivityOldNoUsed() {
		FlowActivity activity=new FlowActivity(1L,1,"parallelSample3-old");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		
		
		
		if (subflowTool!=null && 
				pigExecuteTool!=null && 
				pigStoreTool!=null && 
				pigFilterTool!=null && 
				showStringTool!=null) {
			
			
			// TODO: Continuing here
			
			flowElements=addGeneralElement(pigLoadTool.getName(),flowElements,1L,pigLoadTool,34,21,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[4]),"/user/root/pig_data/movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[5]),"PigStorage(',')"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[6]),"movieId:int,title:chararray,genres:chararray"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigLoadTool.getProperties()[7]),"MOVIES"),
			},null);
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,2L,pigFilterTool,142,98,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"title<'D'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"MOVIES_D"),
			},null);
			flowElements=addGeneralElement(pigGroupTool.getName(),flowElements,3L,pigGroupTool,224,182,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[6]),"false"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_GROUP_D"),
					
			},null);
			flowElements=addGeneralElement(pigFilterTool.getName(),flowElements,4L,pigFilterTool,120,28,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[4]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[5]),"title>'T'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigFilterTool.getProperties()[6]),"MOVIES_T"),
			},null);
			flowElements=addGeneralElement(pigGroupTool.getName(),flowElements,5L,pigGroupTool,496,180,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[6]),"false"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_GROUP_T"),
			},null);
			flowElements=addGeneralElement(pigInnerJoinTool.getName(),flowElements,6L,pigInnerJoinTool,138,277,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[4]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_FINAL_MOVIES"),
			},null);
			flowElements=addGeneralElement(pigExecuteTool.getName(),flowElements,7L,pigExecuteTool,835,271,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(showStringTool.getName(),flowElements,8L,showStringTool,1015,165,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(pigForEachTool.getName(),flowElements,9L,pigForEachTool,342,249,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[6]),"group AS genres,COUNT(MOVIES_D) AS COUNT_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[7]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[8]),"GROUP_D"),
			},null);
			flowElements=addGeneralElement(pigForEachTool.getName(),flowElements,10L,pigForEachTool,625,248,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[4]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[6]),"group AS genres,COUNT(MOVIES_T) AS COUNT_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[7]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigForEachTool.getProperties()[8]),"GROUP_T"),
			},null);
			flowElements=addGeneralElement(pigInnerJoinAddAliasTool.getName(),flowElements,11L,pigInnerJoinAddAliasTool,780,37,
					nodeElementSetting,1,new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinAddAliasTool.getProperties()[4]),"GROUP_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinAddAliasTool.getProperties()[5]),"genres"),
			},null);
			flowElements=addGeneralElement(pigStoreTool.getName(),flowElements,12L,pigStoreTool,804,175,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[4]),"TMP_FINAL_MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[5]),"/user/root/pig_data/final_movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigStoreTool.getProperties()[7]),"false"),

			},null);
			flowElements=addGeneralElement(pigInnerJoinTool.getName(),flowElements,13L,pigInnerJoinTool,573,48,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[4]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[5]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigInnerJoinTool.getProperties()[7]),"1"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(pigGroupTool.getProperties()[8]),"TMP_FINAL_MOVIES"),
			},null);
			
			
			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 4L,flowElements.get(3),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 7L,flowElements.get(6),1,flowElements.get(7),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 8L,flowElements.get(6),4,flowElements.get(7),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 9L,flowElements.get(2),1,flowElements.get(8),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 10L,flowElements.get(8),1,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 11L,flowElements.get(4),1,flowElements.get(9),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 14L,flowElements.get(12),1,flowElements.get(10),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 16L,flowElements.get(9),1,flowElements.get(12),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 17L,flowElements.get(10),1,flowElements.get(11),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 18L,flowElements.get(11),1,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			
			
			activity.setElements(flowElements);
			activity.setConnectors(flowConnectors);

			
			
		}

		return activity;
	}
	
	private FlowActivity prepareHiveSampleActivity() {
		FlowActivity activity=new FlowActivity(3L,3,"HiveSample");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();

		
		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (hiveCreateTool!=null && 
				hiveInnerJoinTool!=null && 
				hiveExecuteTool!=null && 
				hiveSelectTool!=null && 
				showStringTool!=null) {

			
			flowElements=addGeneralElement(hiveCreateTool.getName(),flowElements,1L,hiveCreateTool,38,33,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[4]),"false"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[5]),"true"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[6]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[7]),"movieId INT,title STRING,genres STRING"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[8]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[9]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[10]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[11]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[12]),"/user/root/pig_data/movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveCreateTool.getProperties()[13]),"false")
			},null);
			
			
			flowElements=addGeneralElement(hiveInnerJoinTool.getName(),flowElements,4L,hiveInnerJoinTool,487,143,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveInnerJoinTool.getProperties()[4]),"true"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveInnerJoinTool.getProperties()[5]),"GROUP_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveInnerJoinTool.getProperties()[6]),"GROUP_D.genres=GROUP_T.genres"),
			},null);
			
			
			flowElements=addGeneralElement(hiveExecuteTool.getName(),flowElements,5L,hiveExecuteTool,698,34,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(showStringTool.getName(),flowElements,6L,showStringTool,978,124,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement(hiveSelectTool.getName(),flowElements,8L,hiveSelectTool,138,124,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[4]),"ALL"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[5]),"genres,COUNT(*) AS COUNT_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[6]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[7]),"title<'D'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[8]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[9]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[10]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[11]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[12]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[13]),"GROUP_D"),
			},null);
			
			flowElements=addGeneralElement(hiveSelectTool.getName(),flowElements,9L,hiveSelectTool,250,234,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[4]),"ALL"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[5]),"genres,COUNT(*) AS COUNT_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[6]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[7]),"title>'T'"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[8]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[9]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[10]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[11]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[12]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[13]),"GROUP_T"),
			},null);
			
			flowElements=addGeneralElement(hiveSelectTool.getName(),flowElements,10L,hiveSelectTool,393,69,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[4]),"ALL"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[5]),"GROUP_D.genres,COUNT_D,COUNT_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[6]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[7]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[8]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[9]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[10]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[11]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[12]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(hiveSelectTool.getProperties()[13]),""),
			},null);
			
			
			
			
			FlowConnector flowConnector=new FlowConnector( 4L,flowElements.get(1),1,flowElements.get(2),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 5L,flowElements.get(2),1,flowElements.get(3),1);
			flowConnectors.add(flowConnector);

			flowConnector=new FlowConnector( 6L,flowElements.get(2),4,flowElements.get(3),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 7L,flowElements.get(0),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 8L,flowElements.get(4),1,flowElements.get(5),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 9L,flowElements.get(5),1,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 10L,flowElements.get(6),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
		}
		/*
		 *         "className" : "de.tum.in.aflux.component.pig.PigLoad",
	        "className" : "de.tum.in.aflux.component.pig.PigGroup",
	        "className" : "de.tum.in.aflux.component.pig.PigInnerJoin",
	        "className" : "de.tum.in.aflux.component.pig.PigExecute",
	        "className" : "de.tum.in.aflux.component.pig.PigForEach",
	        "className" : "de.tum.in.aflux.component.pig.PigInnerJoinAddAlias",
	        "className" : "de.tum.in.aflux.component.pig.PigStore",
	        "className" : "de.tum.in.aflux.component.pig.PigLoad",
	        "className" : "de.tum.in.aflux.component.pig.PigFilter",

	        
	        "className" : "de.tum.in.aflux.component.hive.HiveCreate",
	        "className" : "de.tum.in.aflux.component.hive.HiveInnerJoin",
	        "className" : "de.tum.in.aflux.component.hive.HiveExecute",
	        "className" : "de.tum.in.aflux.component.hive.HiveSelect",
	        "className" : "de.tum.in.aflux.component.hive.HiveInnerJoin",
	        "className" : "de.tum.in.aflux.component.hive.HiveExecute",
	        "className" : "de.tum.in.aflux.component.hive.HiveCreate",

		 */

		activity.setElements(flowElements);
		activity.setConnectors(flowConnectors);

		return activity;
	}
	
	
	private FlowActivity prepareCA2PigHive() {
		
		FlowActivity activity=new FlowActivity(1L,1,"CA2PIGHIVE");
		
		List<FlowSetting> settings = this.settingsRepository.findAll();
		NodeElementSetting nodeElementSetting=settings.get(0).getNodeElement();
		
		
		List<FlowElement> flowElements=new ArrayList<FlowElement>();
		List<FlowConnector> flowConnectors=new ArrayList<FlowConnector>();
		
		if (CAExecuteTool!=null && CAJoinTool!=null && 
				CALoadTool!=null && 
				CAShowTool!=null && 
				CASummarizeTool!=null && 
				CASelectTool!=null && 
				showStringValueTool!=null ) {
			
			
			flowElements=addGeneralElement("CA Execute Pig",flowElements,1L,CAExecuteTool,660,272,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAExecuteTool.getProperties()[4]),"PIG"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAExecuteTool.getProperties()[5]),"true"),
			},null);
			flowElements=addGeneralElement(CAJoinTool.getName(),flowElements,2L,CAJoinTool,429,270,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAJoinTool.getProperties()[4]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAJoinTool.getProperties()[5]),"GROUP_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAJoinTool.getProperties()[6]),"FINAL_MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAJoinTool.getProperties()[7]),"GROUP_D.genres,COUNT_T,COUNT_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAJoinTool.getProperties()[8]),"genres=genres"),
			},null);
			flowElements=addGeneralElement("CA LOAD Movies",flowElements,3L,CALoadTool,68,48,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CALoadTool.getProperties()[4]),"/user/root/pig_data/movies.csv"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CALoadTool.getProperties()[5]),"movieId:INT,title:STRING,genres:STRING"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CALoadTool.getProperties()[6]),"MOVIES"),
					
			},null);
			flowElements=addGeneralElement(CAShowTool.getName(),flowElements,4L,CAShowTool,539,74,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CAShowTool.getProperties()[4]),"FINAL_MOVIES"),
			},null);
			flowElements=addGeneralElement("CA GROUP (genre)",flowElements,5L,CASummarizeTool,175,265,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[4]),"GROUP_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[6]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[7]),"COUNT() AS COUNT_D"),
			},null);
			flowElements=addGeneralElement("CA SELECT < D",flowElements,6L,CASelectTool,128,151,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[4]),"MOVIES_D"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[5]),""),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[6]),"*"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[7]),"title<'D'"),
			},null);
			flowElements=addGeneralElement(showStringValueTool.getName(),flowElements,7L,showStringValueTool,1032,307,nodeElementSetting,1,
					new FlowElementProperty[]{
			},null);
			flowElements=addGeneralElement("CA SELECT > T",flowElements,8L,CASelectTool,325,55,nodeElementSetting,1,
					new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[4]),"MOVIES_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[5]),"MOVIES"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[6]),"*"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASelectTool.getProperties()[7]),"title>'T'"),
			},null);
			flowElements=addGeneralElement("CA GROUP (genre)",flowElements,9L,CASummarizeTool,
						371,174,nodeElementSetting,1,new FlowElementProperty[]{
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[4]),"GROUP_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[5]),"MOVIES_T"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[6]),"genres"),
					new FlowElementProperty(FlowElementProperty.toToolProperty(CASummarizeTool.getProperties()[7]),"COUNT() AS COUNT_T"),
			},null);


			// Add Connectors
			FlowConnector flowConnector=new FlowConnector( 1L,flowElements.get(0),1,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 2L,flowElements.get(0),4,flowElements.get(6),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 3L,flowElements.get(2),1,flowElements.get(5),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 4L,flowElements.get(5),1,flowElements.get(4),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 5L,flowElements.get(4),1,flowElements.get(7),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 6L,flowElements.get(7),1,flowElements.get(8),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 7L,flowElements.get(8),1,flowElements.get(1),1);
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 8L,flowElements.get(1),1,flowElements.get(3),1); // 3596
			flowConnectors.add(flowConnector);
			flowConnector=new FlowConnector( 9L,flowElements.get(3),1,flowElements.get(0),1);
			flowConnectors.add(flowConnector);
		}
		activity.setElements(flowElements);
		activity.setConnectors(flowConnectors);
		
		return activity;
		
	}
	
	
	// removed @Override
	public void run(String... arg0) throws Exception {
		boolean initializeDatabase=checkDatabaseInitialization();

		
		
		
		if (initializeDatabase) {
			this.repository.deleteAll();
			FlowActivity activity2=prepareCase1FirstWorkingSample();
			FlowActivity activity3=prepareCase2MongoTweetSample();
			FlowActivity scheduledActivity=prepareCase3Schedule();
			
			FlowJob firstJob=new FlowJob("FirstSampleJob-2");
			List<FlowActivity> activityList=new ArrayList<FlowActivity>();
			if (activity2.getElements().size()!=0 && activity3.getElements().size()!=0 && scheduledActivity.getElements().size()!=0) {
				activityList.add(activity2);
				activityList.add(activity3);
				activityList.add(scheduledActivity);
				firstJob.setActivities(activityList);
				FlowJob syncFlow1Sample=prepareSyncFlow1Sample();
				repository.save(firstJob);
				repository.save(syncFlow1Sample);
			}
			
			generateMongoDBData();
			// fetch all flowJobs
			System.out.println("FlowJob found with findAll");
			System.out.println("--------------------------");
			for (FlowJob flowJob:repository.findAll()) {
				System.out.println(flowJob);
			}
			System.out.println();
			

			// fetch all flowJobs
			System.out.println("FlowJob found with findByName('sample1')");
			System.out.println("--------------------------");
			System.out.println(repository.findByName("sample1"));
			System.out.println();
			
			// fetch all flowJobs
			System.out.println("FlowJob found with findByName('sample2')");
			System.out.println("--------------------------");
			System.out.println(repository.findByName("sample2"));
			System.out.println();
			
			System.out.println("sample mongodb data generated");
		} else {
			System.out.println("using EXISTING mongodb data");
			System.out.println("To initialize DATABASE (All data will be lost) run...");
			System.out.println("> mongo");
			System.out.println("mongo>show collections");
			System.out.println("(Collections are shown)");
			System.out.println("db.flowSetting.drop()");
			System.out.println("... and restart the app");
			
		}
		
		this.addMainSampleJobs();
		
		
		this.setInitizationSettings(false);
		
	}

	
	private void addMainSampleJobs() {
		addCAJob();
		addParallelJob();
		
	}
	
	
	// based on CAMainSample.json
	private void addCAJob() {
		String jobName="jobSampleCA-2";
		FlowJob job=this.repository.findByName(jobName);
		if (job==null) {
			FlowActivity CAactivity=prepareCA2PigHive();
			FlowActivity pigActivity=preparePigSampleActivity();
			FlowActivity hiveActivity=prepareHiveSampleActivity();
			
			FlowJob CASampleJob=new FlowJob(jobName);
			List<FlowActivity> activityList=new ArrayList<FlowActivity>();
			if (CAactivity.getElements().size()!=0 && pigActivity.getElements().size()!=0 && hiveActivity.getElements().size()!=0) {
				activityList.add(CAactivity);
				activityList.add(pigActivity);
				activityList.add(hiveActivity);
				CASampleJob.setActivities(activityList);
				repository.save(CASampleJob);
			}
		}
	}
	
	
	
	// based on sample parallelSample3Working.json
	private void addParallelJob() {
		String jobName="jobSampleParallell-2";
		FlowJob job=this.repository.findByName(jobName);
		if (job==null) {
			
			
			
			
			
			FlowActivity pigActivity=preparePigMoviesActivity();
			FlowActivity pig2Activity=preparePigLinksActivity();
			FlowActivity parallelactivity=prepareParallelSampleActivity(jobName,pigActivity,pig2Activity);
			
			FlowJob CASampleJob=new FlowJob(jobName);
			List<FlowActivity> activityList=new ArrayList<FlowActivity>();
			if (parallelactivity.getElements().size()!=0 && pigActivity.getElements().size()!=0 && pig2Activity.getElements().size()!=0) {
				activityList.add(parallelactivity);
				activityList.add(pigActivity);
				activityList.add(pig2Activity);
				CASampleJob.setActivities(activityList);
				repository.save(CASampleJob);
			}
		}
		
	}
	
	private List<FlowElement> addGeneralElement(
				String name,
				List<FlowElement> elementList,
				Long id,
				FlowElementType tool,
				int x,int y,
				NodeElementSetting nodeElementSetting,
				int concurrency,
				FlowElementProperty[] properties,
			    SubFlow subFlow) {
		
		
		FlowElement flowElement=
				new FlowElement(
						id,
						name,
						tool,
						x,y,
						nodeElementSetting.getWidth(),
						nodeElementSetting.getHeight(),
						tool.getInputInterfaces(),
						tool.getOutputInterfaces(),
						tool.getColor(),
						concurrency,
						properties,
						subFlow);
		
		
		/*
		 *                         {
		 */
		elementList.add(flowElement);
		return elementList;
	}

	
}
