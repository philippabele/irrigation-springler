package com.springler.demo;

import com.springler.demo.entity.Green;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenRepository extends JpaRepository<Green, Long> {
}
