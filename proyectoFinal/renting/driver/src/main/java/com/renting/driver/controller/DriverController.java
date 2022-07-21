package com.renting.driver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renting.driver.model.Driver;
import com.renting.driver.service.IDriverService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("renting/driver")
public class DriverController {
    @Autowired
    private IDriverService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Driver> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/getId/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Driver>  findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/getDocument/{dni}", produces = { "application/json" })
    @ResponseBody
    public Mono<Driver>  findByDocument(@PathVariable("dni") String dni) throws Exception {
        return service.findByDni(dni);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Driver driver) throws Exception {
        try {
            Mono<Driver> response = service.create(driver);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = { "/update/{idDriver}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(@PathVariable("idDriver") Long idDriver,
                                        @RequestBody Mono<Driver> driver) throws Exception {
        try {
            Mono<Driver> response = service.update(idDriver, driver);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.OK);
        }
    }

    @DeleteMapping(path = { "delete/{id}" })
    public String deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete ( id);
        return "Eliminado";
    }
}
