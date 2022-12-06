// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.boundary;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Objects;

import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.authentication.control.ProfileRepository;
import io.quarkus.test.junit.QuarkusTest;
import telsos.string.Email;

@QuarkusTest
class AuthenticationResourceIntegrationTest {

  private AuthenticationResourceClient authenticationResourceClient;

  @Inject
  ProfileRepository profileRepository;

  @ConfigProperty(name = "quarkus.http.port")
  Long port;

  @BeforeEach
  void setUp() {
    Objects.requireNonNull(port);

    final var uri = URI.create("http://localhost:" + port);
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
  void testSignUpB2C() {
    final var signUpData = Json.createObjectBuilder()
        .add("email", "kongra@gmail.com")
        .add("firstName", "Konrad")
        .add("lastName", "Grzanek")
        .add("profileKind", "standard")
        .add("address", "Gdańsk, Rogaczewskiego 5")
        .build();

    try (final var response = authenticationResourceClient
        .signUpB2C(signUpData)) {
      assertThat(response.getStatus())
          .isEqualTo(Status.CREATED.getStatusCode());
    }
  }

  @Test
  void testSignUpB2B() {
    final var signUpData = Json.createObjectBuilder()
        .add("email", "kongra@gmail.com")
        .add("firstName", "Konrad")
        .add("lastName", "Grzanek")
        .add("profileKind", "standard")
        .add("regon", "12345678512347")
        .build();

    try (final var response = authenticationResourceClient
        .signUpB2B(signUpData)) {
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
        .add("profileKind", "standard")
        .add("address", "Gdańsk, Rogaczewskiego 5")
        .build();
    try (final var response = authenticationResourceClient
        .signUpB2C(signUpData)) {
      assertThat(response.getStatus())
          .isEqualTo(Status.CREATED.getStatusCode());
    }

    try (final var response = authenticationResourceClient
        .findProfileByEmail("kongra@gmail.com")) {
      assertThat(response.getStatus())
          .isEqualTo(Status.OK.getStatusCode());

      // assertThat(response.readEntity(ProfileDto.class)).isNotNull();
    }
  }

  @Test
  void testFindProfileByEmailNegative() {
    try (final var response = authenticationResourceClient
        .findProfileByEmail("kgrzanek@san.edu.pl")) {
      assertThat(response.getStatus())
          .isEqualTo(Status.OK.getStatusCode());

      // assertThat(response.readEntity(ProfileDto.class)).isNull();
    }
  }

}
