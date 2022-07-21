package com.renting.serviceTrans.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.renting.serviceTrans.model.ServiceTrans;

import reactor.core.publisher.Mono;

@Repository
public interface ServiceRepository extends ReactiveMongoRepository<ServiceTrans, Integer> {
    @Query("{'dni': ?0}")
    Mono<ServiceTrans> findCurrentServiceDriver(String dni) throws Exception;
    @Query("{'ruc': ?0}")
    Mono<ServiceTrans> findCurrentServiceClient(String ruc) throws Exception;
    @Query("{'ruc': ?0}")
    Mono<ServiceTrans> findCurrentServiceVehicle(String placa) throws Exception;
}
