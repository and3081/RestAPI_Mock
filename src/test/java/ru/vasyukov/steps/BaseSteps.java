package ru.vasyukov.steps;

import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.requestSpec;
import static ru.vasyukov.steps.Specification.responseSpecCreate;

public class BaseSteps {
    public static void initMockExpectation(String filename) {
        given()
                .spec(requestSpec())
                .body(Json.readBodyJson(filename).toString())
                .when()
                .put(TestData.mock.endpointExpect())
                .then()
                .log().all()
                .spec(responseSpecCreate());
    }
}
