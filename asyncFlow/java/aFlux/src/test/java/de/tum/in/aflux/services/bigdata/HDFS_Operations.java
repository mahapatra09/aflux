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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.store.DataStoreWriter;
import org.springframework.data.hadoop.store.dataset.DatasetOperations;
import org.springframework.data.hadoop.store.dataset.RecordCallback;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;



import de.tum.in.models.bigdata.DeviceData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mahapatr on 24/09/16.
 */

@Service
public class HDFS_Operations {

    static Logger log = Logger.getLogger(HDFS_Operations.class.getName());

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
