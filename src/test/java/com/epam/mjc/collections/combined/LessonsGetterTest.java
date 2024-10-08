package com.epam.mjc.collections.combined;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LessonsGetterTest {

    public static Stream<Arguments> testCases() {
        return Stream.of(
                // Пустое расписание
                Arguments.of("EmptyTimetable",
                        Map.of(),
                        Set.of()),

                // Расписание без уроков (все дни пустые)
                Arguments.of("TimetableWithoutLessons",
                        Map.of("Monday", List.of(),
                                "Tuesday", List.of(),
                                "Wednesday", List.of(),
                                "Thursday", List.of(),
                                "Friday", List.of()),
                        Set.of()),

                // Расписание с уроками (уникальные предметы)
                Arguments.of("TimetableWithLessons",
                        Map.of("Monday", List.of("English", "Mathematics", "Chemistry"),
                                "Tuesday", List.of("Biology", "Informatics"),
                                "Wednesday", List.of("Geography", "Astronomy", "English"),
                                "Thursday", List.of("Mathematics", "Literature"),
                                "Friday", List.of("Physics", "Technology", "English")),
                        Set.of("Biology", "English", "Physics", "Astronomy", "Chemistry", "Geography", "Literature", "Technology", "Informatics", "Mathematics")),

                // Предметы с разным регистром
                Arguments.of("TimetableWithDifferentCaseLessons",
                        Map.of("Monday", List.of("English", "mathematics", "chemistry"),
                                "Tuesday", List.of("Biology", "informatics"),
                                "Wednesday", List.of("Geography", "astronomy", "english"),
                                "Thursday", List.of("Mathematics", "literature"),
                                "Friday", List.of("physics", "Technology", "english")),
                        Set.of("Biology", "English", "mathematics", "physics", "astronomy", "chemistry", "Geography", "literature", "Technology", "informatics", "Mathematics")),

                // Расписание с дубликатами уроков на разных днях
                Arguments.of("TimetableWithDuplicateLessons",
                        Map.of("Monday", List.of("English", "Mathematics", "Chemistry"),
                                "Tuesday", List.of("English", "Mathematics", "Chemistry"),
                                "Wednesday", List.of("English", "Mathematics", "Chemistry")),
                        Set.of("English", "Mathematics", "Chemistry"))
        );
    }

    @ParameterizedTest(name = "getLessons_{0}_Test")
    @MethodSource(value = "testCases")
    void getLessonsTest(String name, Map<String, List<String>> timetable, Set<String> expectedLessons) {

        LessonsGetter lessonsGetter = new LessonsGetter();
        Set<String> actualLessons = lessonsGetter.getLessons(timetable);
        assertEquals(expectedLessons, actualLessons);
    }
}
