// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
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

  @Test
  void testNaturalNumbers() {
    final var N = LazySeq.iterate(x -> x + 1, 0);
    assertThat(N.first()).isZero();
    assertThat(N.rest().first()).isEqualTo(1);

    assertThat(Seq.nth(N, 0)).contains(0);
    assertThat(Seq.nth(N, 1)).contains(1);
    assertThat(Seq.nth(N, 2)).contains(2);
    assertThat(Seq.nth(N, 200)).contains(200);
  }

  @Test
  void testFibonacci() {
    // ([a, b], [b, a+b], ...)
    // ([0, 1], [1, 1], [1, 2], [2, 3], [3, 5], [5, 8], [8, 13], ...)
    // ( 0, 1, 1, 2, 3, 5, 8, ...)
    record FibPair(long a, long b) {}
    final UnaryOperator<FibPair> fibNext = p -> new FibPair(p.b(),
        p.a() + p.b());
    final var start = new FibPair(0, 1);
    final var fib = LazySeq.fmap(FibPair::a, LazySeq.iterate(fibNext, start));

    assertThat(Seq.nth(fib, 0)).contains(0L);
    assertThat(Seq.nth(fib, 1)).contains(1L);
    assertThat(Seq.nth(fib, 2)).contains(1L);
    assertThat(Seq.nth(fib, 3)).contains(2L);
    assertThat(Seq.nth(fib, 4)).contains(3L);
    assertThat(Seq.nth(fib, 5)).contains(5L);
    assertThat(Seq.nth(fib, 6)).contains(8L);

    assertThat(Seq.asString(LazySeq.take(10, fib)))
        .isEqualTo("(0,1,1,2,3,5,8,13,21,34)");

    var fib20 = LazySeq.take(20, fib);
    var sum20 = LazySeq.reduce((acc, x) -> acc + x, 0L, fib20);
    assertThat(sum20).isEqualTo(10945L);

    var fib20even = LazySeq.filter(x -> x % 2 == 0, fib20);
    assertThat(Seq.asString(fib20even)).isEqualTo("(0,2,8,34,144,610,2584)");
  }

}
