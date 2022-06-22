package ru.vasyukov.steps;

import io.qameta.allure.Step;
import ru.vasyukov.data.Expectations;

public class Steps extends BaseSteps {
    @Step("init Mock Photo 123")
    public static void initMockPhoto123() {
        initMockExpectation(Expectations.EXPECT_Photo123);
    }

    @Step("init Mock Photo 456")
    public static void initMockPhoto456() {
        initMockExpectation(Expectations.EXPECT_Photo456);
    }
}
