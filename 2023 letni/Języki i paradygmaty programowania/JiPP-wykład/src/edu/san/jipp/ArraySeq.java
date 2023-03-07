// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp;

import java.util.Iterator;
import java.util.Objects;

public class ArraySeq implements Iterable {

  private final Object[] array;

  private int count;

  public ArraySeq(int maxCount) {
    array = new Object[maxCount];
  }

  public ArraySeq(int maxCount, Object... startElements) {
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

  public Object get(int i) {
    return array[i];
  }

  public ArraySeq set(int i, Object e) {
    array[i] = e;
    return this;
  }

  public ArraySeq add(Object e) {
    array[count] = e;
    count += 1;
    return this;
  }

  @Override
  public String toString() {
    final var s = new StringBuilder("#").append(array.length)
        .append("(");
    for (var i = 0; i < count; i++) {
      s.append(get(i));
      if (i != count - 1) {
        s.append(", ");
      }
    }
    return s.append(")").toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final var other = (ArraySeq) o;
    if (count != other.count)
      return false;
    for (var i = 0; i < count; i++) {
      if (!Objects.equals(get(i), other.get(i)))
        return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    var result = 1;
    for (var i = 0; i < count; i++) {
      final var e = get(i);
      result = 31 * result + (e == null ? 0 : e.hashCode());
    }
    return result;
  }

  @Override
  public Iterator iterator() {
    return new Iter();
  }

  private class Iter implements Iterator {

    int i;

    @Override
    public boolean hasNext() {
      return i != count;
    }

    @Override
    public Object next() {
      final var e = get(i);
      i += 1;
      return e;
    }
  }
}