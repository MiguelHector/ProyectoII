package com.renting.serviceTrans.service;

import com.renting.serviceTrans.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Flux<Client> findAll();

    Mono<Client> findById(Long id);

    Mono<Client> findByDocument(String document) throws Exception;
}
