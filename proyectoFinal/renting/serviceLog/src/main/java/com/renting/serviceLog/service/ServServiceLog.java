package com.renting.serviceLog.service;

import com.renting.serviceLog.model.ServiceLog;
import com.renting.serviceLog.repository.ServiceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServServiceLog implements IServServiceLog {
    @Autowired
    ServiceLogRepository repository;

    @Override
    public Mono<ServiceLog> create(ServiceLog servicelog) throws Exception {
        Long idTrans=Long.valueOf(servicelog.getIdServTrans());

        servicelog.setId(Long.valueOf(0));

        return this.repository.save(servicelog);
        /*
        return repository.findAll().filter (v->v.getIdServTrans().equals(idTrans))
                .map(vf->{
                    return servicelog;
                })
                .defaultIfEmpty(new ServiceLog()).last()
                .map(vs->{
                    if(vs.getIdServTrans()==null)
                    {
                        try {
                            throw new Exception("Id de servicio " + String.valueOf(idTrans) + " no existe");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return vs;
                })
                .flatMap(f->this.repository.save(servicelog));*/
    }

    @Override
    public Flux<ServiceLog> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<ServiceLog> findById(Long id) {
        Integer idInt = id.intValue();
        return repository.findById(idInt);
    }

    @Override
    public Mono<ServiceLog> findCurrentServiceDriver(String dni) throws Exception {
        return null;
    }

    @Override
    public Mono<ServiceLog> findCurrentServiceClient(String ruc) throws Exception {
        return null;
    }

    @Override
    public Mono<ServiceLog> findCurrentServiceVehicle(String placa) throws Exception {
        return null;
    }

    @Override
    public Mono<ServiceLog> update(Long idService, Mono<ServiceLog> serviceLog) throws Exception {
        Integer idInt = idService.intValue();
        //Mono<Client> find = repository.findById(idInt);

        return repository.findById(idInt)
                .flatMap(elem -> serviceLog.map(serv -> {
                    elem.setId(idService);
                    elem.setIdServTrans(serv.getIdServTrans());
                    elem.setTypeLog(serv.getTypeLog());
                    elem.setDateLog(serv.getDateLog());
                    elem.setKm(serv.getKm());
                    elem.setObservation(serv.getObservation());
                    return elem;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    @Override
    public void delete(Long id) {

        Integer idInt = id.intValue();

        this.repository
                .findById(idInt)
                .flatMap(p -> this.repository.delete(p));
    }
}
