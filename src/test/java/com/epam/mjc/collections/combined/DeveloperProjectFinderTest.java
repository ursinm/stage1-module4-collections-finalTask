package com.epam.mjc.collections.combined;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeveloperProjectFinderTest {

    public static Stream<Arguments> testCases() {
        return Stream.of(
                // Пустая карта проектов
                Arguments.of("EmptyProjectsMap",
                        Map.of(),
                        "Robert",
                        List.of()),

                // Разработчик не участвует ни в одном проекте
                Arguments.of("NoProjects",
                        Map.of("AXE", Set.of("Helen", "Bob", "Marian", "Tom"),
                                "MazPay", Set.of("James", "John"),
                                "SeaV", Set.of("John", "Angelina", "David"),
                                "DelFo", Set.of("Patricia", "Jessica", "Mark", "Paul"),
                                "ReaNon", Set.of("George", "Kyle", "Brandon", "Austin")),
                        "Robert",
                        List.of()),

                // Разработчик участвует в нескольких проектах
                Arguments.of("HaveProjects",
                        Map.of("AXE", Set.of("Helen", "Bob", "Marian", "Tom"),
                                "MazPay", Set.of("James", "John", "Robert"),
                                "SeaV", Set.of("John", "Robert", "Angelina", "David"),
                                "DelFo", Set.of("Patricia", "Jessica", "Mark", "Paul"),
                                "ReaNon", Set.of("George", "Kyle", "Robert", "Brandon", "Austin")),
                        "Robert",
                        List.of("ReaNon", "MazPay", "SeaV")),

                // Несколько проектов с одинаковой длиной имени
                Arguments.of("ProjectsWithSameLength",
                        Map.of("AXE", Set.of("Ivan"),
                                "BEE", Set.of("Ivan"),
                                "SEE", Set.of("Ivan"),
                                "ZEE", Set.of("Ivan")),
                        "Ivan",
                        List.of("ZEE", "SEE", "BEE", "AXE")),

                // Разработчик указан в одном проекте несколько раз
                Arguments.of("DeveloperListedMultipleTimes",
                        Map.of("AXE", Set.of("Ivan", "Ivan"),
                                "BEE", Set.of("Ivan", "Alex"),
                                "SEE", Set.of("Ivan")),
                        "Ivan",
                        List.of("SEE", "BEE", "AXE"))
        );
    }

    @ParameterizedTest(name = "findDeveloperProject_{0}_Test")
    @MethodSource(value = "testCases")
    void findDeveloperProjectTest(String name,
                                  Map<String, Set<String>> projects,
                                  String developer,
                                  List<String> expectedDeveloperProjects) {

        DeveloperProjectFinder finder = new DeveloperProjectFinder();
        List<String> actualDeveloperProjects = finder.findDeveloperProject(projects, developer);
        assertEquals(expectedDeveloperProjects, actualDeveloperProjects);
    }
}
