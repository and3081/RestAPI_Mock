package ru.vasyukov.hooks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockserver.configuration.ConfigurationProperties;
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
        mockServer = startClientAndServer(Integer.parseInt(TestData.mock.portServer()));
        Assertions.assertTrue(mockServer.hasStarted() && mockServer.isRunning(),
                "Ошибка запуска MockServer");
        ConfigurationProperties.logLevel(TestData.mock.loggingServer());
    }

    @BeforeEach
    public void resetMockServer() {
        mockServer.reset();
    }

    @AfterAll
    public static void stopMockServer() {
        if (mockServer != null) {
            mockServer.stop();
            mockServer = null;
        }
    }
}
