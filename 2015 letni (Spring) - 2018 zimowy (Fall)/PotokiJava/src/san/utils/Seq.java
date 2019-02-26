package san.utils;

public interface Seq<T> {

  T first();

  Seq<T> rest();

  Seq<T> cons(T obj);

  boolean isEmpty();

}
