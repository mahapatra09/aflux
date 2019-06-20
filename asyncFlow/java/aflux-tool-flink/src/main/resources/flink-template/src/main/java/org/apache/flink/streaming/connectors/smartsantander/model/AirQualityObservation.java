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

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Observation from mobile sensors that measure different gas levels in air.
 */
public class AirQualityObservation implements SmartSantanderObservation {

	// metadata
	@SerializedName("dc:identifier")
	private int sensorID;
	@SerializedName("dc:modified")
	private String timestamp;
	@SerializedName("ayto:latitude")
	private double latitude;
	@SerializedName("ayto:longitude")
	private double longitude;

	/**
	 * Level of NO2 expressed in ug/m3.
	 */
	@SerializedName("ayto:NO2")
	private double levelOfNO2;
	/**
	 * Level of CO expressed in mg/m3.
	 */
	@SerializedName("ayto:CO")
	private double levelofCO;
	/**
	 * Level of ozone expressed in ug/m3.
	 */
	@SerializedName("ayto:ozone")
	private double levelOfOzone;
	/**
	 * Measured temperature, expressed in degrees Celsius (ºC).
	 */
	@SerializedName("ayto:temperature")
	private double temperature;

	public AirQualityObservation() {
		this(0, null, 0, 0, 0, 0, 0, 0);
	}

	public AirQualityObservation(int sensorID, String timestamp, double latitude, double longitude, double levelOfNO2, double levelOfCO, double levelOfOzone, double temperature) {
		this.sensorID = sensorID;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.levelOfNO2 = levelOfNO2;
		this.levelofCO = levelOfCO;
		this.levelOfOzone = levelOfOzone;
		this.temperature = temperature;
	}

	@Override
	public int getSensorID() {
		return sensorID;
	}

	public void setSensorID(int sensorID) {
		this.sensorID = sensorID;
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLevelOfNO2() {
		return levelOfNO2;
	}

	public void setLevelOfNO2(double levelOfNO2) {
		this.levelOfNO2 = levelOfNO2;
	}

	public double getLevelofCO() {
		return levelofCO;
	}

	public void setLevelofCO(double levelofCO) {
		this.levelofCO = levelofCO;
	}

	public double getLevelOfOzone() {
		return levelOfOzone;
	}

	public void setLevelOfOzone(double levelOfOzone) {
		this.levelOfOzone = levelOfOzone;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "AirQualityObservation: (sensorID = " + this.getSensorID() + "), " +
				"(location = " + this.getLatitude() + ", " + this.getLongitude() + "), " +
				"(timestamp = " + this.getTimestamp() + "), " +
				"(temperature = " + this.getTemperature() + "), " +
				"(NO2 = " + this.getLevelOfNO2() + "), " +
				"(CO = " + this.getLevelofCO() + "), " +
				"(Ozone = " + this.getLevelOfOzone() + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensorID, timestamp, latitude, longitude, levelOfNO2, levelofCO, levelOfOzone, temperature);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AirQualityObservation)) {
			return false;
		}
		AirQualityObservation other = (AirQualityObservation) o;
		return this.getSensorID() == other.getSensorID() &&
				Objects.equals(this.getTimestamp(), other.getTimestamp()) &&
				this.getLatitude() == other.getLatitude() &&
				this.getLongitude() == other.getLongitude() &&
				this.getTemperature() == other.getTemperature() &&
				this.getLevelOfNO2() == other.getLevelOfNO2() &&
				this.getLevelofCO() == other.getLevelofCO() &&
				this.getLevelOfOzone() == other.getLevelOfOzone();
	}
}
