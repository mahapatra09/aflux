package de.tum.in.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;

import org.apache.flink.streaming.connectors.smartsantander.SmartSantanderAPIEndpoints;
import org.apache.flink.streaming.connectors.smartsantander.SmartSantanderSource;
import org.apache.flink.streaming.connectors.smartsantander.model.TrafficObservation;

public class SmartSantanderJobSample {

    public static void main (String[] args) throws Exception {
        StreamExecutionEnvironment see = StreamExecutionEnvironment.getExecutionEnvironment();

        SmartSantanderSource<TrafficObservation> trafficSource = new SmartSantanderSource<>(
                TrafficObservation[].class,
                SmartSantanderAPIEndpoints.TRAFFIC,
                4
        );

        DataStream<TrafficObservation> trafficObs  = see.addSource(trafficSource,
                TypeInformation.of(TrafficObservation.class));

        DataStream<Tuple2<Integer, Integer>> result = trafficObs
                .map(new MapFunction<TrafficObservation, Tuple2<Integer, Integer>>() {
                    @Override
                    public Tuple2<Integer, Integer> map(TrafficObservation trafficObservation) throws Exception {
                        return new Tuple2<>(
                                trafficObservation.getSensorID(),
                                trafficObservation.getOccupation()
                        );
                    }
                });

        result
                .map(new MapFunction<Tuple2<Integer,Integer>, String>() {
                    @Override
                    public String map(Tuple2<Integer, Integer> tuple) {
                        return tuple.toString();
                    }
                })
                .addSink(new FlinkKafkaProducer08<>("localhost:9092", "wiki-result", new SimpleStringSchema()));

        see.execute();
    }

}
