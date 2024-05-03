package com.apitest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@ExtendWith(RestAssuredExtension.class)
public class UserOperationsTest {

    @Test
    public void testGetUser() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
        .when()
            .get("/user")
        .then()
            .assertThat()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("user_postman_collection.json"));
    }

    @Test
    public void testCreateUser() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"firstName\": \"Bachrudin\",\n  \"lastName\": \"Muchtar\",\n  \"email\": \"BMuchtar.1999@gmail.com\"\n}")
            .post("/user/create")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUser() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"firstName\": \"Check updated firstName\",\n  \"lastName\": \"Check updated lastName\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleMr() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"mr\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleMrs() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"mrs\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleMs() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"ms\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleMiss() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"miss\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleDr() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"dr\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserTitleEmpty() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"title\": \"\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserFirstName() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"firstName\": \"NewFirstName\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLastName() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"lastName\": \"NewLastName\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserGenderMale() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"gender\": \"male\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserGenderFemale() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"gender\": \"female\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserGenderOther() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"gender\": \"other\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserGenderEmpty() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"gender\": \"\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserDateOfBirth() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"dateOfBirth\": \"1999-01-01\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserPhone() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"phone\": \"1234567890\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserPictureUrl() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"picture\": \"https://example.com/picture.jpg\"\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLocationStreet() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"location\": {\n    \"street\": \"Ciwaruga St.\"\n  }\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLocationCity() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"location\": {\n    \"city\": \"Bandung\"\n  }\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLocationState() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"location\": {\n    \"state\": \"West Java\"\n  }\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLocationCountry() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"location\": {\n    \"country\": \"Indonesia\"\n  }\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void testUpdateUserLocationTimezone() {
        given()
            .header("app-id", "6627086c776faf356d1e175c")
            .body("{\n  \"location\": {\n    \"timezone\": \"WIB\"\n  }\n}")
            .put("/user/60d0fe4f5311236168a109ca")
        .then()
            .assertThat()
            .statusCode(200);
    }
}
