package com.renting.driver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.driver.model.Driver;
import com.renting.driver.repository.DriverRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DriverService implements IDriverService {
    @Autowired
    DriverRepository repository;

    @Override
    public Mono<Driver> create(Driver driver) throws Exception{
        String dni=driver.getDni();
        driver.setId(Long.valueOf(0));

        return repository.findAll().filter (v->v.getDni().equals(dni))
                .map(vf->{
                    return new Driver();
                })
                .defaultIfEmpty(driver).last()
               .map(vs->{
                   if(vs.getDni()==null)
                   {
                       try {
                           throw new Exception("DNI ya esta regisgtrasdo");
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   }
                   return vs;
               }).flatMap(veh->this.repository.save(veh));
    }

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

    @Override
    public Mono<Driver> update(Long idDriver, Mono<Driver> driver) throws Exception{
        Integer idInt = idDriver.intValue();
        //Mono<Client> find = repository.findById(idInt);

        return repository.findById(idInt)
                .flatMap(elem -> driver.map(cliDri -> {
                    elem.setId(idDriver);
                    elem.setDni(cliDri.getDni());
                    elem.setNames(cliDri.getNames());
                    elem.setLastName(cliDri.getLastName());
                    elem.setLicense(cliDri.getLicense());
                    elem.setCategory(cliDri.getCategory());
                    elem.setDateBirth(cliDri.getDateBirth());
                    elem.setStatus(cliDri.getStatus());
                    return elem;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    public void delete(Long idDriver) {
        Integer idInt = idDriver.intValue();

        this.repository
                .findById(idInt)
                .flatMap(p -> this.repository.delete(p));

    }
}
