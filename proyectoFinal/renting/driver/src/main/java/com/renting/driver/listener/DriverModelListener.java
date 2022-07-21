package com.renting.driver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.renting.driver.model.Driver;
import com.renting.driver.service.ISequenceGeneratorService;

import java.util.concurrent.ExecutionException;

@Component
public class DriverModelListener extends AbstractMongoEventListener<Driver> {
    private static final Logger logger = LoggerFactory.getLogger(DriverModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public DriverModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Driver> event) {
        try {
            if(event.getSource().getId()==0)
                event.getSource().setId(sequenceGenerator.generateSequence(Driver.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}