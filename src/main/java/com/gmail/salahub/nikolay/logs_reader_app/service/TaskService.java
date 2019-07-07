package com.gmail.salahub.nikolay.logs_reader_app.service;

import org.springframework.context.ConfigurableApplicationContext;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public interface TaskService {
    void executeTask(ConfigurableApplicationContext context) throws URISyntaxException,
            ExecutionException, InterruptedException;
}
