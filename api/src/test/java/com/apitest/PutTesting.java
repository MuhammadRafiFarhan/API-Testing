package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PutTesting {
    Dotenv dotenv = Dotenv.load();

    // initialize RestAssured
    @BeforeEach
    public void beforeEach() {
        // reset all request specifications
        RestAssured.reset();

        // set url
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    // TC
    @Test
    @DisplayName("Operasi tidak menggunakan id user")
    void update_no_user_id() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .put("/user/")
        .then()
        .assertThat()
        .statusCode(404)
        .body("error", Matchers.equalTo("PATH_NOT_FOUND"));
    }
}
