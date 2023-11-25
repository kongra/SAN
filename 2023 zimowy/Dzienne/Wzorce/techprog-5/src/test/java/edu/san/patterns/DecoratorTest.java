// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class DecoratorTest {

  @Test
  void testCountables() {
    final var foo = new Foo(List.of(1d, 2d, 3d, 4d));
    assertThat(foo).isNotNull();

    final var n = CountingOrchestrator.summariseCountables(foo);
    assertThat(n).isEqualTo(4);

    final var m = CountingOrchestrator.summariseCountables(
        CountableStringDecorator.of("aaa"),
        CountableStringDecorator.of("bbb"),
        CountableStringDecorator.of("ccc"));
    assertThat(m).isEqualTo(9);
  }

}
