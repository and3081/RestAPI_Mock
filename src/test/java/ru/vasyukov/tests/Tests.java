package ru.vasyukov.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.hooks.Hooks;
import ru.vasyukov.steps.Steps;

public class Tests extends Hooks {
    @DisplayName("Тестирование Photo123 status 200")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto123")
    public void testPhoto123(String filename) {
        Steps.initMock(filename);
    }

    @DisplayName("Тестирование Photo456 status 400")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.tests.DataProvider#providerPhoto456")
    public void testPhoto456(String filename) {
        Steps.initMock(filename);
    }
}
