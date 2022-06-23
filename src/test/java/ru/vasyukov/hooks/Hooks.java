package ru.vasyukov.hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockserver.integration.ClientAndServer;
import ru.vasyukov.properties.TestData;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * Класс хуков
 */
public class Hooks {
    protected static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockServer() {
        mockServer = startClientAndServer(Integer.parseInt(TestData.mock.basePortServer()));
        Assertions.assertNotNull(mockServer, "Ошибка start MockServer");
    }

    @AfterAll
    public static void stopMockServer() {
        if (mockServer != null) {
            mockServer.stop();
            mockServer = null;
        }
    }

//    @Step("Reset MockServer")
//    @BeforeAll
//    public static void resetMock() {
//        try {
//            given()
//                    .spec(requestSpec())
//                    .when()
//                    .put(TestData.mock.endpointReset())
//                    .then()
//                    //.log().all()
//                    .spec(responseSpecOk());
//        } catch (Exception e) {
//            Assertions.fail("Ошибка соединения с MockServer\n" + e);
//        }
//    }
}
