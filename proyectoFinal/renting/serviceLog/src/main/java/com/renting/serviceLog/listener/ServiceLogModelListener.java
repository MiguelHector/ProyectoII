package com.renting.serviceLog.listener;

import com.renting.serviceLog.model.ServiceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.renting.serviceLog.service.ISequenceGeneratorService;

import java.util.concurrent.ExecutionException;

@Component
public class ServiceLogModelListener extends AbstractMongoEventListener<ServiceLog> {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ServiceLogModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ServiceLog> event) {
        try {
            if(event.getSource().getId()==0)
                event.getSource().setId(sequenceGenerator.generateSequence(ServiceLog.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}