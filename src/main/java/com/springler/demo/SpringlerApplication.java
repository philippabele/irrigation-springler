package com.springler.demo;

import com.springler.demo.data.entity.Green;
import com.springler.demo.data.repository.GreenRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringlerApplication {

    public static final Logger log = LoggerFactory.getLogger(SpringlerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringlerApplication.class, args);
    }

    @RestController
    @RequestMapping("/history")
    public class RoomController {
        @Autowired
        private GreenRepository greenRepository;

        @GetMapping
        public Iterable<Green> getGreens() {
            return this.greenRepository.findAll();
        }

    }

}
