package san.algo;

public interface ISeq {

  Object first();
  
  ISeq rest();
  
  boolean isEmpty();
  
  ISeq cons(Object x);
  
}
