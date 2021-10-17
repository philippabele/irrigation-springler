package com.springler.demo.data.repository;

import com.springler.demo.data.entity.Green;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenRepository extends CrudRepository<Green, Long> {
}
