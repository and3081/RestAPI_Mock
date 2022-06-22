package ru.vasyukov.steps;

import io.qameta.allure.Attachment;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Json {
    public static JSONObject readBodyJson(String filename) {
        JSONObject json = null;
        attachFile(filename);
        try {
            String str = readJsonFile(filename);
            Assertions.assertNotNull(str, "Неправильный JSON: " + filename);
            json = new JSONObject(str);
            Assertions.assertNotNull(json, "Неправильный JSON: " + filename);
        } catch (Exception e) {
            Assertions.fail("Неправильный JSON: " + filename);
        }
        return json;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "JSON '{filename}'", type = "application/json")
    public static byte[] attachFile(String filename) {
        try {
            return Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            Assertions.fail("Ошибка чтения файла: " + filename);
        }
        return null;
    }

    public static String readJsonFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            Assertions.fail("Ошибка чтения файла: " + filename);
        }
        return null;
    }
}
