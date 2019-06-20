
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
