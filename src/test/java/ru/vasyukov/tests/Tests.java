package ru.vasyukov.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.hooks.Hooks;
import ru.vasyukov.steps.Specification;
import ru.vasyukov.steps.Steps;

public class Tests extends Hooks {
    @DisplayName("Тестирование Photo123 status 200")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto123")
    public void testPhoto123(String filename, String endpoint,
                             int statusCode, int length, String itemName) {
        Steps.initMockPhoto123(filename, endpoint, statusCode);
//        Steps.requestMock(endpoint, Specification.responseSpecPhoto123(statusCode, length, itemName));
        Steps.requestMock("/api/core/cats/get-by-id?id=1", Specification.responseSpecPhoto(statusCode));
    }

//    @DisplayName("Тестирование Photo456 status 400")
//    @ParameterizedTest(name = "{arguments}")
//    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto456")
//    public void testPhoto456(String endpoint, int statusCode, String statusLine) {
//        Steps.initMockPhoto456(endpoint, statusCode, statusLine);
//        Steps.requestMock(endpoint, Specification.responseSpecPhoto456(statusCode, statusLine));
//    }
}
