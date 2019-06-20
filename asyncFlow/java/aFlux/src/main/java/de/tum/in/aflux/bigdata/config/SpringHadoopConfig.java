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
