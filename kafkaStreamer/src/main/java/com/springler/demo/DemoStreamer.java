package com.springler.demo;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import com.springler.demo.data.entity.History;
import com.springler.demo.data.entity.Hourly;

@Configuration
public class DemoStreamer {

  public KeyValue<Integer, Double> reduceSingleDataPoint(String key, Hourly hourly) {
    return new KeyValue<>(hourly.getDt(), hourly.getRain().get_1h());
  }

  @Bean
  public Function<KStream<String, History>, KStream<Integer, Double>> streamApp() {

    return kstream -> kstream.flatMapValues(history -> history.getHourly())
        .filter((key, hour) -> hour.getRain() != null).map((key, hourly) -> reduceSingleDataPoint(key, hourly));
  }

  @Bean
  public Function<KStream<Integer, Double>, KStream<Integer, Double>> streamApp2() {
    return kstream -> kstream;
  }

}
