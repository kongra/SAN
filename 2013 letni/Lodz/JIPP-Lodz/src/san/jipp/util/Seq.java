package san.jipp.util;

public interface Seq<T> {

  Seq addToFront(T obj);

  T first();

  Seq<T> rest();

  boolean isEmpty();

}