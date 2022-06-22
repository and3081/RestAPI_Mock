package ru.vasyukov.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.steps.Steps;

public class Tests {
    @DisplayName("Тестирование")
//    @ParameterizedTest(name = ".")
//    @MethodSource("ru.vasyukov.DataProvider#providerSelect")
    @Test
    public void test1() {
        Steps.initMock();
    }
}
