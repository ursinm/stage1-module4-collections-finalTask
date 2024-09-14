package com.epam.mjc.collections.combined;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapFromKeysCreator {
    public Map<Integer, Set<String>> createMap(Map<String, Integer> sourceMap) {
        Map<Integer, Set<String>> resultMap = new HashMap<>();
        
        // Проходим по каждой записи исходной карты
        for (String key : sourceMap.keySet()) {
            int length = key.length();
            
            // Если такой длины еще нет в карте, добавляем новое множество
            resultMap.putIfAbsent(length, new HashSet<>());
            
            // Добавляем строку в соответствующее множество
            resultMap.get(length).add(key);
        }
        
        return resultMap;
    }
}
