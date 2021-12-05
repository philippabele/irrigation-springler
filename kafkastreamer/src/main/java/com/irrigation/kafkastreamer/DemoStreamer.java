package com.irrigation.kafkastreamer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import com.irrigation.kafkastreamer.data.entity.History;
import com.irrigation.kafkastreamer.data.entity.Hourly;

@Configuration
public class DemoStreamer {

  public KeyValue<Integer, Double> reduceSingleDataPoint(String key, Hourly hourly) {
    return new KeyValue<>(hourly.getDt(), hourly.getRain().get_1h());
  }

  public KeyValue<Integer, Double> printMe(Integer i, Double d) {
    System.out.println("Key is " + i + " Value is " + d);
    return new KeyValue<>(i, d);
  }

  @Bean
  public Function<KStream<String, History>, KStream<Integer, Double>> streamApp() {

    return kstream -> kstream.flatMapValues(history -> history.getHourly())
        .filter((key, hour) -> hour.getRain() != null).map((key, hourly) -> reduceSingleDataPoint(key, hourly));
  }

  // Stream the upper one to a state store !

  // Consume the state store in the lower one :)

  @Bean
  public Function<KStream<Integer, Double>, KStream<Integer, Double>> streamApp2() {
    return kstream -> kstream.map((k, v) -> printMe(k, v)).groupByKey(Grouped.with(Serdes.Integer(), Serdes.Double()))
        .reduce(Double::sum).toStream().map((k, v) -> printMe(k, v));
  }

}
