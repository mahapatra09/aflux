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

package de.tum.in.aflux.component.kafka;

public class ApacheKafkaConsumerConstants
{
    public static final String POLL_TIME_LIMIT = "poll time limit";
	public static final String FACTORY_CLASS = "data factory class";
	public static final String HOST="bootstrap.servers";
    public static final String TOPIC="topic";
    public static final String GROUP="group.id";
    public static final String AUTOCOMMIT="enable.auto.commit";
    public static final String INTERVAL="auto.commit.interval.ms";
    public static final String DURATION="session.timeout.ms";
    public static final String DESERIALIZERK="key.deserializer";
    public static final String DESERIALIZERV="value.deserializer";
    public static final String COLOR="#E050C0";
}
