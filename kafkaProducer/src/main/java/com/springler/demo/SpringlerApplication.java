package com.springler.demo;

import com.springler.demo.data.entity.Green;
import com.springler.demo.data.entity.Quote;
import com.springler.demo.data.repository.GreenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.kafka.support.SendResult;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringlerApplication {

    public static final Logger log = LoggerFactory.getLogger(SpringlerApplication.class);

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SpringlerApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);

        /*
         * Sending a Hello World message to topic 'baeldung'. Must be received by both
         * listeners with group foo and bar with containerFactory
         * fooKafkaListenerContainerFactory and barKafkaListenerContainerFactory
         * respectively. It will also be received by the listener with
         * headersKafkaListenerContainerFactory as container factory.
         */
        producer.sendMessage("Hello, World!");

        /*
         * Sending message to a topic with 5 partitions, each message to a different
         * partition. But as per listener configuration, only the messages from
         * partition 0 and 3 will be consumed.
         */
        for (int i = 0; i < 5; i++) {
            producer.sendMessageToPartition("Hello To Partitioned Topic!", i);
        }

        /*
         * Sending message to 'filtered' topic. As per listener configuration, all
         * messages with char sequence 'World' will be discarded.
         */
        producer.sendMessageToFiltered("Hello Baeldung!");
        producer.sendMessageToFiltered("Hello World!");

        /*
         * Sending message to 'greeting' topic. This will send and received a java
         * object with the help of greetingKafkaListenerContainerFactory.
         */
        producer.sendGreetingMessage(new Greeting("Greetings", "World!"));

        /*
         * Close the app if all work is done
         */

        // context.close();

    }

    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    public static class MessageProducer {

        @Autowired
        private KafkaTemplate<String, String> kafkaTemplate;

        @Autowired
        private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

        @Value(value = "${message.topic.name}")
        private String topicName;

        @Value(value = "${partitioned.topic.name}")
        private String partitionedTopicName;

        @Value(value = "${filtered.topic.name}")
        private String filteredTopicName;

        @Value(value = "${greeting.topic.name}")
        private String greetingTopicName;

        public void sendMessage(String message) {

            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println(
                            "Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                }
            });
        }

        public void sendMessageToPartition(String message, int partition) {
            kafkaTemplate.send(partitionedTopicName, partition, null, message);
        }

        public void sendMessageToFiltered(String message) {
            kafkaTemplate.send(filteredTopicName, message);
        }

        public void sendGreetingMessage(Greeting greeting) {
            greetingKafkaTemplate.send(greetingTopicName, greeting);
        }
    }

    /*
     * Playing around with HTTP Requests to another API Using RestTemplate is the
     * classical soluation There is an alternative called WebClient for non-blocking
     * requests
     */

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RestController
    @RequestMapping("/quote")
    final class QuoteController {

        @Autowired
        private RestTemplate restTemplate;
        private Quote quote;

        @GetMapping
        public String getQuote() {
            quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
            return this.quote.toString();
        }

    }

    /*
     * Playing around with data from SQL DB
     */

    @RestController
    @RequestMapping("/history")
    final class RoomController {
        @Autowired
        private GreenRepository greenRepository;

        @GetMapping
        public Iterable<Green> getGreens() {
            return this.greenRepository.findAll();
        }

    }
}
