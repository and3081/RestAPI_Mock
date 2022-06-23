package ru.vasyukov.steps;

import io.qameta.allure.restassured.AllureRestAssured;

/**
 * Листенер RestAssured для запросов и ответов
 */
public class MyListener {
    public static AllureRestAssured myListener() {
        return new AllureRestAssured();
    }
}
