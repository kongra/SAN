package san.util;

public class FillArrayImpl<T> implements FillArray<T> {

  private int fillPointer;

  private final Object[] data;

  public FillArrayImpl(int maxLength) {
    data = new Object[maxLength];
  }

  @Override
  public FillArray add(T x) {
    data[fillPointer++] = x;
    return this;
  }

  @Override
  public T get(int i) {
    if (i >= length()) {
      throw new ArrayIndexOutOfBoundsException(i);
    }
    return (T) data[i];
  }

  @Override
  public int length() {
    return fillPointer;
  }

  @Override
  public int maxLength() {
    return data.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("#").append(maxLength()).append("[");

    for (int i = 0; i < fillPointer; i++) {
      s.append(data[i]);
      if (i != fillPointer - 1) {
        s.append(", ");
      }
    }
    return s.append("]").toString();
  }
}
