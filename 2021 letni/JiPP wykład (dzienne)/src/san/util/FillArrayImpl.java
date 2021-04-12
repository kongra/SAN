package san.util;

public class FillArrayImpl implements FillArray {

  private int fillPointer;

  private final int[] data;

  public FillArrayImpl(int maxLength) {
    data = new int[maxLength];
  }

  @Override
  public FillArray add(int x) {
    data[fillPointer++] = x;
    return this;
  }

  @Override
  public int get(int i) {
    if (i >= length()) {
      throw new ArrayIndexOutOfBoundsException(i);
    }
    return data[i];
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
