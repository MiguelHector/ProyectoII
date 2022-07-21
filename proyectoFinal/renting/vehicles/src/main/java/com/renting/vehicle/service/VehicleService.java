package com.renting.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renting.vehicle.model.Vehicle;
import com.renting.vehicle.repository.VehicleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class VehicleService implements IVehicleService {
    @Autowired
    VehicleRepository repository;

    @Override
    public Mono<Vehicle> create(Vehicle vehicle) throws Exception{
        String placa=vehicle.getPlaca();
        vehicle.setId(Long.valueOf(0));

        return repository.findAll().filter (v->v.getPlaca().equals(placa))
                .map(vf->{
                    return new Vehicle();
                })
                .defaultIfEmpty(vehicle).last()
               .map(vs->{
                   if(vs.getPlaca()==null)
                   {
                       try {
                           throw new Exception("Placa ya existe");
                       } catch (Exception e) {
                           throw new RuntimeException(e);
                       }
                   }
                   return vs;
               }).flatMap(veh->this.repository.save(veh));

    }

    @Override
    public Flux<Vehicle> findAll() {
        Flux<Vehicle> c=repository.findAll();

        return repository.findAll();
    }
    @Override
    public Mono<Vehicle> findById(Long id) {
        Integer idInt = id.intValue();
        return repository.findById(idInt);
    }

    @Override
    public Mono<Vehicle> findByPlaca(String placa)  throws Exception
    {
            Mono<Vehicle> vehicle = repository.findAll().filter
                    (c -> c.getPlaca().equals(placa)).last();
            return vehicle;
    }

    @Override
    public Mono<Vehicle> update(Long idClient, Mono<Vehicle> vehicle) throws Exception{
        Integer idInt = idClient.intValue();
        //Mono<Client> find = repository.findById(idInt);

        return repository.findById(idInt)
                .flatMap(elem -> vehicle.map(cliVeh -> {
                    elem.setId(idClient);
                    elem.setPlaca(cliVeh.getPlaca());
                    elem.setDescriptionVehicle(cliVeh.getDescriptionVehicle());
                    elem.setDatePurchase(cliVeh.getDatePurchase());
                    elem.setStatus(cliVeh.getStatus());
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
