package ru.vasyukov.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.vasyukov.properties.TestData;

import static org.hamcrest.Matchers.*;

/**
 * Класс спецификаций для запросов и ответов
 */
public class Specification {
    /**
     * Спецификация запроса: базовый URI, тип контента
     *
     * @return объект спецификации
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.mock.baseUrlServer())
                .setContentType("application/json; charset=UTF-8")
                .build();
    }

    public static ResponseSpecification responseSpecOk() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecCreate() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecPhoto123(int statusCode, int length, String itemName) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectBody("images", notNullValue())
                .expectBody("images", not(emptyArray()))
                .expectBody("images", not(hasItem(nullValue())))
                .expectBody("images.size()", is(length))
                .expectBody("images", hasItem(itemName))
                .build();
    }

    public static ResponseSpecification responseSpecPhoto456(int statusCode, String statusLine) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectStatusLine(statusLine)
                .build();
    }
}