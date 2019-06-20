
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

package de.tum.in.aflux.bigdata.service;

import java.io.IOException;
import java.util.ArrayList;

import de.tum.in.aflux.bigdata.model.DeviceData;


/**
 * 
 * @author Tanmaya Mahapatra
 * 
 * Interface to access HDFS_Operations implementation
 *
 */
public interface HDFSOperationsService {

	public abstract boolean processAVRO(DeviceData deviceData)
			throws IOException;

	public abstract Long countRecords();

	public abstract ArrayList<DeviceData> viewAllRecords();

}