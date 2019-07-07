package com.gmail.salahub.nikolay.logs_reader_app.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileWriterThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(FileWriterThread.class);

    List<String> stringList;

    public FileWriterThread(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public void run() {
        File file = new File("result");
        Path filePath = Paths.get(file.getName());
        try {
            Files.write(filePath, stringList);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
