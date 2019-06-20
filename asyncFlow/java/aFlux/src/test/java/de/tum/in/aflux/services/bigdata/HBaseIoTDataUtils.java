

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

package de.tum.in.aflux.services.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;

import de.tum.in.json_responses.HBase_Response;
import de.tum.in.models.bigdata.Data;
import de.tum.in.models.bigdata.DeviceData;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

/**
 * Created by mahapatr on 22/01/17.
 */
@Component
public class HBaseIoTDataUtils implements InitializingBean {

    private String tableName = "hBase_iot_data";

    @Resource(name = "hbaseConfiguration")
    private Configuration config;


    @Autowired
    private HBaseIoTDataRepository hBaseIoTDataRepository;

    private HBaseAdmin admin;
    private HTable table;

    private HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

    public boolean initialize() throws IOException {

        /*if (admin.tableExists(tableNameAsBytes)) {
            admin.disableTable(tableNameAsBytes);
            admin.deleteTable(tableNameAsBytes);
        }*/

        HColumnDescriptor columnDescriptor = new HColumnDescriptor(
                HBaseIoTDataRepository.CF_INFO);
        tableDescriptor.addFamily(columnDescriptor);
        admin.createTable(tableDescriptor);
        return true;
    }

    public void addIoTData(DeviceData deviceData) {

        List<Data> data = deviceData.getData();
        for(Data d : data){
            hBaseIoTDataRepository.save(tableDescriptor,deviceData.getDevice_name() ,d.getData_type(),
                    String.valueOf(d.getValue()));
        }
    }

    public HBase_Response searchIoTData(String searchTerm ) {


        return hBaseIoTDataRepository.search(searchTerm, table);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        admin = new HBaseAdmin(config);
        table = new HTable(config, tableName);
    }

}


