package com.irrigation.kafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class KafkaproducerApplication {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(KafkaproducerApplication.class, args);

		KafkaProducer producer = context.getBean(KafkaProducer.class);

		while (true) {

			producer.sendHistoryWeinheim();
			Thread.sleep(100000);

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
