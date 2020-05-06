package san.utils;

public final class IntArray {

  private final int[] data;

  private int fillPointer;

  private IntArray(int length) {
    data = new int[length];
  }

  private IntArray(int length, int initialValue) {
    data = new int[length];
    for (int i = 0; i < length; i++) {
      data[i] = initialValue;
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

  public void forEach(Worker w) {

  }

  public IntArray map(Mapper m) {
    return null;
  }

  @FunctionalInterface
  public interface Worker {

    void work(int e);

  }

  @FunctionalInterface
  public interface Mapper {

    int map(int e);

  }

}
