package com.renting.serviceLog.controller;

import com.renting.serviceLog.model.ServiceLog;
import com.renting.serviceLog.service.IServServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renting.serviceLog.model.ServiceTrans;
import com.renting.serviceLog.service.IServService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("renting/serviceLog")
public class ServiceLogController {
    @Autowired
    private IServServiceLog serviceLog;

    @GetMapping(value = "/get/all", produces = { "application/json" })
    @ResponseBody
    public Flux<ServiceLog> findAll() {
        return serviceLog.findAll();
    }

    @GetMapping(value = "/getId/{id}", produces = { "application/json" })
    @ResponseBody
    public Mono<ServiceLog>  findById(@PathVariable("id") Long id) {
        return serviceLog.findById(id);
    }

    /*
    @GetMapping(value = "/getDocument/{dni}", produces = { "application/json" })
    @ResponseBody
    public Mono<ServiceTrans>  findByDocument(@PathVariable("dni") String dni) throws Exception {
        return service.findByDni(dni);
    }
    */
    @PostMapping(path ="/create",produces = { "application/json" })
    public ResponseEntity<Object>  create(@RequestBody ServiceLog servLog) throws Exception {
        try {
            Mono<ServiceLog> response = serviceLog.create(servLog);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping(path = { "/update/{idLog}" }, produces = { "application/json" })
    public ResponseEntity<Object> update(@PathVariable("idLog") Long idLog,
                                        @RequestBody Mono<ServiceLog> serv) throws Exception {
        try {
            Mono<ServiceLog> response = serviceLog.update(idLog, serv);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping(path = { "delete/{id}" })
    public String deleteById(@PathVariable("id") Long id) throws Exception {
        serviceLog.delete ( id);
        return "Eliminado";
    }
}
