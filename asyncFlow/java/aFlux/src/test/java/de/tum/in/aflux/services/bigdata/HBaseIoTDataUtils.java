/*
 *
 *  * IoT BigData Middleware : Interfacing IoT Mashups and Big Data
 *  * Copyright (C) 2016  Software & Systems Engineering , TUM
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


