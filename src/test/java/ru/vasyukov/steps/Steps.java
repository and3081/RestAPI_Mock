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

        new MockServerClient(TestData.mock.ipServer(),
                Integer.parseInt(TestData.mock.portServer()))
                .when(request()
                                .withMethod("GET")
                                .withPath("/api/core/cats/get-by-id")
                                .withQueryStringParameters(
                                        param("id", "[0-9]+")),
                        Times.unlimited(),
                        TimeToLive.unlimited(),
                        1)
                .respond(template(HttpTemplate.TemplateType.VELOCITY,
                        "#if($request.queryStringParameters['id'] == ['1'])\n" +
                                "    {\n" +
                                "        'statusCode': 200,\n" +
                                "        'body': \"{'name': 'value'}\"\n" +
                                "    }\n" +
                                "#else\n" +
                                "    {\n" +
                                "        'statusCode': 200,\n" +
                                "        'body': \"$!request.queryStringParameters['id']\"\n" +
                                "    }\n" +
                                "#end"));

//                        response()
//                        .withStatusCode(statusCode)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody(
//                                "#if($request.method == 'GET') { --- }" +
//                                        "#else { === }"));
    }

    @Step("Request Mock {request}")
    public static void requestMock(String request, ResponseSpecification checkResponse) {
        given()
                .spec(requestSpec())
                .when()
                .get(request)
                .then()
                .log().all()
                .spec(checkResponse);
    }

    @Step("Init Mock Photo456 {filename}")
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
