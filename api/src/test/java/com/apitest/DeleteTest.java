package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

class DeleteTest {
    Dotenv dotenv = Dotenv.load();

    public String userID = "";

    @BeforeEach
    public void beforeEach() {
        userID = new InitiationUserId().getUserID();
        RestAssured.reset();
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    @Test
    @DisplayName("Operasi menggunakan id user yang terdaftar dalam sistem")
    void delete_deleted_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .delete("/user/"+userID);
    }

    @Test
    @DisplayName("Operasi menggunakan id user yang tidak terdaftar di sistem")
    void delete_invalid_user_id() {
        given()
            .header("app-id", dotenv.get("APP_ID"))
            .delete("/user/06370b6310f9d4fd57438990")
            .then()
            .assertThat()
            .statusCode(404)
            .body("error", Matchers.equalTo("RESOURCE_NOT_FOUND"));
    }
}