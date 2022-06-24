package ru.vasyukov.steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import ru.vasyukov.properties.TestData;

import static org.hamcrest.Matchers.*;
import static ru.vasyukov.steps.MyListener.myListener;

/**
 * Класс спецификаций для запросов и ответов
 */
public class Specification {
    /**
     * Спецификация запроса: базовый URI, тип контента, листенер для аллюра
     * @return объект спецификации
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(TestData.mock.urlServer()+":"+TestData.mock.portServer())
                .setContentType("application/json; charset=UTF-8")
                .addFilter(myListener())
                .build();
    }

    /**
     * Спецификация ответа: код статуса и проверки body
     * @param statusCode код статуса
     * @param length     длина массива images
     * @param itemName   значение в массиве
     * @return объект спецификации
     */
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

    /**
     * Спецификация ответа: код статуса и проверки body
     * @param statusCode код статуса
     * @param statusLine фрагмент строки статуса
     * @return объект спецификации
     */
    public static ResponseSpecification responseSpecPhoto456(int statusCode, String statusLine) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectStatusLine(containsString(statusLine))
                .build();
    }

    /**
     * Спецификация ответа: код статуса и проверки body
     * @param statusCode код статуса
     * @param id         значение ID
     * @return объект спецификации
     */
    public static ResponseSpecification responseSpecIdParams(int statusCode, String id) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectBody("ID", notNullValue())
                .expectBody("ID", is(id))
                .build();
    }

    /**
     * Спецификация ответа: код статуса и проверки body
     * @param statusCode код статуса
     * @param id         значение WrongID
     * @return объект спецификации
     */
    public static ResponseSpecification responseSpecIdParamsWrong(int statusCode, String id) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectBody("WrongID", notNullValue())
                .expectBody("WrongID", is(id))
                .build();
    }
}