package san.coll;

public interface ISeq<T> {

  T first();

  ISeq<T> rest();
  
  ISeq<T> cons(T obj);

  boolean isEmpty();
  
  ISeq<T> interpose(T separator);

}
