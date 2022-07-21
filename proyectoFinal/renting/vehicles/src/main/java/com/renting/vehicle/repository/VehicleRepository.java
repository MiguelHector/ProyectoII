package com.renting.vehicle.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.renting.vehicle.model.Vehicle;

import reactor.core.publisher.Mono;

@Repository
public interface VehicleRepository extends ReactiveMongoRepository<Vehicle, Integer> {
    @Query("{'placa': ?0}")
    Mono<Vehicle> findByPlaca(String placa);

}
