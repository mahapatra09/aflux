package de.tum.in.flink;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;

public class AverageAggregate implements AggregateFunction<Double, Tuple2<Double, Integer>, Double> {
    @Override
    public Tuple2<Double, Integer> createAccumulator() {
        return new Tuple2<>(0D, 0);
    }

    @Override
    public Tuple2<Double, Integer> add(Double value, Tuple2<Double, Integer> accumulator) {
        if (value <= 0) {
            return null;
        }
        return new Tuple2<>(accumulator.f0 + value, accumulator.f1 + 1);
    }

    @Override
    public Double getResult(Tuple2<Double, Integer> accumulator) {
        return ((double) accumulator.f0) / accumulator.f1;
    }

    @Override
    public Tuple2<Double, Integer> merge(Tuple2<Double, Integer> a, Tuple2<Double, Integer> b) {
        return new Tuple2<>(a.f0 + b.f0, a.f1 + b.f1);
    }
}