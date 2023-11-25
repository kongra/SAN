// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns;

import java.util.List;
import java.util.Objects;

public record Foo (List<Double> xs) implements Countable {
  
  public Foo {
    Objects.requireNonNull(xs);
  }

  @Override
  public int count() {
    return xs.size();
  }
  
}
