package com.springler.demo;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import com.springler.demo.data.entity.History;
import com.springler.demo.data.entity.Hourly;
import com.springler.demo.data.entity.PrecipitationDataPoint;

@Configuration
public class DemoStreamer {

  private static PrecipitationDataPoint formatHourly(Hourly hourly) {
    PrecipitationDataPoint dataPoint = new PrecipitationDataPoint(hourly);
    return dataPoint;
  }

  @Bean
  public Function<KStream<String, History>, KStream<String, PrecipitationDataPoint>> streamApp() {

    return kstream -> kstream.flatMapValues(history -> history.getHourly())
        .filter((key, hour) -> hour.getRain() != null).map((key, hourly) -> new KeyValue<>(key, formatHourly(hourly)));
  }

  @Bean
  public Function<KStream<String, PrecipitationDataPoint>, KStream<String, PrecipitationDataPoint>> streamApp2() {
    return kstream -> kstream;
  }

}
