package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class UpdateTest {
    Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void beforeEach() {
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

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

    @Test
    @DisplayName("Operasi menggunakan id user yang tidak valid")
    void update_invalid_user_id() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .put("/user/123456")
                .then()
                .assertThat()
                .statusCode(400)
                .body("error", Matchers.equalTo("PARAMS_NOT_VALID"));
    }

    @Test
    @DisplayName("Update title  user dengan title mr")
    void update_user_title_mr() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"mr\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }

    @Test
    @DisplayName("Update title  user dengan title ms")
    void update_user_title_ms() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"ms\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }

    @Test
    @DisplayName("Update title  user dengan title mrs")
    void update_user_title_mrs() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"mrs\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }

    @Test
    @DisplayName("Update title  user dengan title miss")
    void update_user_title_miss() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"miss\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }

    @Test
    @DisplayName("Update title user dengan title dr")
    void update_user_title_dr() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"dr\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }

    @Test
    @DisplayName("Update title user dengan title kosong")
    void update_user_title_null() {
        given()
                .header("app-id", dotenv.get("APP_ID"))
                .body("{\"title\":\"\"}")
                .put("/user/60d0fe4f5311236168a109cb")
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                .body("title", Matchers.equalTo("miss"));
    }
}
