// Copyright (c) Konrad Grzanek
// Created 22.07.19
package san.jipp;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class JmhRunnerTest {

  private static final List<Class> BENCH_CLASSES = List.of(
      // ChBench.class
      // StreamsBench.class
      // UncheckedBench.class
  );

  @Test
  void runJmhBenchmarks() throws RunnerException {
    var opts = new OptionsBuilder()
        .warmupIterations(3)
        .measurementIterations(3)
        .forks(1)
        .jvmArgsAppend("-server", "-Xms256m", "-Xmx1024m");

    BENCH_CLASSES.forEach(c -> opts.include(c.getSimpleName()));

    Collection<RunResult> runResults = new Runner(opts.build()).run();
    assertFalse(runResults.isEmpty());
  }

}
