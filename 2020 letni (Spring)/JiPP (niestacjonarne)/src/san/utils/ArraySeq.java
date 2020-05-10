package san.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Function;

// Integer(int n) { ... }
// new Integer(5);
// Integer - konstruktor
// n - parametr formalny
// 5 - argument

// ArraySeq<String> - nowy typ
// ArraySeq - konstruktor typów
// T - zmienna typowa (type-variable)
// String - argument typowy
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
    if (i >= count) throw new ArrayIndexOutOfBoundsException();
    return array[i];
  }

  public ArraySeq<T> set(int i, T e) {
    if (i >= count) throw new ArrayIndexOutOfBoundsException();
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

  @NotNull
  @Override
  public Iterator<T> iterator() {
    return new It();
  }

  private class It implements Iterator<T> {

    private int i;

    @Override
    public boolean hasNext() {
      return i < count();
    }

    @Override
    public T next() {
      return array[i++];
    }
  }

  public <R> ArraySeq<R> map(Function<T, R> f) {
    ArraySeq<R> result = new ArraySeq<>(this.array.length);
    for (var e : this) {
      result.add(f.apply(e));
    }
    return result;
  }
}
