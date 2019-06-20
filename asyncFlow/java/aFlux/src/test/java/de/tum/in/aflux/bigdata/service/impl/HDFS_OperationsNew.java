


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

package de.tum.in.aflux.bigdata.service.impl;

import de.tum.in.aflux.bigdata.model.DeviceData;
import de.tum.in.aflux.bigdata.service.HDFSOperationsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;
import org.springframework.data.hadoop.store.dataset.RecordCallback;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mahapatr on 24/09/16.
 */

@Service
public class HDFS_OperationsNew implements HDFSOperationsService {

    static Logger log = Logger.getLogger(HDFS_OperationsNew.class.getName());

    private DatasetOperations datasetOperations;

    private DataStoreWriter<DeviceData> writer;


    @Autowired
    public void setDatasetOperations(DatasetOperations datasetOperations) {
        this.datasetOperations = datasetOperations;
    }

    @Autowired
    public void setDataStoreWriter(DataStoreWriter dataStoreWriter) {
        this.writer = dataStoreWriter;
    }

    /* (non-Javadoc)
	 * @see de.tum.in.aflux.bigdata.service.impl.HDFSOperationsService#processAVRO(de.tum.in.aflux.bigdata.model.DeviceData)
	 */
    @Override
	public boolean processAVRO(DeviceData deviceData) throws IOException {

        boolean response_code = false;
        try {
                writer.write(deviceData);
        } catch (IOException e) {
            return response_code;
        }
        finally {
            writer.close();
            response_code = true;
            }
        return response_code;
    }


    /* (non-Javadoc)
	 * @see de.tum.in.aflux.bigdata.service.impl.HDFSOperationsService#countRecords()
	 */
    @Override
	public Long countRecords() {
        final AtomicLong count = new AtomicLong();
        datasetOperations.read(DeviceData.class, new RecordCallback<DeviceData>() {
            @Override
            public void doInRecord(DeviceData deviceData) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    /* (non-Javadoc)
	 * @see de.tum.in.aflux.bigdata.service.impl.HDFSOperationsService#viewAllRecords()
	 */
    @Override
	public ArrayList<DeviceData> viewAllRecords() {
        ArrayList<DeviceData> retrievedRecords = new ArrayList<>();
        datasetOperations.read(DeviceData.class, new RecordCallback<DeviceData>() {
            @Override
            public void doInRecord(DeviceData deviceData) {
               retrievedRecords.add(deviceData);
            }
        });
        return retrievedRecords;
    }
}
