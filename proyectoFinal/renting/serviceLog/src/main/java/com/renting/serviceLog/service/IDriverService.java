package com.renting.serviceLog.service;

import com.renting.serviceLog.model.Driver;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDriverService {
    Flux<Driver> findAll();

    Mono<Driver> findById(Long id);

    Mono<Driver> findByDni(String dni) throws Exception;

}
