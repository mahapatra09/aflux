
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

package de.tum.in.json_responses;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mahapatr on 18/11/16.
 */
public class HDFS_FC {
    private Long records;

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }
}
