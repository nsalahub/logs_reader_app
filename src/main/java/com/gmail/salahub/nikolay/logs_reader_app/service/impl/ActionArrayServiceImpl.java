package com.gmail.salahub.nikolay.logs_reader_app.service.impl;

import com.gmail.salahub.nikolay.logs_reader_app.service.ActionArrayService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.gmail.salahub.nikolay.logs_reader_app.constant.ApplicationConstant.POSITION_DATE_IN_ARRAY_AFTER_SPLITTING;
import static com.gmail.salahub.nikolay.logs_reader_app.constant.ApplicationConstant.SPLITTING_DATA_IN_LOG_FILE_REGEX;

@Service("actionArrayService")
public class ActionArrayServiceImpl implements ActionArrayService {
    @Override
    public List<String> getListWithDateAndCount(List<String> dates) {
        Map<String, Integer> repeatableDateMap = new HashMap<String, Integer>();
        for (String str : dates) {
            if (repeatableDateMap.containsKey(str)) {
                repeatableDateMap.put(str, repeatableDateMap.get(str) + 1);
            } else {
                repeatableDateMap.put(str, 1);
            }
        }

        return getListForRecording(repeatableDateMap);
    }

    @Override
    public List<String> getLinesFromFutureList(List<Future<List<String>>> futureList) throws ExecutionException,
            InterruptedException {
        List<String> lines = new ArrayList<>();
        for (Future<List<String>> future : futureList) {
            for (String string : future.get()) {
                lines.add(string);
            }
        }
        return lines;
    }

    @Override
    public List<String> getListWithDateFromLinesList(List<String> lines) {
        List<String> listWithDate = new ArrayList<>();
        for (String line : lines) {
            String[] splitArray = line.split(SPLITTING_DATA_IN_LOG_FILE_REGEX);
            listWithDate.add(splitArray[POSITION_DATE_IN_ARRAY_AFTER_SPLITTING]);
        }
        return listWithDate;
    }

    private List<String> getListForRecording(Map<String, Integer> repeatableDateMap) {
        List<String> resultList = new ArrayList<>();
        Set<Map.Entry<String, Integer>> entrySet = repeatableDateMap.entrySet();

        for (Integer repeat : repeatableDateMap.values()) {
            for (Map.Entry<String, Integer> pair : entrySet) {
                if (repeat.equals(pair.getValue())) {
                    resultList.add(pair.getKey() + " " + repeat);
                }
            }
        }
        return resultList;
    }
}
