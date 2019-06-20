

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
