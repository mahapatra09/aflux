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

package org.apache.flink.streaming.connectors.smartsantander;

import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.connectors.smartsantander.model.SmartSantanderObservation;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Data source that can be used in Flink jobs and retrieves data from the sensors deployed
 * in Smart Santander.
 */
public class SmartSantanderSource<T extends SmartSantanderObservation> extends RichSourceFunction<T> {

	/**
	 * Endpoint of the API.
	 */
	private final SmartSantanderAPIEndpoints endpoint;
	/**
	 * Frequency of updates expressed in seconds.
	 */
	private final int updateFrequency;
	/**
	 * Type of the observations array, necessary to perform deserialization.
	 */
	private final Class<T[]> observationsArrayClass;

	private volatile boolean isRunning = true;

	public SmartSantanderSource(Class<T[]> observationsArrayClass, SmartSantanderAPIEndpoints endpoint, int updateFrequency) {
		this.endpoint = endpoint;
		this.updateFrequency = updateFrequency;
		this.observationsArrayClass = observationsArrayClass;
	}

	@Override
	public void run(SourceContext<T> ctx) throws Exception {
		try (SmartSantanderObservationStream<T> stream = new SmartSantanderObservationStream<>(observationsArrayClass, endpoint, updateFrequency)) {
			// Open connection
			stream.connect();

			while (isRunning) {
				// Query for the next observation event
				T event = stream.getObservations().poll(1, TimeUnit.SECONDS);

				if (event != null) {
					ctx.collect(event);
				}
			}
		}
	}

	@Override
	public void cancel() {
		isRunning = false;
	}

}
