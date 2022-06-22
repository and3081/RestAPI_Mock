package ru.vasyukov.steps;

import io.qameta.allure.Step;
import ru.vasyukov.data.Expectations;
import ru.vasyukov.properties.TestData;

import static io.restassured.RestAssured.given;
import static ru.vasyukov.steps.Specification.*;

public class Steps {
    @Step("")
    public static void initMock() {
        given()
                //.log().all()
                .spec(requestSpec())
                .body(Expectations.EXPECT_1)
                .when()
                .put(TestData.mock.endpointExpect())
                .then()
                .log().all()
                .spec(responseSpecCreate());
//                .extract().body().as(Resource.class)
//                .getData();
    }
}
