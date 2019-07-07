package com.gmail.salahub.nikolay.logs_reader_app.service.impl;

import com.gmail.salahub.nikolay.logs_reader_app.service.ActionArrayService;
import com.gmail.salahub.nikolay.logs_reader_app.service.FileService;
import com.gmail.salahub.nikolay.logs_reader_app.service.TaskService;
import com.gmail.salahub.nikolay.logs_reader_app.thread.FileReaderThread;
import com.gmail.salahub.nikolay.logs_reader_app.thread.FileWriterThread;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.gmail.salahub.nikolay.logs_reader_app.constant.ApplicationConstant.POSITION_DATE_IN_ARRAY_AFTER_SPLITTING;
import static com.gmail.salahub.nikolay.logs_reader_app.constant.ApplicationConstant.SPLITTING_DATA_IN_LOG_FILE_REGEX;
import static com.gmail.salahub.nikolay.logs_reader_app.constant.ApplicationConstant.VALUE_OF_THREADS_IN_EXECUTOR_SERVICE;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    private final FileService fileService;
    private final ActionArrayService actionArrayService;

    public TaskServiceImpl(FileService fileService, ActionArrayService actionArrayService) {
        this.actionArrayService = actionArrayService;
        this.fileService = fileService;
    }

    @Override
    public void executeTask(ConfigurableApplicationContext context) throws URISyntaxException,
            ExecutionException, InterruptedException {
        File[] files = fileService.getListFilesFromDirectory();

        ExecutorService executorServiceWriter = Executors.newFixedThreadPool(VALUE_OF_THREADS_IN_EXECUTOR_SERVICE);
        List<Future<List<String>>> futureList = new ArrayList<>();
        for (File file : files) {
            Callable<List<String>> readerThread = new FileReaderThread(file);
            Future<List<String>> nameOfFile = executorServiceWriter.submit(readerThread);
            futureList.add(nameOfFile);
        }

        List<String> resultList = actionArrayService.getListWithDateAndCount(
                actionArrayService.getListWithDateFromLinesList(
                        actionArrayService.getLinesFromFutureList(futureList)));

        Runnable runnable = new FileWriterThread(resultList);
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
