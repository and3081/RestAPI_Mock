package ru.vasyukov.hooks;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.*;

/**
 * Класс хуков
 */
public class Hooks {
    /**
     * Reset MockServer
     */
    @Step("Reset MockServer")
    @BeforeAll
    public static void resetMock() {
        given()
                .spec(requestSpec())
                .when()
                .put(TestData.mock.endpointReset())
                .then()
                .log().all()
                .spec(responseSpecOk());
    }
}
