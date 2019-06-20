

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

package de.tum.in.controller.bigdata_apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahapatr on 28/09/16.
 */
public class HDFS_Create {
    private List<String> message = new ArrayList<String>();
    private List<String> response = new ArrayList<String>();

    public List<String> getFilename() {
        return message;
    }

    public void setFilename(List<String> filename) {
        this.message = filename;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public void addMessage(String filename) {
        this.message.add(filename);
    }

    public void addResponseCode(String response) {
        this.response.add(response);
    }
}
