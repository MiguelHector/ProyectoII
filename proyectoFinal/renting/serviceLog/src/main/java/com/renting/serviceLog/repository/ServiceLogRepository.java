package com.renting.serviceLog.repository;

import com.renting.serviceLog.model.ServiceLog;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ServiceLogRepository extends ReactiveMongoRepository<ServiceLog,Integer> {

    @Query("{'dni': ?0}")
    Mono<ServiceLog> findByDni(String dni);
    @Query("{'dni': ?0}")
    Mono<ServiceLog> findCurrentServiceDriver(String dni) throws Exception;
    @Query("{'ruc': ?0}")
    Mono<ServiceLog> findCurrentServiceClient(String ruc) throws Exception;
    @Query("{'ruc': ?0}")
    Mono<ServiceLog> findCurrentServiceVehicle(String placa) throws Exception;
}
