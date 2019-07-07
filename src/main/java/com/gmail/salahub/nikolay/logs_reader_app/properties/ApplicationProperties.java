package com.gmail.salahub.nikolay.logs_reader_app.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("applicationProperties")
public class ApplicationProperties {

    @Value("${task.files.directory}")
    private String nameDirectoryWithLogs;

    public String getNameDirectoryWithLogs() {
        return nameDirectoryWithLogs;
    }
}
