// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.jmh;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import edu.san.passwords.app.PasswordsStregnthAnalyzerBenchTest;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class JMHRunner {

  private static final List<Class> BENCH_CLASSES = List.of(
      PasswordsStregnthAnalyzerBenchTest.class);

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
