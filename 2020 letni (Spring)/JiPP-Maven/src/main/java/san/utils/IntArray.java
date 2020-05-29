package san.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class IntArray {

  @Contract(value = "_ -> new", pure = true)
  public static @NotNull IntArray ofLenght(int length) {
    return new IntArray(length);
  }

  @Contract("_, _ -> new")
  public static @NotNull IntArray initWith(int length, int initialValue) {
    return new IntArray(length, initialValue);
  }

  @Contract("_, _ -> new")
  public static @NotNull IntArray ofValues(int length, int... values) {
    return new IntArray(length, values);
  }

  private final int[] data;

  private int fillPointer;

  private IntArray(int length) {
    data = new int[length];
  }

  private IntArray(int length, int initialValue) {
    this(length);
    for (int i = 0; i < length; i++) {
      data[i] = initialValue;
    }
  }

  private IntArray(int length, int @NotNull ... values) {
    this(length);
    for (var e : values) {
      push(e);
    }
  }

  public IntArray push(int e) {
    data[fillPointer++] = e;
    return this;
  }

  public void setFillPointer(int fillPointer) {
    if (fillPointer >= data.length) {
      throw new IllegalArgumentException();
    }
    this.fillPointer = fillPointer;
  }

  public int fillPointer() {
    return fillPointer;
  }

  public boolean isEmpty() {
    return 0 == fillPointer();
  }

  public int get(int i) {
    if (i >= fillPointer) throw new ArrayIndexOutOfBoundsException();
    return data[i];
  }

  public IntArray set(int i, int e) {
    if (i >= fillPointer) throw new ArrayIndexOutOfBoundsException();
    data[i] = e;
    return this;
  }

  @Override
  public @NotNull String toString() {
    StringBuilder s = new StringBuilder("#").append(data.length).append("(");
    forEach((i, e) -> {
      s.append(get(i));
      if (i != fillPointer() - 1) {
        s.append(", ");
      }
    });
    return s.append(")").toString();
  }

  public void forEach(Worker w) {
    for (int i = 0; i < fillPointer(); i++) {
      w.work(data[i]);
    }
  }

  public void forEach(IndexedWorker w) {
    for (int i = 0; i < fillPointer(); i++) {
      w.work(i, data[i]);
    }
  }

  @Contract(pure = true)
  public @NotNull IntArray map(Mapper m) {
    IntArray result = new IntArray(data.length);
    forEach(e -> result.push(m.map(e)));
    return result;
  }

  @Contract(pure = true)
  public @NotNull IntArray map(IndexedMapper m) {
    IntArray result = new IntArray(data.length);
    forEach((i, e) -> result.push(m.map(i, e)));
    return result;
  }

  @Contract(pure = true)
  public @NotNull IntArray filter(Pred pred) {
    IntArray result = new IntArray(data.length);
    forEach(e -> {
      if (pred.call(e)) result.push(e);
    });
    return result;
  }

  @Contract(pure = true)
  public @NotNull IntArray filter(IndexedPred pred) {
    IntArray result = new IntArray(data.length);
    forEach((i, e) -> {
      if (pred.call(i, e)) result.push(e);
    });
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntArray other = (IntArray) o;
    if (fillPointer() != other.fillPointer()) return false;


    for (int i = 0; i < fillPointer(); i++) {
      if (data[i] != other.data[i]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    if (fillPointer() == 0) return 0;
    int result = 1;
    for (int i = 0; i < fillPointer(); i++) {
      result = 31 * result + data[i];
    }
    result = 31 * result + fillPointer;
    return result;
  }

  @FunctionalInterface
  public interface Worker {

    void work(int e);

  }

  @FunctionalInterface
  public interface IndexedWorker {

    void work(int i, int e);

  }

  @FunctionalInterface
  public interface Mapper {

    int map(int e);

  }

  @FunctionalInterface
  public interface IndexedMapper {

    int map(int i, int e);

  }

  @FunctionalInterface
  public interface Pred {

    boolean call(int e);

  }

  @FunctionalInterface
  public interface IndexedPred {

    boolean call(int i, int e);

  }
}
