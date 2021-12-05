package com.irrigation.kafkaconsumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.irrigation.kafkaconsumer.entity.Green;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.listener.MessageListener;

import org.springframework.kafka.support.KafkaHeaders;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

@SpringBootApplication
public class KafkaconsumerApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaconsumerApplication.class, args);

		MessageListener listener = context.getBean(MessageListener.class);

		listener.latch.await(10, TimeUnit.SECONDS);

		listener.partitionLatch.await(10, TimeUnit.SECONDS);

		listener.filterLatch.await(10, TimeUnit.SECONDS);

		listener.greetingLatch.await(10, TimeUnit.SECONDS);

	}

	@RestController
	@RequestMapping("/history")
	final class GreenController {
		@Autowired
		private GreenRepository greenRepository;

		@GetMapping
		public Iterable<Green> getGreens() {
			return this.greenRepository.findAll();
		}

	}

	@RestController
	@RequestMapping("/newentry")
	final class EntryController {
		@Autowired
		private GreenRepository greenRepository;

		@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Green> addData(@RequestBody Green newGreen) {
			this.greenRepository.save(newGreen);
			return new ResponseEntity<>(newGreen, HttpStatus.CREATED);
		}
	}

	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

	public static class MessageListener {

		private CountDownLatch latch = new CountDownLatch(3);

		private CountDownLatch partitionLatch = new CountDownLatch(2);

		private CountDownLatch filterLatch = new CountDownLatch(2);

		private CountDownLatch greetingLatch = new CountDownLatch(1);

		@KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
		public void listenGroupFoo(String message) {
			System.out.println("Received Message in group 'foo': " + message);
			latch.countDown();
		}

		@KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
		public void listenGroupBar(String message) {
			System.out.println("Received Message in group 'bar': " + message);
			latch.countDown();
		}

		@KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
		public void listenWithHeaders(@Payload String message,
				@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
			System.out.println("Received Message: " + message + " from partition: " + partition);
			latch.countDown();
		}

		@KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = { "0",
				"3" }), containerFactory = "partitionsKafkaListenerContainerFactory")
		public void listenToPartition(@Payload String message,
				@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
			System.out.println("Received Message: " + message + " from partition: " + partition);
			this.partitionLatch.countDown();
		}

		@KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
		public void listenWithFilter(String message) {
			System.out.println("Received Message in filtered listener: " + message);
			this.filterLatch.countDown();
		}

		@KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
		public void greetingListener(Greeting greeting) {
			System.out.println("Received greeting message: " + greeting);
			this.greetingLatch.countDown();
		}

	}

}
