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
     * Метод-провайдер для тест-кейса testDb()
     * @return  стрим аргументов:

     */
    protected static Stream<Arguments> providerPhoto123() {
        return Stream.of(arguments("src/test/resources/expectPhoto123.json"));
    }

    protected static Stream<Arguments> providerPhoto456() {
        return Stream.of(arguments("src/test/resources/expectPhoto456.json"));
    }
}
