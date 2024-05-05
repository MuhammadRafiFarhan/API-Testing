package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class CreateTest {        
        Dotenv dotenv = Dotenv.load();

        InitiationUserId initiationUserId = new InitiationUserId();

        @BeforeEach
        public void beforeEach() {
                RestAssured.reset();
                RestAssured.baseURI = "https://dummyapi.io/data/v1";
        }

        @Test
        @DisplayName("Membuat user dengan mengisi firstName, lastName, dan email")
        void create_valid_user_id() {
                Response response = given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Sulistyo\",\n" +
                                                "  \"lastName\": \"Suwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"sulistyosuwityo@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create");

                String userID_fromPostNewUser = response.jsonPath().getString("id");
                initiationUserId.setUserID(userID_fromPostNewUser);

                response.then()
                                .assertThat()
                                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                                .statusCode(200)
                                .body("title", Matchers.equalTo("mrs"))
                                .body("firstName", Matchers.equalTo("Sulistyo"))
                                .body("lastName", Matchers.equalTo("Suwityo"))
                                .body("gender", Matchers.equalTo("female"))
                                .body("email", Matchers.equalTo("sulistyosuwityo@gmail.com"))
                                .body("dateOfBirth", Matchers.equalTo("1986-01-16T00:00:00.000Z"))
                                .body("phone", Matchers.equalTo("+081223456657"))
                                .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/Sulistyo"))
                                .body("location.street", Matchers.equalTo("Cijerah"))
                                .body("location.city", Matchers.equalTo("Cimahi"))
                                .body("location.state", Matchers.equalTo("Jawa Barat"))
                                .body("location.country", Matchers.equalTo("Indonesia"))
                                .body("location.timezone", Matchers.equalTo("+7:00"));
        }

        @Test
        @DisplayName("Membuat user  dengan dengan mengisi firstName, lastName saja")
        void create_invalid_user_without_email() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Sulistyo\",\n" +
                                                "  \"lastName\": \"Suwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .statusCode(400)
                                .body("error", Matchers.equalTo("BODY_NOT_VALID"));
        }

        @Test
        @DisplayName("Membuat user dengan first name dan email saja")
        void create_invalid_user_without_lastname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Sulistyo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"SSuwityo@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"sreet\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .statusCode(400)
                                .body("error", Matchers.equalTo("BODY_NOT_VALID"));
        }

        @Test
        @DisplayName("Membuat user dengan last name dan email saja")
        void create_invalid_user_without_firstname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"lastName\": \"Suwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"SSuwityo@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"sreet\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .statusCode(400)
                                .body("error", Matchers.equalTo("BODY_NOT_VALID"));
        }

        @Test
        @DisplayName("Membuat user dengan firstName minimum")
        void create_valid_user_minimum_firstname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Su\",\n" +
                                                "  \"lastName\": \"Suwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"validminimumsuwityo@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                                .statusCode(200)
                                .body("title", Matchers.equalTo("mrs"))
                                .body("firstName", Matchers.equalTo("Su"))
                                .body("lastName", Matchers.equalTo("Suwityo"))
                                .body("gender", Matchers.equalTo("female"))
                                .body("email", Matchers.equalTo("validminimumsuwityo@gmail.com"))
                                .body("dateOfBirth", Matchers.equalTo("1986-01-16T00:00:00.000Z"))
                                .body("phone", Matchers.equalTo("+081223456657"))
                                .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/Sulistyo"))
                                .body("location.street", Matchers.equalTo("Cijerah"))
                                .body("location.city", Matchers.equalTo("Cimahi"))
                                .body("location.state", Matchers.equalTo("Jawa Barat"))
                                .body("location.country", Matchers.equalTo("Indonesia"))
                                .body("location.timezone", Matchers.equalTo("+7:00"));

        }

        @Test
        @DisplayName("Membuat user dengan firstName maksimum")
        void create_valid_user_maksimum_firstname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"SulistyoSulistyoSulistyo\",\n" +
                                                "  \"lastName\": \"Suwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"validmaksimumsuwityo@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                                .statusCode(200)
                                .body("title", Matchers.equalTo("mrs"))
                                .body("firstName", Matchers.equalTo("SulistyoSulistyoSulistyo"))
                                .body("lastName", Matchers.equalTo("Suwityo"))
                                .body("gender", Matchers.equalTo("female"))
                                .body("email", Matchers.equalTo("validmaksimumsuwityo@gmail.com"))
                                .body("dateOfBirth", Matchers.equalTo("1986-01-16T00:00:00.000Z"))
                                .body("phone", Matchers.equalTo("+081223456657"))
                                .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/Sulistyo"))
                                .body("location.street", Matchers.equalTo("Cijerah"))
                                .body("location.city", Matchers.equalTo("Cimahi"))
                                .body("location.state", Matchers.equalTo("Jawa Barat"))
                                .body("location.country", Matchers.equalTo("Indonesia"))
                                .body("location.timezone", Matchers.equalTo("+7:00"));

        }

        @Test
        @DisplayName("Membuat user dengan lastName minimum")
        void create_valid_user_minimum_lastname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Sulistyo\",\n" +
                                                "  \"lastName\": \"Su\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"validminimumlastname@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                                .statusCode(200)
                                .body("title", Matchers.equalTo("mrs"))
                                .body("firstName", Matchers.equalTo("Sulistyo"))
                                .body("lastName", Matchers.equalTo("Su"))
                                .body("gender", Matchers.equalTo("female"))
                                .body("email", Matchers.equalTo("validminimumlastname@gmail.com"))
                                .body("dateOfBirth", Matchers.equalTo("1986-01-16T00:00:00.000Z"))
                                .body("phone", Matchers.equalTo("+081223456657"))
                                .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/Sulistyo"))
                                .body("location.street", Matchers.equalTo("Cijerah"))
                                .body("location.city", Matchers.equalTo("Cimahi"))
                                .body("location.state", Matchers.equalTo("Jawa Barat"))
                                .body("location.country", Matchers.equalTo("Indonesia"))
                                .body("location.timezone", Matchers.equalTo("+7:00"));

        }

        @Test
        @DisplayName("Membuat user dengan lastName maksimum")
        void create_valid_user_maksimum_lastname() {
                given()
                                .header("app-id", dotenv.get("APP_ID"))
                                .header("Content-Type", "application/json")
                                .body("{\n" +
                                                "  \"title\": \"mrs\",\n" +
                                                "  \"firstName\": \"Sulistyo\",\n" +
                                                "  \"lastName\": \"SuwityoSuwityoSuwityo\",\n" +
                                                "  \"gender\": \"female\",\n" +
                                                "  \"email\": \"validmaksimumlastname@gmail.com\",\n" +
                                                "  \"dateOfBirth\": \"1986-01-16T00:00:00.000Z\",\n" +
                                                "  \"phone\": \"+081223456657\",\n" +
                                                "  \"picture\": \"https://id.wikipedia.org/wiki/Sulistyo\",\n"
                                                +
                                                "  \"location\": {\n" +
                                                "    \"street\": \"Cijerah\",\n" +
                                                "    \"city\": \"Cimahi\",\n" +
                                                "    \"state\": \"Jawa Barat\",\n" +
                                                "    \"country\": \"Indonesia\",\n" +
                                                "    \"timezone\": \"+7:00\"\n" +
                                                "  }\n" +
                                                "}")
                                .post("/user/create")
                                .then()
                                .assertThat()
                                .body(matchesJsonSchemaInClasspath("user-schema.json"))
                                .statusCode(200)
                                .body("title", Matchers.equalTo("mrs"))
                                .body("firstName", Matchers.equalTo("Sulistyo"))
                                .body("lastName", Matchers.equalTo("SuwityoSuwityoSuwityo"))
                                .body("gender", Matchers.equalTo("female"))
                                .body("email", Matchers.equalTo("validmaksimumlastname@gmail.com"))
                                .body("dateOfBirth", Matchers.equalTo("1986-01-16T00:00:00.000Z"))
                                .body("phone", Matchers.equalTo("+081223456657"))
                                .body("picture", Matchers.equalTo("https://id.wikipedia.org/wiki/Sulistyo"))
                                .body("location.street", Matchers.equalTo("Cijerah"))
                                .body("location.city", Matchers.equalTo("Cimahi"))
                                .body("location.state", Matchers.equalTo("Jawa Barat"))
                                .body("location.country", Matchers.equalTo("Indonesia"))
                                .body("location.timezone", Matchers.equalTo("+7:00"));

        }
}
