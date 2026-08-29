package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloResponseTest {

    @Test
    void storesTheGivenMessage() {
        HelloResponse response = new HelloResponse("Hello Commit Academy!");

        assertEquals("Hello Commit Academy!", response.message());
    }

    @Test
    void isARecordWithNonNullMessage() {
        HelloResponse response = new HelloResponse("ping");

        assertNotNull(response.message());
    }
}
