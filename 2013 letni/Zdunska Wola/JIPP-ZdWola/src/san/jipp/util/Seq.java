package san.jipp.util;

public interface Seq {
  
  Seq addToFront(Object obj);
  
  Object first();
  
  Seq rest();

  boolean isEmpty();
  
}