package com.renting.serviceTrans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renting.serviceTrans.model.ServiceTrans;
import com.renting.serviceTrans.service.IServiceTransService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("renting/service")
public class ServiceController {
    @Autowired
    private IServiceTransService service;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ServiceTrans> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/getId/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<ServiceTrans>  findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    /*
    @GetMapping(value = "/getDocument/{dni}", produces = { "application/json" })
    @ResponseBody
    public Mono<ServiceTrans>  findByDocument(@PathVariable("dni") String dni) throws Exception {
        return service.findByDni(dni);
    }
    */
    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody ServiceTrans serv) throws Exception {
        try {
            Mono<ServiceTrans> response = service.create(serv);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping(path = { "/update/{id}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(@PathVariable("id") Long id,
                                        @RequestBody Mono<ServiceTrans> serv) throws Exception {
        try {
            Mono<ServiceTrans> response = service.update(id, serv);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = { "delete/{id}" })
    public String deleteById(@PathVariable("id") Long id) throws Exception {

        service.delete ( id);
        return "Eliminado";
    }
}
