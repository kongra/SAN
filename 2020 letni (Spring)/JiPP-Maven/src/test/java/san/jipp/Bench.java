package san.jipp;

import org.jetbrains.annotations.NotNull;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import san.utils.IntArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Bench {

  @State(Scope.Thread)
  public static class MyState {
    private final int N = 1_000_000;
    private List<Integer> arrayList = createArrayList(this);
    private IntArray intArray = createIntArray(this);

    private long sum = 0;
  }

  // @Benchmark
  public void benchArrayList(@NotNull MyState state, Blackhole blackhole) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < state.N; i++) {
      numbers.add(i);
    }
    blackhole.consume(numbers);
  }

  // @Benchmark
  public void benchArrayListWithSpaceReseved(@NotNull MyState state
      , @NotNull Blackhole blackhole) {
    List<Integer> numbers = createArrayList(state);
    blackhole.consume(numbers);
  }

  @NotNull
  private static List<Integer> createArrayList(@NotNull MyState state) {
    List<Integer> numbers = new ArrayList<>(state.N);
    for (int i = 0; i < state.N; i++) {
      numbers.add(i);
    }
    return numbers;
  }

  // @Benchmark
  public void benchIntArray(@NotNull MyState state, @NotNull Blackhole blackhole) {
    IntArray numbers = createIntArray(state);
    blackhole.consume(numbers);
  }

  @NotNull
  private static IntArray createIntArray(@NotNull MyState state) {
    IntArray numbers = IntArray.ofLenght(state.N);
    for (int i = 0; i < state.N; i++) {
      numbers.push(i);
    }
    return numbers;
  }

  @Benchmark
  public void benchArrayListSum(@NotNull MyState state, @NotNull Blackhole blackhole) {
    state.sum = 0;
    for (var e : state.arrayList) {
      state.sum += e;
    }
    blackhole.consume(state.sum);
  }

  @Benchmark
  public void benchIntArraySum(@NotNull MyState state, @NotNull Blackhole blackhole) {
    state.sum = 0;
    state.intArray.forEach(e -> {
      state.sum += e;
    });
    blackhole.consume(state.sum);
  }

}
