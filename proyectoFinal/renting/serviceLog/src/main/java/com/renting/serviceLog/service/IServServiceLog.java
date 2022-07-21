package com.renting.serviceLog.service;

import com.renting.serviceLog.model.ServiceLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServServiceLog {
    Mono<ServiceLog> create(ServiceLog servicelog) throws Exception;

    Flux<ServiceLog> findAll();

    Mono<ServiceLog> findById(Long id);

    Mono<ServiceLog> findCurrentServiceDriver(String dni) throws Exception;
    Mono<ServiceLog> findCurrentServiceClient(String ruc) throws Exception;
    Mono<ServiceLog> findCurrentServiceVehicle(String placa) throws Exception;
    Mono<ServiceLog> update(Long idService, Mono<ServiceLog> serviceLOg) throws Exception;

    void delete(Long id);
}
