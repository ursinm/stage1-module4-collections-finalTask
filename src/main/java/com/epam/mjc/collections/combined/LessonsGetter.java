package com.epam.mjc.collections.combined;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LessonsGetter {
    public Set<String> getLessons(Map<String, List<String>> timetable) {
        Set<String> uniqueLessons = new HashSet<>();
        
        // Проходим по каждому дню недели и добавляем уроки в множество
        for (List<String> lessons : timetable.values()) {
            uniqueLessons.addAll(lessons);
        }
        
        return uniqueLessons;
    }
}
