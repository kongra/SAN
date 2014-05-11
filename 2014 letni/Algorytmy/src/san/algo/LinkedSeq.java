package san.algo;

public class LinkedSeq implements ISeq {

  public static ISeq of(Object... elements) {
    ISeq result = EmptySeq.INSTANCE;
    for(int i = elements.length - 1; i > -1; i--) {
      result = result.cons(elements[i]);
    }
    
    return result;
  }

  private final Object first;

  private final ISeq rest;

  LinkedSeq(Object first, ISeq rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public Object first() {
    return first;
  }

  @Override
  public ISeq rest() {
    return rest;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public ISeq cons(Object x) {
    return new LinkedSeq(x, this);
  }

}
