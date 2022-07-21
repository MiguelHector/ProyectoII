package com.renting.serviceTrans.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.renting.serviceTrans.model.ServiceTrans;
import com.renting.serviceTrans.service.ISequenceGeneratorService;

import java.util.concurrent.ExecutionException;

@Component
public class ServiceModelListener extends AbstractMongoEventListener<ServiceTrans> {
    private static final Logger logger = LoggerFactory.getLogger(ServiceModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ServiceModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ServiceTrans> event) {
        try {
            if(event.getSource().getId()==0)
                event.getSource().setId(sequenceGenerator.generateSequence(ServiceTrans.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}