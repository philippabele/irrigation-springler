package com.springler.demo;

import com.springler.demo.entity.Green;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenRepository extends CrudRepository<Green, Long> {
}
