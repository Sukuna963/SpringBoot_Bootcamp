package com.example.springtest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;

public class TestUtil {

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsBytes(object);
    }

    public static String convertObjectToJsonString(Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }

    public static <T> T convertJsonBytesToObject(byte[] bytes, Class<T> clazz) throws IOException {
        ObjectReader reader = new ObjectMapper().readerFor(clazz);

        return reader.readValue(bytes);
    }
}
