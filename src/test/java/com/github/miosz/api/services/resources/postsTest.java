package com.github.miosz.api.services.resources;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class postsTest {

    String url = "https://jsonplaceholder.typicode.com";

    @Test
    public void
    checkPostsContentTest() {

        given().
                baseUri(url).
        when().
                get("/posts").
        then().
                statusCode(200).
                body(
                        "userId[0]", equalTo(1),
                        "id[1]", equalTo(2),
                        "title[2]", containsString("exercitationem"),
                        "body[3]", stringContainsInOrder(Arrays.asList("assumenda", "tenetur"))
                );
    }

    @Test
    public void
    checkPostIdContentTest() {

        given().
                baseUri(url).
        when().
                get("/posts/1").
        then().
                statusCode(200).
                body("userId", equalTo(1)).
                body("id", equalTo(1)).
                body("title", containsString("occaecati")).
                body("body", stringContainsInOrder(Arrays.asList("consequuntur", "eveniet")));
    }

    @Test
    public void
    createNewPostTest() {

        given().
                baseUri(url).
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"userId\": 123,\n" +
                        "    \"title\": \"test\",\n" +
                        "    \"body\": \"post test\"\n" +
                        "}")
        .when()
                .post("/posts")
        .then()
                .statusCode(201);
    }

    @Test
    public void
    changePostBodyTest() {

        given().
                baseUri(url).
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"id\": 100,\n" +
                        "    \"userId\": 123,\n" +
                        "    \"title\": \"test\",\n" +
                        "    \"body\": \"put test\"\n" +
                        "}")
        .when()
                .put("/posts/100")
        .then()
                .statusCode(200);
    }
}