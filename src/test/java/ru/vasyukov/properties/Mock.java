package ru.vasyukov.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для проперти
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:mock.properties"
})
public interface Mock extends Config {
    @Key("url.server")
    String urlServer();

    @Key("ip.server")
    String ipServer();

    @Key("port.server")
    String portServer();

    @Key("logging.server")
    String loggingServer();
}
