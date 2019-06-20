/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.connectors.smartsantander.model;

/**
 * Observation from the sensors in Smart Santander. Implemented by all
 * observation classes. Required to bound SmartSantanderObservationStream.
 */
public interface SmartSantanderObservation {

	/**
	 * Get the unique identifier of the sensor related with the observation.
	 *
	 * @return ID of the sensor
	 */
	int getSensorID();

	/**
	 * Get the timestamp associated to the observation.
	 *
	 * @return Timestamp expressed in ISO8601 format
	 */
	String getTimestamp();

	/**
	 * Get the latitude associated to the observation.
	 *
	 * @return Latitude expressed in degrees
	 */
	double getLatitude();

	/**
	 * Get the longitude associated to the observation.
	 *
	 * @return Longitude expressed in degrees
	 */
	double getLongitude();

}
