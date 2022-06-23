package ru.vasyukov.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import ru.vasyukov.properties.TestData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static ru.vasyukov.steps.Specification.requestSpec;

public class Steps {
    @Step("Init Mock Photo123 {filename}")
    public static void initMockPhoto123(String filename, String endpoint, int statusCode) {
        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath(endpoint),
                        Times.once(),
                        TimeToLive.unlimited(),
                        1)
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("content-type", "application/json")
                        .withBody(attachFile(filename)));
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

    @Step("Init Mock Photo456 {filename}")
    public static void initMockPhoto456(String endpoint, int statusCode, String statusLine) {
        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath(endpoint),
                        Times.once(),
                        TimeToLive.unlimited(),
                        1)
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("content-type", "application/json")
                        .withReasonPhrase(statusLine));
    }


    @Attachment(value = "body '{filename}'", type = "application/json")
    public static byte[] attachFile(String filename) {
        try {
            return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            Assertions.fail("Ошибка чтения файла: " + filename);
        }
        return null;
    }
}
