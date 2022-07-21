package com.renting.serviceTrans.service;

import com.renting.serviceTrans.model.Client;
import com.renting.serviceTrans.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository repository;

    @Override
    public Flux<Client> findAll() {
        return repository.findAll();
    }
    @Override
    public Mono<Client> findById(Long id) {
        Integer idInt = id.intValue();
        return repository.findById(idInt);
    }
    @Override
    public Mono<Client> findByDocument(String document)  throws Exception
    {
        Mono<Client> client = repository.findAll().filter
                (c -> c.getRuc().equals(document)).last();
        return client;
    }

}
