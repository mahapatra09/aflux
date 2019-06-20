

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

package de.tum.in.aflux.component.trafficA9;

import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;

public class TrafficA9Constants {
	public static String COLOR="#B0BEC5";

	
	/**
	 * TrafficA9ExecutionConnectors
	 */
	public static int SHOULDER_CONTROL_OUTPUT=1;
	public static int REPORT_OUTPUT=2;
	public static int EXECUTION_LOG_OUTPUT=3;

	
	
	public static int WORKFLOW_OUTPUT=1;
	public static String PRODUCT_DISCRIMINATOR_LABEL="product discriminator";
	public static String IGNORE_FIRST_RESULTS="ignore first results";
	public static String SAMPLE_SIZE="sample size";
	public static String OCCUPANCY_FILTER="occupancy filter";
}
