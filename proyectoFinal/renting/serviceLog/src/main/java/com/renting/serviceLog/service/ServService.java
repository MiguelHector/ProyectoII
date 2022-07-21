package com.renting.serviceLog.service;

import com.renting.serviceLog.model.Client;
import com.renting.serviceLog.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import com.renting.serviceLog.model.ServiceTrans;
import com.renting.serviceLog.repository.ServiceRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServService implements IServService {
    @Autowired
    ServiceRepository repository;

    @Override
    public Flux<ServiceTrans> findAll() {
        Flux<ServiceTrans> c=repository.findAll();

        return repository.findAll();
    }
    @Override
    public Mono<ServiceTrans> findById(Long id) {
        Integer idInt = id.intValue();
        return repository.findById(idInt);
    }

    @Override
    public Mono<ServiceTrans> findCurrentServiceDriver(String dni) throws Exception {
        return null;
    }

    @Override
    public Mono<ServiceTrans> findCurrentServiceClient(String ruc) throws Exception {
        return null;
    }


}
