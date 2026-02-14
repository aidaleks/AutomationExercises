package com.automationexercise.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonDataReader {
    private JsonDataReader() {
    }

    /**
     * Load a JSON resource from the classpath (src/main/resources) and deserialize to given class.
     */
    public static <T> T loadResource(String resourceName, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonDataReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found on classpath: " + resourceName);
            }
            return mapper.readValue(is, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load JSON resource: " + resourceName, e);
        }
    }
}
