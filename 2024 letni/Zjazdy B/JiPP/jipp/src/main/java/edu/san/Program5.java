// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.concurrent.atomic.AtomicInteger;

import edu.san.utils.Seq;

interface Program5 {

  static void main(String... args) {
    final var seq1 = Seq.empty().cons(1).cons(2).cons(3).cons(4);
    System.out.println(seq1);

    final Seq<Integer> seq2 = Seq.of(1, 2, 3, 4, 5);
    System.out.println(seq2);

    final var sum = new AtomicInteger(0);
    seq2.forEach((e, isLast) -> {
      sum.addAndGet(e);
    });

    System.out.println("Suma wynosi " + sum.get());

    final Seq<String> seq3 = Seq.of("aaa", "bbb", "ccc");
    System.out.println(seq3);
  }

}
