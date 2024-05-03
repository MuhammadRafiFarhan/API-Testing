package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PostTesting {
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
    @DisplayName("Membuat user baru dengan mengisi firstName, lastName, dan email valid")
    void post_valid_user_id() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .header("Content-Type", "application/json")
        .body("{\n" +
            "  \"title\": \"mr\",\n" +
            "  \"firstName\": \"Radjiman\",\n" +
            "  \"lastName\": \"Wedyodinigrat\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"email\": \"radjimanwdn@gmail.com\",\n" +
            "  \"dateOfBirth\": \"1906-04-16T00:00:00.000Z\",\n" +
            "  \"phone\": \"+1234567890\",\n" +
            "  \"picture\": \"https://id.wikipedia.org/wiki/drradjiman_(dokter)\",\n"
            +
            "  \"location\": {\n" +
            "    \"street\": \"Jalan\",\n" +
            "    \"city\": \"Bandung\",\n" +
            "    \"state\": \"Jawa Barat\",\n" +
            "    \"country\": \"Indonesia\",\n" +
            "    \"timezone\": \"+7:00\"\n" +
            "  }\n" +
            "}")
        .post("/user/create")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("user-collection.json"))
        .statusCode(200)
        .body("title", Matchers.equalTo("mr"))
        .body("firstName", Matchers.equalTo("Radjiman"))
        .body("lastName", Matchers.equalTo("Wedyodinigrat"))
        .body("gender", Matchers.equalTo("male"))
        .body("email", Matchers.equalTo("radjimanwdn@gmail.com"))
        .body("dateOfBirth", Matchers.equalTo("1906-04-16T00:00:00.000Z"))
        .body("phone", Matchers.equalTo("+1234567890"))
        .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/drradjiman_(dokter)"))
        .body("location.street", Matchers.equalTo("Jalan"))
        .body("location.city", Matchers.equalTo("Bandung"))
        .body("location.state", Matchers.equalTo("Jawa Barat"))
        .body("location.country", Matchers.equalTo("Indonesia"))
        .body("location.timezone", Matchers.equalTo("+7:00"));
     }

    // TC
    @Test
    @DisplayName("Membuat user baru dengan firstName kurang dari 2 karakter")
    void post_user_with_short_firstName() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .header("Content-Type", "application/json")
        .body("{\n" +
            "  \"title\": \"mr\",\n" +
            "  \"firstName\": \"R\",\n" +
            "  \"lastName\": \"Wedyodinigrat\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"email\": \"radjimanwd@gmail.com\",\n" +
            "  \"dateOfBirth\": \"1906-04-16T00:00:00.000Z\",\n" +
            "  \"phone\": \"+1234567890\",\n" +
            "  \"picture\": \"https://id.wikipedia.org/wiki/drradjiman_(dokter)\",\n"
            +
            "  \"location\": {\n" +
            "    \"street\": \"Jalan\",\n" +
            "    \"city\": \"Bandung\",\n" +
            "    \"state\": \"Jawa Barat\",\n" +
            "    \"country\": \"Indonesia\",\n" +
            "    \"timezone\": \"+7:00\"\n" +
            "  }\n" +
            "}")
        .post("/user/create")
        .then()
        .assertThat()
        .statusCode(400)
        .body("message", Matchers.equalTo("firstName must be at least 2 characters long"));
    }

    // TC
    @Test
    @DisplayName("Membuat user baru dengan dateOfBirth kurang dari 1/1/1900")
    void post_user_with_invalid_dateOfBirth() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .header("Content-Type", "application/json")
        .body("{\n" +
            "  \"title\": \"mr\",\n" +
            "  \"firstName\": \"Peter\",\n" +
            "  \"lastName\": \"Parker\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"email\": \"PeterParker@gmail.com\",\n" +
            "  \"dateOfBirth\": \"1899-12-31T00:00:00.000Z\",\n" +
            "  \"phone\": \"+1234567890\",\n" +
            "  \"picture\": \"https://example.com\",\n" +
            "  \"location\": {\n" +
            "    \"street\": \"123 Street\",\n" +
            "    \"city\": \"New York\",\n" +
            "    \"state\": \"NY\",\n" +
            "    \"country\": \"USA\",\n" +
            "    \"timezone\": \"-5:00\"\n" +
            "  }\n" +
            "}")
        .post("/user/create")
        .then()
        .assertThat()
        .statusCode(400)
        .body("message", Matchers.equalTo("dateOfBirth must be after 1/1/1900"));
    }

    // TC
    @Test
    @DisplayName("Membuat user baru dengan dateOfBirth valid di atas 1 Januari 1900")
    void post_user_with_valid_dateOfBirth() {
        given()
        .header("app-id", dotenv.get("APP_ID"))
        .header("Content-Type", "application/json")
        .body("{\n" +
            "  \"title\": \"mr\",\n" +
            "  \"firstName\": \"John\",\n" +
            "  \"lastName\": \"Marshton\",\n" +
            "  \"gender\": \"male\",\n" +
            "  \"email\": \"johnmarshtonn@gmail.com\",\n" +
            "  \"dateOfBirth\": \"1990-01-01T00:00:00.000Z\",\n" +
            "  \"phone\": \"+1234567890\",\n" +
            "  \"picture\": \"https://example.com\",\n" +
            "  \"location\": {\n" +
            "    \"street\": \"123 Street\",\n" +
            "    \"city\": \"New York\",\n" +
            "    \"state\": \"NY\",\n" +
            "    \"country\": \"USA\",\n" +
            "    \"timezone\": \"-5:00\"\n" +
            "  }\n" +
            "}")
        .post("/user/create")
        .then()
        .assertThat()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("user-collection.json"))
        .body("title", Matchers.equalTo("mr"))
        .body("firstName", Matchers.equalTo("John"))
        .body("lastName", Matchers.equalTo("Marshton"))
        .body("gender", Matchers.equalTo("male"))
        .body("email", Matchers.equalTo("johnmarshtonn@gmail.com"))
        .body("dateOfBirth", Matchers.equalTo("1990-01-01T00:00:00.000Z"))
        .body("phone", Matchers.equalTo("+1234567890"))
        .body("picture", Matchers.equalTo("https://example.com"))
        .body("location.street", Matchers.equalTo("123 Street"))
        .body("location.city", Matchers.equalTo("New York"))
        .body("location.state", Matchers.equalTo("NY"))
        .body("location.country", Matchers.equalTo("USA"))
        .body("location.timezone", Matchers.equalTo("-5:00"));
    }
}
