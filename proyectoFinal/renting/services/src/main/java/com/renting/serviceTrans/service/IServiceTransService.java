package com.renting.serviceTrans.service;

import com.renting.serviceTrans.model.ServiceTrans;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IServiceTransService {
    Mono<ServiceTrans> create(ServiceTrans service) throws Exception;

    Flux<ServiceTrans> findAll();

    Mono<ServiceTrans> findById(Long id);

    Mono<ServiceTrans> findCurrentServiceDriver(String dni) throws Exception;
    Mono<ServiceTrans> findCurrentServiceClient(String ruc) throws Exception;
    Mono<ServiceTrans> update(Long idService, Mono<ServiceTrans> service) throws Exception;

    void delete(Long id);

}


