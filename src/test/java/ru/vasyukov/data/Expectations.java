package ru.vasyukov.data;

/**
 * Дата-класс тела запроса авторизации
 */
public class Expectations {
    public static final String EXPECT_1 ="{\n" +
            "    \"httpRequest\": {\n" +
            "        \"path\": \"/api/photos/cats/123/photos\"\n" +
            "        },\n" +
            "\t\"httpResponse\": {\n" +
            "\t\t\"statusCode\": 200,\n" +
            "\t\t\"headers\": {\n" +
            "\t\t  \"content-type\": [\n" +
            "\t\t\t\"application/json\"\n" +
            "\t\t  ]\n" +
            "\t\t},\n" +
            "\t\t\"body\": {\n" +
            "\t\t\t\"images\": [\n" +
            "\t\t\t\t\"test.jpg\"\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t},\n" +
            "    \"timeToLive\": {\n" +
            "        \"unlimited\": true\n" +
            "    }\n" +
            "}";
}
