package edu.san;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonTest {

  @SuppressWarnings("static-method")
  @Test
  void testSingleton() {
    Singleton.foo();

    var instance1 = Singleton.getInstance();
    var instance2 = Singleton.getInstance();
    assertThat(instance1).isSameAs(instance2);
  }

}
