package ru.vasyukov.steps;

import io.qameta.allure.Step;

public class Steps extends BaseSteps {
    @Step("init Mock {filename}")
    public static void initMock(String filename) {
        initMockExpectation(filename);
    }

}
