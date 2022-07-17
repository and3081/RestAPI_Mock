package ru.vasyukov.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.hooks.Hooks;
import ru.vasyukov.steps.Specification;
import ru.vasyukov.steps.Steps;

/**
 * Тестирование RestAssured + MockServer
 */
public class Tests extends Hooks {
    @DisplayName("Тестирование Photo123 status 200")
    @Tag("1")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto123")
    public void testPhoto123(String filename, String endpoint,
                             int statusCode, int length, String itemName) {
        Steps.initMockPhoto123(filename, endpoint, statusCode);
        Steps.requestMock(endpoint, Specification.responseSpecPhoto123(statusCode, length, itemName));
    }

    @DisplayName("Тестирование Photo456 status 400")
    @Tag("2")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto456")
    public void testPhoto456(String endpoint, int statusCode, String statusLine) {
        Steps.initMockPhoto456(endpoint, statusCode, statusLine);
        Steps.requestMock(endpoint, Specification.responseSpecPhoto456(statusCode, statusLine));
    }

    @DisplayName("Тестирование IdParams '123','456','9999' status 200,201,400")
    @Tag("3")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerIdParams")
    public void testIdParams(String endpoint,
                             String id1, int statusCode1,
                             String id2, int statusCode2,
                             String id3, int statusCode3) {
        Steps.initMockIdParams(endpoint, id1, id2, statusCode1, statusCode2, statusCode3);
        Steps.requestMockParams(endpoint, id1, Specification.responseSpecIdParams(statusCode1, id1));
        Steps.requestMockParams(endpoint, id2, Specification.responseSpecIdParams(statusCode2, id2));
        Steps.requestMockParams(endpoint, id3, Specification.responseSpecIdParamsWrong(statusCode3, id3));
    }
}
