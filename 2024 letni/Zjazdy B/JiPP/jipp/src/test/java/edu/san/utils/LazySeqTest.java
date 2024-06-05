// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LazySeqTest {

  Seq<Integer> seq;

  @BeforeEach
  void setUp() {
    seq = LazySeq.take(2_000, LazySeq.iterate(x -> x + 1, 0));
  }

  @Test
  void testReduce() {
    final var n = LazySeq.reduce((acc, x) -> acc + x, 0, seq);
    assertThat(n.intValue()).isEqualTo(1999000);
  }

}
