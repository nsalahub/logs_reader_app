package com.gmail.salahub.nikolay.logs_reader_app.service;

import java.io.File;
import java.net.URISyntaxException;

public interface FileService {

    File[] getListFilesFromDirectory() throws URISyntaxException;
}
