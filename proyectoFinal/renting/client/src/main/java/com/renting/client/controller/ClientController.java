package com.renting.client.controller;

import org.bson.BsonInt64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renting.client.model.Client;
import com.renting.client.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("renting/client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping("/get-info")
    public  String getStringMessage()
    {
        return "Hola";
    }

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<Client> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/getId/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<Client>  findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/getDocument/{ruc}", produces = { "application/json" })
    @ResponseBody
    public Mono<Client>  findByDocument(@PathVariable("ruc") String ruc) throws Exception {
        return service.findByDocument(ruc);
    }

    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody Client client) throws Exception {
        try {
            Mono<Client> response = service.create(client);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping(path = { "/update/{idClient}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(@PathVariable("idClient") Long idClient,
                                        @RequestBody Mono<Client> client) throws Exception {
        try {
            Mono<Client> response = service.update(idClient, client);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = { "delete/{id}" })
    public String deleteById(@PathVariable("id") Long id) throws Exception {
        service.delete (id);
        return "Eliminado";
    }
}
