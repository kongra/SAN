// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import edu.san.passwords.PasswordsStrengthAnalyzer;
import telsos.strings.NonBlank;

@State(org.openjdk.jmh.annotations.Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PasswordsStregnthAnalyzerBenchTest {

  PasswordsStrengthAnalyzer passwordsStrengthAnalyzer = new NbvcxzPasswordStrengthAnalyzer();

  NonBlank password = NonBlank.of("abcdefgh").orElseThrow(IllegalArgumentException::new);

  @Benchmark
  public void test(Blackhole blackhole) {
    final var output = passwordsStrengthAnalyzer.analyze(password);
    blackhole.consume(output);
  }

}
