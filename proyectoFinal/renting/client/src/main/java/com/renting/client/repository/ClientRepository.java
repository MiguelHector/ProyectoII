package com.renting.client.repository;

import org.bson.BsonInt64;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.renting.client.model.Client;

import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {
    @Query("{'document': ?0}")
    Mono<Client> findByDocument(String document);

}
