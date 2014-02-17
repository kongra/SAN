package san.util;

public interface Queue<T> {

  void put(T element);
  
  T get();
  
  T get(T notFound);
  
  T remove();
  
  boolean isEmpty();
}
