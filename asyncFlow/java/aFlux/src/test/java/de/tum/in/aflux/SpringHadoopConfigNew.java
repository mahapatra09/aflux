

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

package de.tum.in.aflux;

import de.tum.in.aflux.bigdata.model.DeviceData;

import org.kitesdk.data.Formats;
import org.springframework.beans.factory.annotation.Autowired;
// REMOVED import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.*;

import java.util.Arrays;

/**
 *
 * @author Tanmaya Mahapatra
 *
 */
//@Configuration
//@EnableHadoop
//@ImportResource("hadoop-context.xml")
//@PropertySource("classpath:application.properties")
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

public class SpringHadoopConfigNew {


    private @Autowired
    org.apache.hadoop.conf.Configuration hadoopConfiguration;


    @Bean
    public DatasetRepositoryFactory datasetRepositoryFactory() {
        DatasetRepositoryFactory datasetRepositoryFactory = new DatasetRepositoryFactory();
        datasetRepositoryFactory.setConf(hadoopConfiguration);
        datasetRepositoryFactory.setBasePath("/user/spring");
        datasetRepositoryFactory.setNamespace("default");
        return datasetRepositoryFactory;
    }

    @Bean
    public DataStoreWriter<DeviceData> dataStoreWriter() {
        return new AvroPojoDatasetStoreWriter<DeviceData>(DeviceData.class, datasetRepositoryFactory(), deviceDataInfoDatasetDefinition());
    }

    @Bean
    public DatasetOperations datasetOperations() {
        DatasetTemplate datasetOperations = new DatasetTemplate();
        datasetOperations.setDatasetDefinitions(Arrays.asList(deviceDataInfoDatasetDefinition()));
        datasetOperations.setDatasetRepositoryFactory(datasetRepositoryFactory());
        return datasetOperations;
    }

    @Bean
    public DatasetDefinition deviceDataInfoDatasetDefinition() {
        DatasetDefinition definition = new DatasetDefinition();
        definition.setFormat(Formats.AVRO.getName());
        definition.setTargetClass(DeviceData.class);
        definition.setAllowNullValues(false);
        return definition;
    }



}
