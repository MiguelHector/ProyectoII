package com.renting.client.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.renting.client.model.Client;
import com.renting.client.service.ISequenceGeneratorService;

import java.util.concurrent.ExecutionException;

@Component
public class ClientModelListener extends AbstractMongoEventListener<Client> {
    private static final Logger logger = LoggerFactory.getLogger(ClientModelListener.class);

    private ISequenceGeneratorService sequenceGenerator;

    @Autowired
    public ClientModelListener(ISequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Client> event) {
        try {
            if(event.getSource().getId()==0)
                event.getSource().setId(sequenceGenerator.generateSequence(Client.SEQUENCE_NAME));
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error:{}", e.getMessage());
        }
    }
}