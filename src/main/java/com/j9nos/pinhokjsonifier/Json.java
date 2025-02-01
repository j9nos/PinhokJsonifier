package com.j9nos.pinhokjsonifier;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class Json {
    private static final String JSON_FILENAME_TEMPLATE = "target/%s.json";

    private Json() {
    }

    public static void save(final Language language) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(String.format(JSON_FILENAME_TEMPLATE, language.name())), language);
        } catch (final IOException e) {
            System.err.println("Could create JSON : " + e);
        }
    }
}
