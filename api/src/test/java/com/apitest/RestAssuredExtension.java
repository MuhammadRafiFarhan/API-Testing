package com.apitest;

import io.restassured.RestAssured; // Import the RestAssured class
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class RestAssuredExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }
}
