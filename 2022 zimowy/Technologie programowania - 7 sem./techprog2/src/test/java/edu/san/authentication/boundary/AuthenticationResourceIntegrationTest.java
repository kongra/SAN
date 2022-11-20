package edu.san.authentication.boundary;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.authentication.control.ProfileDto;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.test.junit.QuarkusTest;
import telsos.string.Email;

@QuarkusTest
class AuthenticationResourceIntegrationTest {

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
    profileRepository.deleteProfileByEmail(email);
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

  @Test
  void testFindProfileByEmailPositive() {
    final var signUpData = Json.createObjectBuilder()
        .add("email", "kongra@gmail.com")
        .add("firstName", "Konrad")
        .add("lastName", "Grzanek")
        .build();
    try (final var response = authenticationResourceClient.signUp(signUpData)) {
      assertThat(response.getStatus())
          .isEqualTo(Status.CREATED.getStatusCode());
    }

    try (final var response = authenticationResourceClient
        .findProfileByEmail("kongra@gmail.com")) {
      assertThat(response.getStatus())
          .isEqualTo(Status.OK.getStatusCode());

      assertThat(response.readEntity(ProfileDto.class)).isNotNull();
    }
  }

  @Test
  void testFindProfileByEmailNegative() {
    try (final var response = authenticationResourceClient
        .findProfileByEmail("kgrzanek@san.edu.pl")) {
      assertThat(response.getStatus())
          .isEqualTo(Status.OK.getStatusCode());

      assertThat(response.readEntity(ProfileDto.class)).isNull();
    }
  }

}
