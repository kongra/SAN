package multi;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchHtmlFlow {

  @Benchmark
  public static void benchSeqGen(MyState state, Blackhole blackhole) {
    blackhole.consume(TestHtmlFlow.getHTML());
  }

  @State(Scope.Thread)
  public static class MyState {
  }
}
