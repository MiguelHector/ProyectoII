package com.renting.serviceLog.service;

import com.renting.serviceLog.model.ServiceTrans;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServService {
    Flux<ServiceTrans> findAll();

    Mono<ServiceTrans> findById(Long id);

    Mono<ServiceTrans> findCurrentServiceDriver(String dni) throws Exception;
    Mono<ServiceTrans> findCurrentServiceClient(String ruc) throws Exception;

}


