package com.renting.serviceLog.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.renting.serviceLog.model.ServiceTrans;

import reactor.core.publisher.Mono;

@Repository
public interface ServiceRepository extends ReactiveMongoRepository<ServiceTrans, Integer> {
    @Query("{'dni': ?0}")
    Mono<ServiceTrans> findByDni(String dni);
    @Query("{'dni': ?0}")
    Mono<ServiceTrans> findCurrentServiceDriver(String dni) throws Exception;
    @Query("{'ruc': ?0}")
    Mono<ServiceTrans> findCurrentServiceClient(String ruc) throws Exception;

}
