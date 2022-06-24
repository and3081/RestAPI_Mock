package ru.vasyukov.tests;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Класс провайдера данных для тестов
 */
@SuppressWarnings("unused")
public class DataProvider {
    /**
     * Метод-провайдер для тест-кейса testPhoto123()
     * @return  стрим аргументов:
     */
    protected static Stream<Arguments> providerPhoto123() {
        return Stream.of(arguments("src/test/resources/expectBodyPhoto123.json",
                "/api/photos/cats/123/photos",
                200, 1, "test.jpg"));
    }

    protected static Stream<Arguments> providerPhoto456() {
        return Stream.of(arguments("/api/photos/cats/456/photos",
                400, "Wrong request"));
    }

    protected static Stream<Arguments> providerIdParams() {
        return Stream.of(arguments("/api/core/cats/get-by-id",
                "123", "456", 200, 201));
    }
}
