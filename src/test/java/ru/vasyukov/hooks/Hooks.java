package ru.vasyukov.hooks;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
        try {
            given()
                    .spec(requestSpec())
                    .when()
                    .put(TestData.mock.endpointReset())
                    .then()
                    //.log().all()
                    .spec(responseSpecOk());
        } catch (Exception e) {
            Assertions.fail("Ошибка соединения с MockServer\n" + e);
        }
    }
}
