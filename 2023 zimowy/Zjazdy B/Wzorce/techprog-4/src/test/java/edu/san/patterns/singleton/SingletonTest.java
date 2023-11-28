// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SingletonTest {

  @SuppressWarnings("static-method")
  @Test
  void testSingleton() {
    Singleton.foo();
    SingletonOnDemand.goo();

    final var singleton1 = Singleton.getInstance();
    final var singleton2 = Singleton.getInstance();
    assertThat(singleton1).isEqualTo(singleton2);
  }

}
