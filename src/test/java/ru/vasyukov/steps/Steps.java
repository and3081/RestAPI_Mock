package ru.vasyukov.steps;

import io.qameta.allure.Step;
import io.restassured.specification.ResponseSpecification;
import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.requestSpec;
import static ru.vasyukov.steps.Specification.responseSpecCreate;

public class Steps {
    @Step("Init Mock {filename}")
    public static void initMock(String filename) {
        given()
                .spec(requestSpec())
                .body(Json.readBodyJson(filename).toString())
                .when()
                .put(TestData.mock.endpointExpect())
                .then()
                //.log().all()
                .spec(responseSpecCreate());
    }

    @Step("Request Mock {request}")
    public static void requestMock(String request, ResponseSpecification response) {
        given()
                .spec(requestSpec())
                .when()
                .get(request)
                .then()
                .log().all()
                .spec(response);
    }
}
