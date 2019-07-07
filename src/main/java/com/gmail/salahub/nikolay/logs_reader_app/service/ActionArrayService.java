package com.gmail.salahub.nikolay.logs_reader_app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface ActionArrayService {

    List<String> getListWithDateAndCount(List<String> dates);

    List<String> getLinesFromFutureList(List<Future<List<String>>> futureList) throws ExecutionException, InterruptedException;

    List<String> getListWithDateFromLinesList(List<String> lines);
}
