package com.epam.mjc.collections.combined;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeveloperProjectFinder {
    public List<String> findDeveloperProject(Map<String, Set<String>> projects, String developer) {
        List<String> developerProjects = new ArrayList<>();
        
        // Проходим по каждому проекту и проверяем, является ли разработчик участником
        for (Map.Entry<String, Set<String>> entry : projects.entrySet()) {
            if (entry.getValue().contains(developer)) {
                developerProjects.add(entry.getKey());
            }
        }
        
        // Сортируем по убыванию длины названия проекта, если длины одинаковые — по алфавиту в обратном порядке
        developerProjects.sort((a, b) -> {
            int lengthComparison = Integer.compare(b.length(), a.length());
            return lengthComparison != 0 ? lengthComparison : b.compareTo(a);
        });
        
        return developerProjects;
    }
}
