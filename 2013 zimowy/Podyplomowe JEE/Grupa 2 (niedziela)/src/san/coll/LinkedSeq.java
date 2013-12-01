package san.coll;

public final class LinkedSeq implements ISeq {

  public static ISeq create(Object... elements) {
    ISeq result = ISeq.NULL;
    for(int i = elements.length - 1; i != -1; i--) {
      result = result.cons(elements[i]);
    }

    return result;
  }

  @Override
  public Object first() {
    return this.first;
  }

  @Override
  public ISeq rest() {
    return this.rest;
  }

  @Override
  public ISeq cons(Object obj) {
    return new LinkedSeq(obj, this);
  }

  private final Object first;

  private final ISeq rest;

  LinkedSeq(Object first, ISeq rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public String toString() {
    return Utils.toString(this);
  }

  
}
