/*
 *
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

import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.tum.in.aflux.services.bigdata.HBaseIoTDataRepository;
import de.tum.in.aflux.services.bigdata.HBaseIoTDataUtils;
import de.tum.in.essentials.NullCheck;
import de.tum.in.json_responses.HBase_Response;
import de.tum.in.json_responses.HDFS_Create;
import de.tum.in.models.bigdata.Data;
import de.tum.in.models.bigdata.DeviceData;
import de.tum.in.models.bigdata.HBaseIoTData;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by mahapatr on 22/01/17.
 */
@RestController
@RequestMapping("/bigdata/hbase/")
public class HBaseApis {

    static Logger log = Logger.getLogger(HBaseApis.class.getName());

    @Autowired
    private HBaseIoTDataUtils hBaseIoTDataUtils;

    @Autowired
    private HBaseIoTDataRepository hBaseIoTDataRepository;

    @RequestMapping(value = "init", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> initHbase() {

        try{
            if(hBaseIoTDataUtils.initialize())
                return new ResponseEntity<>("true", HttpStatus.OK);
        } catch (Exception exception){};

        return new ResponseEntity<>("false", HttpStatus.OK);
    }

    @RequestMapping(value = "store", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<HDFS_Create> storeHbase(@RequestBody DeviceData deviceData) {
        HDFS_Create hdfs_create = new HDFS_Create();
        boolean error = NullCheck.checkNull(deviceData);
        if(error){
            hdfs_create.addMessage("Null Values/Schema Mismatch");
            hdfs_create.addResponseCode("Failure");
            return new ResponseEntity<>(hdfs_create, HttpStatus.OK);
        }
        hBaseIoTDataUtils.addIoTData(deviceData);
        hdfs_create.addResponseCode("Loaded Successfully");
        hdfs_create.addMessage("Data for Device ID: "+ deviceData.getDevice_name());

        return new ResponseEntity<>(hdfs_create, HttpStatus.OK);

    }

    @RequestMapping(value = "findall", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<HBaseIoTData>> findAll() {
        return new ResponseEntity<>(hBaseIoTDataRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<HBase_Response> search(@RequestParam String searchTerm) {

        return new ResponseEntity<>( hBaseIoTDataUtils.searchIoTData(searchTerm), HttpStatus.OK);
    }


}
