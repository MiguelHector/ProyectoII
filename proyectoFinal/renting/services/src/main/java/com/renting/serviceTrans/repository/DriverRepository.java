package com.renting.serviceTrans.repository;

import com.renting.serviceTrans.model.Driver;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface DriverRepository extends ReactiveMongoRepository<Driver, Integer> {
    @Query("{'dni': ?0}")
    Mono<Driver> findByDni(String dni);
}
