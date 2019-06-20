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
