package ru.vasyukov.steps;

import io.qameta.allure.Step;
import ru.vasyukov.data.Expectations;
import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.requestSpec;
import static ru.vasyukov.steps.Specification.responseSpec;

public class Steps {
    @Step("")
    public static void initMock() {
        given()
                .spec(requestSpec())
                .body(Expectations.EXPECT_1)
                .when()
                .get(TestData.mock.endpointExpect())
                .then()
                .log().body()
                .spec(responseSpec());
//                .extract().body().as(Resource.class)
//                .getData();
    }
}
