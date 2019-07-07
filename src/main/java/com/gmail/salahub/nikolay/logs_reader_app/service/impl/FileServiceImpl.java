package com.gmail.salahub.nikolay.logs_reader_app.service.impl;

import com.gmail.salahub.nikolay.logs_reader_app.properties.ApplicationProperties;
import com.gmail.salahub.nikolay.logs_reader_app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class FileServiceImpl implements FileService {

    private final ApplicationProperties applicationProperties;

    @Autowired
    public FileServiceImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public File[] getListFilesFromDirectory() throws URISyntaxException {
        URL directoryPath = getUrlDirectory(applicationProperties.getNameDirectoryWithLogs());
        return new File(directoryPath.toURI())
                .listFiles();
    }

    private URL getUrlDirectory(String resource) {
        URL directoryUrl = getClass()
                .getClassLoader()
                .getResource(resource);
        return directoryUrl;
    }
}
