/*
 *
 *  * aFlux : JVM Based IoT Mashup Tool
 *  * Copyright (C) 2017  Software & Systems Engineering , TUM
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
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
