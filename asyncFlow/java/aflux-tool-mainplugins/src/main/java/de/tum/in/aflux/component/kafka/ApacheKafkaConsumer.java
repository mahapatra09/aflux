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

import de.tum.in.aflux.tools.core.AbstractMainExecutor;
import de.tum.in.aflux.tools.core.NodeLaunchType;
import de.tum.in.aflux.tools.core.PropertyInputType;
import de.tum.in.aflux.tools.core.ToolProperty;
import de.tum.in.aflux.tools.core.ToolPropertyOption;


import de.tum.in.aflux.component.kafka.actor.ApacheKafkaConsumerActor;

/**
 * First time creates the Kafka consumer
 * Each time a message is received it will output the message for the corresponding topic
 * @author Petr Vasilievich Romanov
 */

public class ApacheKafkaConsumer extends AbstractMainExecutor
{
    public static final String NAME="Kafka consumer";
    public static ToolPropertyOption[] deserializerOptionsK={
    		new ToolPropertyOption("string","org.apache.kafka.common.serialization.StringDeserializer"),
    		new ToolPropertyOption("JSON","org.apache.kafka.common.serialization.JSONDeserializer")};
    public static ToolPropertyOption[] deserializerOptionsV={
    		new ToolPropertyOption("string","org.apache.kafka.common.serialization.StringDeserializer;"),
    		new ToolPropertyOption("JSON","org.apache.kafka.common.serialization.JSONDeserializer")};
    public static ToolPropertyOption[] factoryOptions={
    		new ToolPropertyOption("",""),
    		new ToolPropertyOption("trafficA9.tick","de.tum.in.aflux.component.trafficA9.model.Tick"),
    		new ToolPropertyOption("trafficA9.occupancy","de.tum.in.aflux.component.trafficA9.model.Occupancy")};
    
    static public ToolProperty[] serviceProperties = {
            new ToolProperty(ApacheKafkaConsumerConstants.HOST,"localhost:9092", PropertyInputType.TEXT,null,"Kafka broker host","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.TOPIC,"common",PropertyInputType.TEXT,null,"Topic name","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.GROUP,"test",PropertyInputType.TEXT,null,"group ID","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.AUTOCOMMIT,"true",PropertyInputType.TEXT,null,"set autoretry in case connection failure","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.INTERVAL,"1000",PropertyInputType.TEXT,null,"autoretries time interval","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.DURATION,"30000",PropertyInputType.TEXT,null,"maximum possible time of one try","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.DESERIALIZERK,
            			"org.apache.kafka.common.serialization.StringDeserializer",
            			PropertyInputType.SELECT,
            			deserializerOptionsK,
            			"serializer key",
            			"",
            			false),
            new ToolProperty(ApacheKafkaConsumerConstants.DESERIALIZERV,
            		"org.apache.kafka.common.serialization.StringDeserializer",
            		PropertyInputType.SELECT,
            		deserializerOptionsV,            		
            		"serializer value",
            		"",
            		false),
            new ToolProperty(ApacheKafkaConsumerConstants.POLL_TIME_LIMIT,"1000",PropertyInputType.TEXT,null,"poll time limit when consuming. Generally it should be greater than the period time of execution","",false),
            new ToolProperty(ApacheKafkaConsumerConstants.FACTORY_CLASS,
            		"",
            		PropertyInputType.SELECT,
            		factoryOptions,
        			"full name of java class that implements KafkaAfluxDataFactory - If null it is not used. If setted the output message will be an object of this specified type",
            		"",
            		false)
    };

    public ApacheKafkaConsumer()
    {
        super(NAME,
                ApacheKafkaConsumerActor.class.getCanonicalName(),
                ApacheKafkaConsumer.class.getName(),
                1,
                1,
                NodeLaunchType.LAUNCHED_BY_SIGNAL,//BY_DATA? - it's by data because the input indicates when to start and not the needed input data to start
                true,
                serviceProperties);

        this.setColor(ApacheKafkaProducerConstants.COLOR);
    }

}