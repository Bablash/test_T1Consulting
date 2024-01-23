package com.example.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Override
    public String getResult(String input) {
        Map<String, Integer> resultMap = new HashMap<>();

        if(input == null)
            return resultMap.toString();

        calculateCountOfRepeating(input, resultMap);

        Map<String, Integer> sortedMapDesc = sortMapDesc(resultMap);

        return sortedMapDesc.toString();
    }

    private void calculateCountOfRepeating(String input, Map<String, Integer> output) {
        String[] splitInput = input.replace(" ", "").split("");

        if (splitInput[0].isEmpty()) {
            return;
        }

        for (int i = 0; i < splitInput.length; i++) {
            int count = 1;

            for (int j = i + 1; j < splitInput.length; j++) {
                if (splitInput[i].equals(splitInput[j])) {
                    count++;
                }
            }
            output.putIfAbsent(splitInput[i], count);
        }
    }

    private Map<String, Integer> sortMapDesc(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));
    }
}
