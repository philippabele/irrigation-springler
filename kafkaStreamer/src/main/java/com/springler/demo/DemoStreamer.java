package com.springler.demo;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import com.springler.demo.data.entity.History;
import com.springler.demo.data.entity.Hourly;

@Configuration
public class DemoStreamer {

  @Bean
  public Function<KStream<String, History>, KStream<String, Hourly>> streamApp() {

    return kstream -> kstream.flatMapValues(history -> history.getHourly());

  }
}
