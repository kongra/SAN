package san.math;

public interface Num<T extends Num<T>> {

  T add(Num<? extends T> other);
  
//  T sub(Num<? extends T> other);
//  
//  T mul(Num<? extends T> other);
//  
//  T div(Num<? extends T> other);

}
