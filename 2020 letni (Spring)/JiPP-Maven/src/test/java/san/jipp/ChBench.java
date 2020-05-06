// Copyright (c) Konrad Grzanek
// Created 22.07.19
package san.jipp;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ChBench {

  @State(Scope.Thread)
  public static class MyState {
    final String s1 = "a";
    final String s2 = " a";
    final String s3 = "a ";
    final String s4 = " a ";
    final String s5 = "A very long string with a lot of elements" +
        "inside of it usbdyfbusdybf yubsdfyu vyusvdf uyvsd uvy";

    final String s6 = " ".repeat(200) + s5;

    final long l = 1;
  }

}
