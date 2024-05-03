package com.apitest;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.hamcrest.Matchers;

public class DeleteTesting {
    Dotenv dotenv = Dotenv.load();

    // initialize RestAssured
    @BeforeEach
    public void beforeEach() {
        // reset all request specifications
        RestAssured.reset();

        // set url
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

        //TC
        @Test
        @DisplayName("Operasi menggunakan id user yang pernah ada di sistem namun sudah dihapus sebelumnya")
        void delete_deleted_user_id() {
            given()
                    .header("app-id", dotenv.get("APP_ID"))
                    // delete user with id 60d0fe4f5311236168a109fb first
                    .delete("/user/60d0fe4f5311236168a109ca");
    
            // try to delete user with id 60d0fe4f5311236168a109fb again
            given()
                    .header("app-id", dotenv.get("APP_ID"))
                    .delete("/user/60d0fe4f5311236168a109ca")
                    .then()
                    .assertThat()
                    .statusCode(404)
                    .body("error", Matchers.equalTo("RESOURCE_NOT_FOUND"));
        }
}
