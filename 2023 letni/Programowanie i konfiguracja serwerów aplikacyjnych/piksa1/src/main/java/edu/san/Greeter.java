// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class Greeter {

  private final String greetingText;

  Greeter(
      @ConfigProperty(name = "edu.san.greetingText") String greetingText) {
    this.greetingText = Objects.requireNonNull(greetingText).strip();
  }

  public String greetMe(String whoami) {
    Objects.requireNonNull(whoami);
    return getGreetingText() + " " + whoami;
  }

  public String getGreetingText() {
    return this.greetingText;
  }
}
