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
import de.tum.in.aflux.component.kafka.actor.ApacheKafkaProducerActor;

/**
 * Publish when asked 
 * It will publish as value the string it recevies. The topic is configured entered as property
 * @author Petr Vasilievich Romanov
 */
public class ApacheKafkaProducer extends AbstractMainExecutor
{
    public static final String NAME="Kafka producer";

    public static ToolPropertyOption[] serializerOptionsK={
    		new ToolPropertyOption("string","org.apache.kafka.common.serialization.StringSerializer;"),
    		new ToolPropertyOption("JSON","org.apache.kafka.common.serialization.JSONSerializer")};
    public static ToolPropertyOption[] serializerOptionsV={
    		new ToolPropertyOption("string","org.apache.kafka.common.serialization.StringSerializer;"),
    		new ToolPropertyOption("JSON","org.apache.kafka.common.serialization.JSONSerializer")};
    
    
    
    static public ToolProperty[] serviceProperties = {
            new ToolProperty(ApacheKafkaProducerConstants.HOST,"localhost:9092", PropertyInputType.TEXT,null,"Kafka broker host","",false),
            new ToolProperty(ApacheKafkaProducerConstants.TOPIC,"common",PropertyInputType.TEXT,null,"Topic name","",false),
            new ToolProperty(ApacheKafkaProducerConstants.ACKS,"all",PropertyInputType.TEXT,null,"acks for producer requests","",false),
            new ToolProperty(ApacheKafkaProducerConstants.RETRIES,"0",PropertyInputType.TEXT,null,"set autoretry in case connection failure","",false),
            new ToolProperty(ApacheKafkaProducerConstants.BATCH,"16384",PropertyInputType.TEXT,null,"buffer size in config","",false),
            new ToolProperty(ApacheKafkaProducerConstants.LENGTH,"1",PropertyInputType.TEXT,null,"reduce the no of requests less than 0","",false),
            new ToolProperty(ApacheKafkaProducerConstants.BUFFER,"33554432",PropertyInputType.TEXT,null,"totally available for producer memory","",false),
            new ToolProperty(ApacheKafkaProducerConstants.SERIALIZERK,"org.apache.kafka.common.serialization.StringSerializer",PropertyInputType.SELECT,serializerOptionsK,"serializer key","",false),
            new ToolProperty(ApacheKafkaProducerConstants.SERIALIZERV,"org.apache.kafka.common.serialization.StringSerializer",PropertyInputType.SELECT,serializerOptionsV,"serializer value","",false)
    };

    
    public ApacheKafkaProducer()
    {
        super(NAME,
            ApacheKafkaProducerActor.class.getCanonicalName(),
            ApacheKafkaProducer.class.getName(),
            1,
            0,
            NodeLaunchType.LAUNCHED_BY_DATA,
            true,
            serviceProperties);

        this.setColor(ApacheKafkaProducerConstants.COLOR);
    }

}
