package san.coll;

public interface ISeq<T> {

  T first();

  ISeq<T> rest();

  ISeq<T> cons(T first);

  boolean isEmpty();

}
