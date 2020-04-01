package san.jipp.genericgame;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Objects;

public class ArraySeq<T> implements Iterable<T> {

  private final T[] array;

  private int count;

  public ArraySeq(int maxCount) {
    array = (T[]) new Object[maxCount];
  }

  public ArraySeq(int maxCount, @NotNull T... startElements) {
    this(maxCount);
    count = startElements.length;
    System.arraycopy(startElements, 0, array, 0, count);
  }

  public int count() {
    return count;
  }

  public boolean isEmpty() {
    return 0 == count();
  }

  public T get(int i) {
    return array[i];
  }

  public ArraySeq<T> set(int i, T e) {
    array[i] = e;
    return this;
  }

  public ArraySeq<T> add(T e) {
    array[count] = e;
    count += 1;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("#").append(array.length).append("(");
    for (int i = 0; i < count; i++) {
      s.append(get(i));
      if (i != count - 1) {
        s.append(", ");
      }
    }
    return s.append(")").toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArraySeq<T> other = (ArraySeq<T>) o;
    if (count != other.count) {
      return false;
    }
    for (int i = 0; i < count; i++) {
      if (!Objects.equals(get(i), other.get(i))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    for (int i = 0; i < count; i++) {
      T e = get(i);
      result = 31 * result + (e == null ? 0 : e.hashCode());
    }
    return result;
  }

  @NotNull
  @Override
  public Iterator<T> iterator() {
    return new Iter();
  }

  private class Iter implements Iterator<T> {

    int i;

    @Override
    public boolean hasNext() {
      return i != count;
    }

    @Override
    public T next() {
      T e = get(i);
      i += 1;
      return e;
    }
  }
}
