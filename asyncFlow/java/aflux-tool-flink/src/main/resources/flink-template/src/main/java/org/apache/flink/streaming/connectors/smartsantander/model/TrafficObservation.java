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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

/**
 * Observation from magnetic sensors that measure traffic.
 */
public class TrafficObservation implements SmartSantanderObservation {

	// metadata
	private int sensorID;
	private String timestamp;
	private double latitude;
	private double longitude;

	/**
	 * Time percentage that the transit loop is occupied by a vehicle.
	 */
	private int occupation;
	/**
	 * Number of counted vehicles, expanded to vehicles per hour (vph).
	 */
	private int intensity;
	/**
	 * Estimation of congestion based on occupation and intensity (on a 100-basis).
	 */
	private int charge;

	public TrafficObservation(int sensorID, String timestamp, int occupation, int intensity, int charge) {
		this.sensorID = sensorID;
		this.timestamp = timestamp;
		this.occupation = occupation;
		this.intensity = intensity;
		this.charge = charge;

		double[] coordinates = findCoordinates(sensorID);
		this.latitude = coordinates[0];
		this.longitude = coordinates[1];
	}

	@Override
	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
		double[] coordinates = findCoordinates(sensorID);
		this.latitude = coordinates[0];
		this.longitude = coordinates[1];
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public static double[] findCoordinates(int sensorID) {
		Reader in = null;
		Iterable<CSVRecord> records = null;
		try {
			ClassLoader classLoader = TrafficObservation.class.getClassLoader();
			in = new InputStreamReader(classLoader.getResourceAsStream("trafficSensorsLocation.csv"));
			records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double[] coordinates = new double[2];
		for (CSVRecord record : records) {
			if (Integer.valueOf(record.get("sensorID")) != sensorID) {
				continue;
			}
			coordinates[0] = Double.valueOf(record.get("latitude"));
			coordinates[1] = Double.valueOf(record.get("longitude"));
			break;
		}

		return coordinates;
	}

	@Override
	public String toString() {
		return "TrafficObservation: (sensorID = " + this.getSensorID() + "), " +
				"(location = " + this.getLatitude() + ", " + this.getLongitude() + "), " +
				"(timestamp = " + this.getTimestamp() + "), " +
				"(charge = " + this.getCharge() + "), " +
				"(occupation = " + this.getOccupation() + "), " +
				"(intensity = " + this.getIntensity() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensorID, timestamp, latitude, longitude, occupation, intensity, charge);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof TrafficObservation)) {
			return false;
		}
		TrafficObservation other = (TrafficObservation)o;
		return this.getSensorID() == other.getSensorID() &&
				Objects.equals(this.getTimestamp(), other.getTimestamp()) &&
				this.getLatitude() == other.getLatitude() &&
				this.getLongitude() == other.getLongitude() &&
				this.getOccupation() == other.getOccupation() &&
				this.getIntensity() == other.getIntensity() &&
				this.getCharge() == other.getCharge();
	}
}
