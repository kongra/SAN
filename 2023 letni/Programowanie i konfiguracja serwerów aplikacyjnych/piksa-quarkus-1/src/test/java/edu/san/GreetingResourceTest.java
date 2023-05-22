package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class GreetingResourceTest {

  @ConfigProperty(name = "quarkus.http.port")
  Long port;

  @Test
  void testHelloEndpoint() throws IOException {
    final var uri = URI.create("http://localhost:" + port + "/hello");
    final var con = (HttpURLConnection) uri.toURL().openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json");
    con.setRequestProperty("Accept", "application/json");

    con.setDoOutput(true);
    final var jsonInputString = "{\"name\": \"John\"}";

    try (final var os = con.getOutputStream()) {
      final var input = jsonInputString.getBytes("utf-8");
      os.write(input, 0, input.length);
    }

    try (final var br = new BufferedReader(
        new InputStreamReader(con.getInputStream(), "utf-8"))) {
      final var response = new StringBuilder();
      String responseLine = null;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }

      assertThat(response.toString())
          .hasToString("{\"greeting\":\"Hello John!!!\"}");
    }
  }

}