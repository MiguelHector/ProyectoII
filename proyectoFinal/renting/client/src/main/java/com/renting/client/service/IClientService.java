package com.renting.client.service;

import com.renting.client.model.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Mono<Client> create(Client client) throws Exception;

    Flux<Client> findAll();

    Mono<Client> findById(Long id);

    Mono<Client> findByDocument(String document) throws Exception;

    Mono<Client> update(Long idClient, Mono<Client> client) throws Exception;

    void delete(Long id);

}


