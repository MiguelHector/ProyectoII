package com.renting.serviceTrans.service;

import com.renting.serviceTrans.model.Client;
import com.renting.serviceTrans.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import com.renting.serviceTrans.model.ServiceTrans;
import com.renting.serviceTrans.repository.ServiceRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceTranService implements IServiceTransService {
    @Autowired
    IClientService clientService;
    @Autowired
    IDriverService driverService;
    @Autowired
    ServiceRepository repository;

    @Override
    public Mono<ServiceTrans> create(ServiceTrans serviceTrans) throws Exception{
        String dni=serviceTrans.getDniDriver();
        String ruc=serviceTrans.getRuc();

        List<String> strValid = new ArrayList<>();
        strValid.add(new String("CLIENT"));
        strValid.add(new String("DRIVER"));

        serviceTrans.setId(Long.valueOf(0));

        return repository.findAll().filter (v->v.getDniDriver().equals(dni) && v.getStatus().equals("ACTIVO"))
                .map(vf->{
                    return new ServiceTrans();
                })
                .defaultIfEmpty(serviceTrans).last()
                .map(vs->{
                    if(vs.getDniDriver()==null)
                    {
                        try {
                            throw new Exception("Chofer con DNI " + dni + " aún esta asignado a un servicio activo");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return vs;
                }).thenMany(driverService.findAll()).filter(v->v.getDni().equals(dni))
                            .map(vf->{return vf;})
                            .defaultIfEmpty(new Driver()).last()
                            .map(vs->{
                                if(vs.getDni()==null)
                                {
                                    try {
                                        throw new Exception("No existe chofer con DNI " + dni + " registrado");
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                return vs;
                            })
                            .flatMap(f->this.repository.save(serviceTrans));
    }

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

    @Override
    public Mono<ServiceTrans> update(Long idService, Mono<ServiceTrans> service) throws Exception{
        Integer idInt = idService.intValue();
        //Mono<Client> find = repository.findById(idInt);

        return repository.findById(idInt)
                .flatMap(elem -> service.map(serv -> {
                    elem.setId(idService);
                    elem.setDniDriver(serv.getDniDriver());
                    elem.setRuc(serv.getRuc());
                    elem.setDateExit(serv.getDateExit());
                    elem.setDateReturn(serv.getDateReturn());
                    elem.setDestinity(serv.getDestinity());
                    elem.setCostService(serv.getCostService());
                    elem.setFeeDriver(serv.getFeeDriver());
                    elem.setStatus(serv.getStatus());
                    return elem;
                }))
                .flatMap(p -> this.repository.save(p));

    }

    @Override
    public void delete(Long idService) {
        Integer idInt = idService.intValue();

        this.repository
                .findById(idInt)
                .flatMap(p -> this.repository.delete(p));
    }
}
