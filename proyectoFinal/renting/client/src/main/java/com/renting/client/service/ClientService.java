package com.renting.client.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.client.model.Client;
import com.renting.client.repository.ClientRepository;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService{
    @Autowired
    ClientRepository repository;

    @Override
    public Mono<Client> create(Client client) throws Exception{
        String ruc=client.getRuc();
        client.setId(Long.valueOf(0));

        return repository.findAll().filter (v->v.getRuc().equals(ruc))
                .map(vf->{
                    return new Client();
                })
                .defaultIfEmpty(client).last()
                .map(vs->{
                    if(vs.getRuc()==null)
                    {
                        try {
                            throw new Exception("Ruc ya esta registrado");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return vs;
                }).flatMap(veh->this.repository.save(veh));
    }

    @Override
    public Flux<Client> findAll() {
        Flux<Client> c=repository.findAll();

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
        System.out.println(document);
            Mono<Client> client = repository.findAll().filter
                    (c -> c.getRuc().equals(document)).last();
            return client;
    }

    @Override
    public Mono<Client> update(Long idClient, Mono<Client> client) throws Exception{
        Integer idInt = idClient.intValue();
        //Mono<Client> find = repository.findById(idInt);

        return repository.findById(idInt)
                .flatMap(elem -> client.map(cliRuc -> {
                    elem.setId(idClient);
                    elem.setRuc(cliRuc.getRuc());
                    elem.setCompanyName(cliRuc.getCompanyName());
                    elem.setDepartment(cliRuc.getDepartment());
                    elem.setProvince(cliRuc.getProvince());
                    elem.setDistrict(cliRuc.getDistrict());
                    elem.setStatus(cliRuc.getStatus());
                    return elem;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    public void delete(Long idClient) {
        Integer idInt = idClient.intValue();

        this.repository
            .findById(idInt)
            .flatMap(p -> this.repository.delete(p));

    }


}
