public class Counter {
  // Zadaniem obiektów tej klasy jest zliczanie
  // wywołań

  private long count;

  public Counter(long initCount) {
    this.count = initCount;
  }

  public long value() {
    return this.count;
  }

  public Counter inc() {
    this.count += 1;
    return this;
  }

}
