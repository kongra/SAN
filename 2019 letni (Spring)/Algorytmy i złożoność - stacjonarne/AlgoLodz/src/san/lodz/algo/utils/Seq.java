package san.lodz.algo.utils;

public interface Seq<T> {

  T first();

  Seq<T> rest();

}
