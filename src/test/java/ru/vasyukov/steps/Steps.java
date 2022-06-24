package ru.vasyukov.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.TimeToLive;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpTemplate;
import org.mockserver.model.MediaType;
import ru.vasyukov.properties.TestData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.HttpTemplate.template;
import static org.mockserver.model.JsonBody.json;
import static org.mockserver.model.Parameter.param;
import static ru.vasyukov.steps.Specification.requestSpec;

public class Steps {
    @Step("Init Mock Photo123 {filename}")
    public static void initMockPhoto123(String filename, String endpoint, int statusCode) {
        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath(endpoint),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        1)
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(json(new String(Objects.requireNonNull(attachFile(filename))),
                                MediaType.APPLICATION_JSON_UTF_8)));
    }

    @Step("Request Mock '{request}'")
    public static void requestMock(String request, ResponseSpecification checkResponse) {
        given()
                .spec(requestSpec())
                .when()
                .get(request)
                .then()
                .log().all()
                .spec(checkResponse);
    }

    @Step("Request Mock '{request}'  Params '{id}'")
    public static void requestMockParams(String request, String id, ResponseSpecification checkResponse) {
        given()
                .spec(requestSpec())
                .param("id", id)
                //.log().all()
                .when()
                .get(request)
                .then()
                .log().all()
                .spec(checkResponse);
    }

    @Step("Init Mock Photo456")
    public static void initMockPhoto456(String endpoint, int statusCode, String statusLine) {
        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath(endpoint),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        1)
                .respond(response()
                        .withStatusCode(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withReasonPhrase(statusLine));
    }

    @Step("Init Mock IdParams: {statusCode1}, {statusCode2}")
    public static void initMockIdParams(String endpoint, String id1, String id2,
                                        int statusCode1, int statusCode2, int statusCode3) {
        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath(endpoint)
                                .withQueryStringParameters(
                                        param("id", "[0-9]+")),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        1)
                .respond(template(HttpTemplate.TemplateType.VELOCITY,
                        "#if($request.queryStringParameters['id'][0] == " + id1 + ")\n" +
                                "    {\n" +
                                "        'statusCode': " + statusCode1 + ",\n" +
                                "        'body': {'ID': '$request.queryStringParameters['id'][0]'}\n" +
                                "    }\n" +
                                "#elseif($request.queryStringParameters['id'][0] == " + id2 + ")\n" +
                                "    {\n" +
                                "        'statusCode': " + statusCode2 + ",\n" +
                                "        'body': {'ID': '$request.queryStringParameters['id'][0]'}\n" +
                                "    }\n" +
                                "#else\n" +
                                "    {\n" +
                                "        'statusCode': " + statusCode3 + ",\n" +
                                "        'body': {'WrongID': '$request.queryStringParameters['id'][0]'}\n" +
                                "    }\n" +
                                "#end"));
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
