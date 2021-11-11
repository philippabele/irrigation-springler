package com.springler.demo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.springler.demo.data.entity.City;
import com.springler.demo.data.entity.History;
import com.springler.demo.data.entity.Hourly;
import com.springler.demo.data.entity.WeatherApi;

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

        producer.sendHistoryWeinheim();

        /*
         * Sending a Hello World message to topic 'baeldung'. Must be received by both
         * listeners with group foo and bar with containerFactory
         * fooKafkaListenerContainerFactory and barKafkaListenerContainerFactory
         * respectively. It will also be received by the listener with
         * headersKafkaListenerContainerFactory as container factory.
         */
        // producer.sendMessage("Hello, World!");

        /*
         * Sending message to a topic with 5 partitions, each message to a different
         * partition. But as per listener configuration, only the messages from
         * partition 0 and 3 will be consumed.
         */
        // for (int i = 0; i < 5; i++) {
        // producer.sendMessageToPartition("Hello To Partitioned Topic!", i);
        // }

        /*
         * Sending message to 'filtered' topic. As per listener configuration, all
         * messages with char sequence 'World' will be discarded.
         */
        // producer.sendMessageToFiltered("Hello Baeldung!");
        // producer.sendMessageToFiltered("Hello World!");

        /*
         * Sending message to 'greeting' topic. This will send and received a java
         * object with the help of greetingKafkaListenerContainerFactory.
         */
        // producer.sendGreetingMessage(new Greeting("Greetings", "World!"));

        /*
         * Close the app if all work is done
         */

        context.close();

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

        @Autowired
        private KafkaTemplate<String, History> historyKafkaTemplate;

        @Autowired
        private RestTemplate restTemplate;

        @Value(value = "${history.topic.name}")
        private String historyTopicName;

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

        public void sendHistoryWeinheim() {

            for (int i = 1; i < 6; i++) {
                City weinheim = new City("Weinheim", 49.5450, 8.6603);
                long unixYesterday = Instant.now().minus(i, ChronoUnit.DAYS).getEpochSecond();
                String url = WeatherApi.getUrl(weinheim, unixYesterday, "261aefb083ddc24c99eecdc9552f6ce7");
                History history = restTemplate.getForObject(url, History.class);
                historyKafkaTemplate.send(historyTopicName, "WeinheimYesterday", history);
            }

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
    @RequestMapping("/weatherapi")
    final class HistoryController {

        /**
         *
         */
        private static final String url = "http://api.openweathermap.org/data/2.5/onecall/timemachine?lat=49.5450&lon=8.6603&dt=1636287136&appid=???";

        @Autowired
        private RestTemplate restTemplate;
        private History history;

        @GetMapping
        public String getHistory() {
            history = restTemplate.getForObject(url, History.class);

            String output = "";

            for (Hourly hour : this.history.getHourly()) {
                output = output + hour.getPressure().toString() + " / ";
            }

            return output;
        }

    }

    /*
     * Playing around with data from SQL DB
     */

}
