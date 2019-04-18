package com.pawelpiechowiak.library;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Ignore
public class ApiRestTest {
    @BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 8080;
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void checkStatusBookByIsbn_bookFound() {
        given().when().get("/books/gJEC2q7DzpQC").then().statusCode(200);
    }

    @Test
    public void checkStatusBookByIsbn_bookNotFound() {
        given().when().get("/books/xxx").then().statusCode(404);
    }

    @Test
    public void checkStatusBookByCategory_capitalLetter() {
        given().when().get("/books/category/Computers").then().statusCode(200);
    }

    @Test
    public void checkStatusBookByCategory_mixedLetter() {
        given().when().get("/books/category/coMpuTeRs").then().statusCode(200);
    }

    @Test
    public void checkStatusAuthorsRating() {
        given().when().get("rating").then().statusCode(200);
    }

    @Test
    public void checkWhatBookByIsbnReturn() {
        RestAssured.registerParser("text/plain", Parser.JSON);
        given().when().get("/books/gJEC2q7DzpQC").then()
                .body("isbn", equalTo("gJEC2q7DzpQC"));
    }
}
