package com.renting.serviceLog.service;

import java.util.concurrent.ExecutionException;

public interface ISequenceGeneratorService {
    long generateSequence(final String sequenceName) throws InterruptedException, ExecutionException;
}
