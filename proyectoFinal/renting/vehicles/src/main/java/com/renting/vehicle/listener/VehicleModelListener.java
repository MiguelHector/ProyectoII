package com.renting.vehicle.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.renting.vehicle.model.Vehicle;
import com.renting.vehicle.service.ISequenceGeneratorService;

import java.util.concurrent.ExecutionException;

@Component
public class VehicleModelListener extends AbstractMongoEventListener<Vehicle> {
    private static final Logger logger = LoggerFactory.getLogger(VehicleModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public VehicleModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Vehicle> event) {
        try {
            if(event.getSource().getId()==0)
                event.getSource().setId(sequenceGenerator.generateSequence(Vehicle.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}