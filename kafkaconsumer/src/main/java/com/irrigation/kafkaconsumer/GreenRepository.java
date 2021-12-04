package com.irrigation.kafkaconsumer;

import com.irrigation.kafkaconsumer.entity.Green;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenRepository extends JpaRepository<Green, Long> {
}
