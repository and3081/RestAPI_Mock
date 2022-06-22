package ru.vasyukov.steps;

import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.requestSpec;
import static ru.vasyukov.steps.Specification.responseSpecCreate;

public class BaseSteps {
    public static void initMockExpectation(String expectation) {
        given()
                .spec(requestSpec())
                .body(expectation)
                .when()
                .put(TestData.mock.endpointExpect())
                .then()
                .log().all()
                .spec(responseSpecCreate());
    }
}
