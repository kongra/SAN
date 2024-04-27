// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import static io.restassured.RestAssured.given;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PasswordsResourceTest {

  PasswordsResourceClient passwordsResourceClient;

  @BeforeEach
  void setUp() throws Exception {
    final var uri = URI
        .create("http://localhost:8081/passwords/v1/isPasswordStrong");
    passwordsResourceClient = QuarkusRestClientBuilder.newBuilder()
        .baseUri(uri)
        .build(PasswordsResourceClient.class);
  }

  @Test
  void testIsPasswordStrong() {
    final var passwordJson = """
        {"password": "abcd"}
        """;

    given()
        .when()
        .header("content-type", "application/json")
        .body(passwordJson)
        .post("/passwords/v1/isPasswordStrong")
        .then()
        .statusCode(200);
  }

}
