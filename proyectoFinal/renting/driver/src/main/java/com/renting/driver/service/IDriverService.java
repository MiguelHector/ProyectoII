package com.renting.driver.service;

import com.renting.driver.model.Driver;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDriverService {
    Mono<Driver> create(Driver driver) throws Exception;

    Flux<Driver> findAll();

    Mono<Driver> findById(Long id);

    Mono<Driver> findByDni(String dni) throws Exception;

    Mono<Driver> update(Long idDriver, Mono<Driver> driver) throws Exception;

    void delete(Long id);

}


