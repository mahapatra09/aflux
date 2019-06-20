

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

package de.tum.in.aflux.bigdata.config;

import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.pig.PigOperations;
import org.springframework.data.hadoop.store.dataset.*;
import org.springframework.data.hadoop.store.output.TextFileWriter;
import org.springframework.data.hadoop.store.strategy.naming.RollingFileNamingStrategy;


/**
 * Created by mahapatr on 24/09/16.
 */
@Configuration
@EnableHadoop
@ImportResource("classpath:hadoop-context.xml")
@PropertySource("classpath:application.properties")
public class SpringHadoopConfig {

    private @Autowired org.apache.hadoop.conf.Configuration hadoopConfiguration;

	@Autowired
	private PigOperations pigTemplate;    
    
    @Value("${dataset.basepath}")
	private String datasetBasepath;

    @Value("${dataset.namespace}")
	private String datasetNamespace;

    
    @Value("${java.io.tmpdir}")
	private String tmpDir;
 
    
    
    @Bean
    public DatasetRepositoryFactory datasetRepositoryFactory() {
    	System.out.println("Initializing hadoop configuration");
    	DatasetRepositoryFactory datasetRepositoryFactory = new DatasetRepositoryFactory();
        datasetRepositoryFactory.setConf(hadoopConfiguration);
        datasetRepositoryFactory.setBasePath(datasetBasepath);
        datasetRepositoryFactory.setNamespace(datasetNamespace);
    	System.out.println("Finish Initializing hadoop configuration");
        return datasetRepositoryFactory;
    }


    
    
    
    @Bean
    public TextFileWriter textFileWriter() {
    	System.out.println("path:"+datasetBasepath);
        Path path = new Path(datasetBasepath);
        System.out.println(this.tmpDir);
        TextFileWriter textFileWriter = new TextFileWriter(hadoopConfiguration, path, null);
        RollingFileNamingStrategy rollingFileNamingStrategy = new RollingFileNamingStrategy();
        textFileWriter.setFileNamingStrategy(rollingFileNamingStrategy);
        //StaticFileNamingStrategy fileNamingStrategy = new StaticFileNamingStrategy("data");
        // textFileWriter.setFileNamingStrategy(fileNamingStrategy);
        
        textFileWriter.setAppendable(true);
        textFileWriter.getOutputContext();
        return textFileWriter;
    }


}
