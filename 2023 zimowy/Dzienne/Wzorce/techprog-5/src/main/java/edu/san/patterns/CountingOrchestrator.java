// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

public final class CountingOrchestrator {

  public static int summariseCountables(Countable... countables) {
    var result = 0;
    for (final var c : countables) {
      result += c.count();
    }
    return result;
  }

  private CountingOrchestrator() {}

}
