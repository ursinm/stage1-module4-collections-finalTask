package com.epam.mjc.collections.combined;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapFromKeysCreatorTest {

    public static Stream<Arguments> testCases() {
        return Stream.of(
                // Пустая карта
                Arguments.of("EmptySourceMap",
                        Map.of(),
                        Map.of()),

                // Карта с разными длинами ключей
                Arguments.of("FilledSourceMap",
                        Map.of("Monday", 1,
                                "Tuesday", 2,
                                "Wednesday", 3,
                                "Thursday", 4,
                                "Friday", 5),
                        Map.of(6, Set.of("Monday", "Friday"),
                                7, Set.of("Tuesday"),
                                8, Set.of("Thursday"),
                                9, Set.of("Wednesday"))),

                // Карта с ключами одинаковой длины
                Arguments.of("SameLengthKeys",
                        Map.of("cat", 1,
                                "dog", 2,
                                "bat", 3),
                        Map.of(3, Set.of("cat", "dog", "bat"))),

                // Карта с одним значением
                Arguments.of("SingleEntry",
                        Map.of("apple", 1),
                        Map.of(5, Set.of("apple"))),

                // Карта с ключами, содержащими пробелы и специальные символы
                Arguments.of("KeysWithSpacesAndSymbols",
                        Map.of("hello world", 1,
                                "foo-bar", 2,
                                "space key", 3),
                        Map.of(11, Set.of("hello world", "space key"),
                                7, Set.of("foo-bar")))
        );
    }

    @ParameterizedTest(name = "createMap_{0}_Test")
    @MethodSource(value = "testCases")
    void createMapTest(String name, Map<String, Integer> sourceMap, Map<Integer, Set<String>> expectedMapFromKeys) {

        MapFromKeysCreator creator = new MapFromKeysCreator();
        Map<Integer, Set<String>> actualMapFromKeys = creator.createMap(sourceMap);
        assertEquals(expectedMapFromKeys, actualMapFromKeys);
    }
}
