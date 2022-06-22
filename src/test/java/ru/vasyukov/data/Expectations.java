package ru.vasyukov.data;

/**
 * Дата-класс тела запроса авторизации
 */
public class Expectations {
    public static final String EXPECT_Photo123 =
            "{\n" +
            "   \"httpRequest\": {\n" +
            "       \"path\": \"/api/photos/cats/123/photos\",\n" +
	    	"       \"method\" : \"GET\"\n" +
            "    },\n" +
            "   \"httpResponse\": {\n" +
            "       \"statusCode\": 200,\n" +
            "       \"headers\": {\n" +
            "           \"content-type\": [\n" +
            "           \"application/json\"\n" +
            "           ]\n" +
            "       },\n" +
            "       \"body\": {\n" +
            "           \"images\": [\n" +
            "               \"test.jpg\"\n" +
            "           ]\n" +
            "       }\n" +
            "   },\n" +
            "   \"timeToLive\": {\n" +
            "       \"unlimited\": true\n" +
            "   }\n" +
            "}";
}
