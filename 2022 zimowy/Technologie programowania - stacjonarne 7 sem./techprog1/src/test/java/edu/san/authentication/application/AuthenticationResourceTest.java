package edu.san.authentication.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.authentication.boundary.AuthenticationResourceClient;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.test.junit.QuarkusTest;
import telsos.string.Email;

@QuarkusTest
class AuthenticationResourceTest {

  private AuthenticationResourceClient authenticationResourceClient;

  @Inject
  ProfileRepository profileRepository;

  @BeforeEach
  void setUp() {
    final var uri = URI.create("http://localhost:8080");
    authenticationResourceClient = RestClientBuilder
        .newBuilder()
        .baseUri(uri)
        .build(AuthenticationResourceClient.class);
  }

  @AfterEach
  void tearDown() {
    final var email = Email.of("kongra@gmail.com").orElseThrow();
    profileRepository.deleteByEmail(email);
  }

  @Test
  void testSignUp() {
    final var signUpData = Json.createObjectBuilder()
        .add("email", "kongra@gmail.com")
        .add("firstName", "Konrad")
        .add("lastName", "Grzanek")
        .build();

    try (final var response = authenticationResourceClient.signUp(signUpData)) {
      assertThat(response.getStatus())
          .isEqualTo(Status.CREATED.getStatusCode());
    }
  }

}
