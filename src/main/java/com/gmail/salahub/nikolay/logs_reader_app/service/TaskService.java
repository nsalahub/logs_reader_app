package com.gmail.salahub.nikolay.logs_reader_app.service;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public interface TaskService {
    void executeTask() throws URISyntaxException,
            ExecutionException, InterruptedException;
}
