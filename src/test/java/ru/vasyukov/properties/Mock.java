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
    @Key("base.url.server")
    String baseUrlServer();

    @Key("endpoint.expect")
    String endpointExpect();
}
