package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetTesting {
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
    @DisplayName("Operasi menggunakan id user yang valid")
    void get_valid_user_id() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .get("/user/60d0fe4f5311236168a109ca")
        .then()
        .assertThat()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("user-collection.json"));
    }

    // TC
    @Test
    @DisplayName("Operasi menggunakan id user yang tidak valid")
    void get_invalid_user_id() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .get("/user/abcdef")
        .then()
        .assertThat()
        .statusCode(400)
        .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }
}
