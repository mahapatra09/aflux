/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
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