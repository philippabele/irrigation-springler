package com.springler.demo;

import com.springler.demo.data.entity.Green;
import com.springler.demo.data.repository.GreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringlerApplication.class, args);
    }

    @RestController
    @RequestMapping("/history")
    public class RoomController {
        @Autowired
        private GreenRepository greenRepository;

        @GetMapping
        public Iterable<Green> getRooms() {
            return this.greenRepository.findAll();
        }

    }

}
