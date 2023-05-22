// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Objects;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.ws.rs.core.Response.Status;

@QuarkusTest
class TestGreetingResource {

  @ConfigProperty(name = "quarkus.http.port")
  Long port;

  GreetingResourceClient greetingResourceClient;

  @BeforeEach
  void setUp() {
    Objects.requireNonNull(port);
    final var uri = URI.create("http://localhost:" + port);
    greetingResourceClient = RestClientBuilder
        .newBuilder()
        .baseUri(uri)
        .build(GreetingResourceClient.class);
  }

  @Test
  void testHelloEndpoint() {
    final var greetingBody = Json.createObjectBuilder()
        .add("name", "John")
        .build();

    try (final var response = greetingResourceClient
        .hello(greetingBody)) {
      assertThat(response.getStatus())
          .isEqualTo(Status.OK.getStatusCode());
    }
  }

}