

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

package de.tum.in.aflux.services.bigdata;

/**
 * Created by mahapatr on 22/01/17.
 */
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import de.tum.in.aflux.bigdata.config.SpringHadoopConfig;
import de.tum.in.json_responses.HBase_Response;
import de.tum.in.models.bigdata.HBaseIoTData;

@Repository
@Import(value = { SpringHadoopConfig.class })
//@ImportResource("hadoop-context.xml")
//@EnableAutoConfiguration
public class HBaseIoTDataRepository {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "hBase_iot_data";

    public static byte[] CF_INFO = Bytes.toBytes("hBase_iot_data");

    private byte[] qDevice_Name = Bytes.toBytes("device_name");
    private byte[] qData_Type = Bytes.toBytes("data_type");
    private byte[] qValue = Bytes.toBytes("value");

    public List<HBaseIoTData> findAll() {
        return hbaseTemplate.find(tableName, "hBase_iot_data", new RowMapper<HBaseIoTData>() {
            @Override
            public HBaseIoTData mapRow(Result result, int rowNum) throws Exception {
                return new HBaseIoTData(Bytes.toString(result.getValue(CF_INFO, qDevice_Name)),
                        Bytes.toString(result.getValue(CF_INFO, qData_Type)),
                        Bytes.toString(result.getValue(CF_INFO, qValue)));
            }
        });

    }

    public HBaseIoTData save(HTableDescriptor tableDescriptor, final String deviceName, final String dataType,
                             final String value) {
        return hbaseTemplate.execute(tableDescriptor.getTableName().toString(), new TableCallback<HBaseIoTData>() {
            public HBaseIoTData doInTable(HTableInterface table) throws Throwable {
                HBaseIoTData hBaseIoTData = new HBaseIoTData(deviceName, dataType, value);
                Put p = new Put(Bytes.toBytes(hBaseIoTData.getDevice_name()));
                p.add(CF_INFO, qDevice_Name, Bytes.toBytes(hBaseIoTData.getDevice_name()));
                p.add(CF_INFO, qData_Type, Bytes.toBytes(hBaseIoTData.getData_type()));
                p.add(CF_INFO, qValue, Bytes.toBytes(hBaseIoTData.getValue()));
                table.put(p);
                return hBaseIoTData;

            }
        });
    }

    public HBase_Response search(String searchString, HTable table) {

        Scan scan = new Scan();
        // Scanning the required columns
        scan.addColumn(Bytes.toBytes("hBase_iot_data"), Bytes.toBytes("device_name"));
        scan.addColumn(Bytes.toBytes("hBase_iot_data"), Bytes.toBytes("data_type"));
        scan.addColumn(Bytes.toBytes("hBase_iot_data"), Bytes.toBytes("value"));
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes("hBase_iot_data"),
                Bytes.toBytes("device_name"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(searchString));
        scan.setFilter(filter);
        HBase_Response hBase_response = new HBase_Response();
        // Getting the scan result
        try {
            ResultScanner scanner = table.getScanner(scan);
            for (Result result = scanner.next(); (result != null); result = scanner.next()) {
                for (KeyValue keyValue : result.list()) {
                    hBase_response.addkey(keyValue.getKeyString());
                    hBase_response.addvalue(Bytes.toString(keyValue.getValue()));
                }
            }
            return hBase_response;
        } catch (IOException e) {}


    return null;

    }

}

