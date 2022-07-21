package com.renting.serviceLog.service;

import com.renting.serviceLog.model.Driver;
import com.renting.serviceLog.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DriverService implements IDriverService{
    @Autowired
    DriverRepository repository;

    @Override
    public Flux<Driver> findAll() {
        Flux<Driver> c=repository.findAll();

        return repository.findAll();
    }
    @Override
    public Mono<Driver> findById(Long id) {
        Integer idInt = id.intValue();
        return repository.findById(idInt);
    }

    @Override
    public Mono<Driver> findByDni(String dni)  throws Exception
    {
        Mono<Driver> driver = repository.findAll().filter
                (c -> c.getDni().equals(dni)).last();
        return driver;
    }

}
