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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import de.tum.in.aflux.services.bigdata.HDFS_Operations;
import de.tum.in.json_responses.HDFS_Create;
import de.tum.in.json_responses.HDFS_FC;
import de.tum.in.json_responses.HiveQueryIn;
import de.tum.in.models.bigdata.Data;
import de.tum.in.models.bigdata.DeviceData;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


/**
 * Created by mahapatr on 24/09/16.
 */
@RestController
@RequestMapping("/bigdata/hive")
public class HiveApis {

    static Logger log = Logger.getLogger(HiveApis.class.getName());

    @Autowired
    private JdbcTemplate hiveTemplate;

    @RequestMapping(value = "/update", method = RequestMethod.OPTIONS)
    public ResponseEntity updateOptions() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HDFS_Create> updatePost(@RequestBody HiveQueryIn hiveQueryIn) {
        HDFS_Create hdfs_create = new HDFS_Create();
        try {
            log.info("[hiveTemplate: " + hiveTemplate + "] [hiveQueryIn.getQueryCommand():" + (hiveQueryIn == null ? "ISNULL" : hiveQueryIn.getQueryCommand()) + "]");
            hiveTemplate.execute(hiveQueryIn.getQueryCommand());
            hdfs_create.addResponseCode("Executed");
        } catch (Exception e) {
            e.printStackTrace();
            hdfs_create.addResponseCode("Failed " + e.getMessage());
        }
        return new ResponseEntity<>(hdfs_create, HttpStatus.OK);
    }

    @RequestMapping(value = "/select", method = RequestMethod.OPTIONS)
    public ResponseEntity selectOptions() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Map<String, Object>>> selectPost(@RequestBody HiveQueryIn hiveQueryIn) {
        try {
            log.info("[hiveTemplate: " + hiveTemplate + "] [hiveQueryIn.getQueryCommand():" + (hiveQueryIn == null ? "ISNULL" : hiveQueryIn.getQueryCommand()) + "]");
            hiveTemplate.execute(hiveQueryIn.getQueryCommand());
            List<Map<String, Object>> rows = hiveTemplate.queryForList(hiveQueryIn.getQueryCommand());
            log.info("[select.rows.length:" + (rows == null ? "ISNULL" : rows.size()) + "]");
            return new ResponseEntity(rows, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
