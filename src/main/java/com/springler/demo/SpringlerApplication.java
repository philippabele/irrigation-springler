package com.springler.demo;

import com.springler.demo.data.entity.Green;
import com.springler.demo.data.entity.Quote;
import com.springler.demo.data.repository.GreenRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringlerApplication {

    public static final Logger log = LoggerFactory.getLogger(SpringlerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringlerApplication.class, args);
    }

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
