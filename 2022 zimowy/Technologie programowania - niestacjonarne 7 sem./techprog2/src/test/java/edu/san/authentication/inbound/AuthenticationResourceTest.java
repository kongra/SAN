package edu.san.authentication.inbound;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import javax.json.Json;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class AuthenticationResourceTest {

  private AuthenticationResourceClient authenticationResourceClient;

  @BeforeEach
  void setUp() {
    final var uri = URI.create("http://localhost:8080");
    authenticationResourceClient = RestClientBuilder
        .newBuilder()
        .baseUri(uri)
        .build(AuthenticationResourceClient.class);
  }

  @Test
  void testSignUp() {
    final var signUpData = Json.createObjectBuilder()
        .add("email", "kongra@gmail.com")
        .add("firstName", "Konrad")
        .add("lastName", "Grzanek")
        .build();

    try (final var response = authenticationResourceClient.signUp(signUpData)) {
      assertThat(response.getStatus()).isEqualTo(Status.OK.getStatusCode());
    }
  }

}
