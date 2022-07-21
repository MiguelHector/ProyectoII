package com.renting.serviceLog.service;

import com.renting.serviceLog.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Flux<Client> findAll();

    Mono<Client> findById(Long id);

    Mono<Client> findByDocument(String document) throws Exception;
}
