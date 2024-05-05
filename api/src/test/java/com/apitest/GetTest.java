package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class GetTest {
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void beforeEach() {
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    @Test
    @DisplayName("Operasi menggunakan id user yang tidak valid")
    void get_invalid_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .get("/user/1234567")
            .then()
            .assertThat()
            .statusCode(400)
            .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }

    @Test
    @DisplayName("Operasi menggunakan id user yang valid")
    void get_valid_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .get("/user/60d0fe4f5311236168a109cb")
            .then()
            .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}
