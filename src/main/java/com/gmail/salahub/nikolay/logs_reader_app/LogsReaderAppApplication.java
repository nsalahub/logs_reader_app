package com.gmail.salahub.nikolay.logs_reader_app;

import com.gmail.salahub.nikolay.logs_reader_app.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class LogsReaderAppApplication {

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(LogsReaderAppApplication.class, args);
        TaskService taskService = context.getBean(TaskService.class);
        taskService.executeTask(context);
    }


}
