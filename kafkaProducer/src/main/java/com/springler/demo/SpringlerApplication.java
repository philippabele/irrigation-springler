package com.springler.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringlerApplication {

    public static final Logger log = LoggerFactory.getLogger(SpringlerApplication.class);

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SpringlerApplication.class, args);

        KafkaProducer producer = context.getBean(KafkaProducer.class);

        while (true) {

            producer.sendHistoryWeinheim();
            Thread.sleep(5000);

        }

    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer();
    }

}
