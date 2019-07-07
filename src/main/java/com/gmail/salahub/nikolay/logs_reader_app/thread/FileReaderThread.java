package com.gmail.salahub.nikolay.logs_reader_app.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FileReaderThread implements Callable<List<String>> {

    private static final Logger logger = LoggerFactory.getLogger(FileReaderThread.class);

    File file;

    public FileReaderThread(File file) {
        this.file = file;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> logsList = new ArrayList<>();
        Files.lines(file.toPath(), StandardCharsets.UTF_8)
                .forEach(line -> logsList.add(line));
        return logsList;
    }
}