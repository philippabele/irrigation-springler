package com.demo;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class DemoStreamer {

  @Bean
  public Function<KStream<String, String>, KStream<String, String>> streamApp() {

    return kstream -> kstream;

  }
}
