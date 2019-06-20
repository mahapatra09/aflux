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

package de.tum.in.controller.bigdata_apis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.tum.in.aflux.services.bigdata.HDFS_Operations;
import de.tum.in.essentials.NullCheck;
import de.tum.in.essentials.RandomString;
import de.tum.in.json_responses.HDFS_Create;
import de.tum.in.json_responses.HDFS_FC;
import de.tum.in.models.bigdata.Data;
import de.tum.in.models.bigdata.DeviceData;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by mahapatr on 24/09/16.
 */
@RestController
@RequestMapping("/bigdata/hdfs/")
public class HdfsApis {

    static Logger log = Logger.getLogger(HdfsApis.class.getName());

    @Autowired
    private HDFS_Operations hdfs_operations;


    @RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HDFS_Create> uploadData(@RequestBody DeviceData deviceData) {

        HDFS_Create hdfs_create = new HDFS_Create();
        boolean error = NullCheck.checkNull(deviceData);
        if(error){
            hdfs_create.addMessage("Null Values/Schema Mismatch");
            hdfs_create.addResponseCode("Failure");
            return new ResponseEntity<>(hdfs_create, HttpStatus.OK);
        }
        hdfs_create.addMessage("Data for Device ID: "+ deviceData.getDevice_name());
        try{
            if(hdfs_operations.processAVRO(deviceData))
                hdfs_create.addResponseCode("Loaded Successfully");
            else
                hdfs_create.addResponseCode("Failure");
        } catch(IOException e) {
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(hdfs_create, HttpStatus.OK);
    }

    @RequestMapping(value = "count", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<HDFS_FC> countRecords() {
        HDFS_FC hdfs_fc = new HDFS_FC();
        hdfs_fc.setRecords(hdfs_operations.countRecords());
        return new ResponseEntity<>(hdfs_fc, HttpStatus.OK);
    }

    @RequestMapping(value = "viewall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<DeviceData>> viewRecords() {

        return new ResponseEntity<>(hdfs_operations.viewAllRecords(), HttpStatus.OK);
    }

    @RequestMapping(value = "generate", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<DeviceData> generateDataset() {
        DeviceData deviceData = new DeviceData();
        RandomString deviceName = new RandomString(10);
        List<Data> dataList = new ArrayList<Data>();
        Data data = new Data();
        int min=1;
        int max = 200;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        data.setData_type("car".concat(Integer.toString(randomNum)));
        data.setTime(new Date());
        data.setValue(randomNum);
        dataList.add(data);
        deviceData.setDevice_name(deviceName.nextString());
        deviceData.setDate(new Date());
        deviceData.setData(dataList);
        return new ResponseEntity<>(deviceData, HttpStatus.OK);
    }


}
