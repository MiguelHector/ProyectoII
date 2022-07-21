package com.renting.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renting.vehicle.model.Vehicle;
import com.renting.vehicle.service.IVehicleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("renting/vehicle")
public class VehicleController {
    @Autowired
    private IVehicleService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Vehicle> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/getId/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Vehicle>  findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/getDocument/{placa}", produces = { "application/json" })
    @ResponseBody
    public Mono<Vehicle>  findByDocument(@PathVariable("placa") String placa) throws Exception {
        return service.findByPlaca(placa);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Vehicle vehicle) throws Exception {
        try {
            Mono<Vehicle> response = service.create(vehicle);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.toString(), HttpStatus.OK);
        }
    }

    @PutMapping(path = { "/update/{idVehicle}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(@PathVariable("idVehicle") Long idVehicle,
                                        @RequestBody Mono<Vehicle> vehicle) throws Exception {
        try {
            Mono<Vehicle> response = service.update(idVehicle, vehicle);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.toString(), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = { "delete/{id}" })
    public String deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete ( id);
        return "Eliminado";
    }
}
