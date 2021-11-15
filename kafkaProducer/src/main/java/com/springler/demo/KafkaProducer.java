package com.springler.demo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.springler.demo.data.entity.City;
import com.springler.demo.data.entity.History;
import com.springler.demo.data.entity.WeatherApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, History> historyKafkaTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${WEATHER_API_KEY}")
    String weatherApiKey;

    @Value(value = "${history.topic.name}")
    private String historyTopicName;

    public void sendHistoryWeinheim() {

        String city = "Weinheim";
        Double lat = 49.5450;
        Double lon = 8.6603;

        City weinheim = new City(city, lat, lon);

        for (int i = 1; i < 6; i++) {

            long unixtime = Instant.now().minus(i, ChronoUnit.DAYS).getEpochSecond();
            String url = WeatherApi.getUrl(weinheim, unixtime, weatherApiKey);
            History history = restTemplate.getForObject(url, History.class);
            historyKafkaTemplate.send(historyTopicName, city, history);
        }

    }
}