// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PasswordsResourceTest {

  @RestClient
  PasswordsResourceClient passwordsResourceClient;

  @BeforeEach
  void setUp() throws Exception {
    assertThat(passwordsResourceClient).isNotNull();
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

    final var input = new PasswordsResourceInput();
    input.password = "abcd";
    final var response = passwordsResourceClient.isPasswordStrong(input);

    assertThat(response.isStrong).isFalse();
  }

}
