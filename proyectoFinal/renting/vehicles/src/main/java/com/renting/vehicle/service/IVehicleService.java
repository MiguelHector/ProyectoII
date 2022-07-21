package com.renting.vehicle.service;

import com.renting.vehicle.model.Vehicle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVehicleService {
    Mono<Vehicle> create(Vehicle vehicle) throws Exception;

    Flux<Vehicle> findAll();

    Mono<Vehicle> findById(Long id);

    Mono<Vehicle> findByPlaca(String placa) throws Exception;

    Mono<Vehicle> update(Long idVehicle, Mono<Vehicle> vehicle) throws Exception;

    void delete(Long id);

}


