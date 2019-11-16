// Copyright (c) Konrad Grzanek
// Created 16.11.2019
package multi;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TestSeqGen {

  @Benchmark
  public static void benchSeqGen(MyState state, Blackhole blackhole) {
    blackhole.consume(state.seqGen.gen());
  }

  @Benchmark
  public static void benchSeqGenValue(MyState state, Blackhole blackhole) {
    blackhole.consume(state.seqGen.value());
  }

  @Benchmark
  public static void benchSeqGenAtomic(MyState state, Blackhole blackhole) {
    blackhole.consume(state.seqGenAtomic.gen());
  }

  @Benchmark
  public static void benchSeqGenAtomicValue(MyState state, Blackhole blackhole) {
    blackhole.consume(state.seqGenAtomic.value());
  }

  @State(Scope.Thread)
  public static class MyState {
    final SeqGen seqGen = new SeqGen();
    final SeqGenAtomic seqGenAtomic = new SeqGenAtomic();
  }


}
