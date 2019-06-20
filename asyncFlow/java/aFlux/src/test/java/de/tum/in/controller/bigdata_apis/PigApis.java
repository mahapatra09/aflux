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
import org.apache.pig.backend.executionengine.ExecJob;
import org.apache.pig.data.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.pig.PigTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tum.in.json_responses.HiveQueryIn;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by mahapatr on 24/09/16.
 */
@RestController
@RequestMapping("/bigdata/pig")
public class PigApis {

    static Logger log = Logger.getLogger(PigApis.class.getName());

    @Autowired
    private PigTemplate pigTemplate;

    @RequestMapping(value = "/query", method = RequestMethod.OPTIONS)
    public ResponseEntity selectOptions() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<Map<String, Object>>> queryPost(@RequestBody HiveQueryIn hiveQueryIn) {
        try {
            log.info("[pigTemplate: " + pigTemplate + "] [hiveQueryIn.getQueryCommand():" + (hiveQueryIn == null ? "ISNULL" : hiveQueryIn.getQueryCommand()) + "]");
            List<ExecJob> execJobs = pigTemplate.executeScript(hiveQueryIn.getQueryCommand());

            List<List<Object>> rows = new LinkedList();
            for (ExecJob execJob : execJobs) {
                log.info("execJob");
                if (execJob.getException() != null) {
                    execJob.getException().printStackTrace();
                }
                log.info("  [alias:" + execJob.getAlias() + "] [alias:" + execJob.getStatistics().getDuration() + "]");
                while (execJob.getResults().hasNext()) {
                    Tuple tuple = execJob.getResults().next();
                    rows.add(tuple.getAll());
//                    for (int i = 0; i < tuple.size(); i++) {
//                        log.info("    [type:" + tuple.getType(i) + "] [value:" + tuple.get(i) + "]");
//                    }
                }
            }
            return new ResponseEntity(rows, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
