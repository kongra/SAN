package san.coll;

class Null implements ISeq {

  static final Null INSTANCE = new Null();

  @Override
  public Object first() {
    return null;
  }

  @Override
  public ISeq rest() {
    return this;
  }

  @Override
  public ISeq cons(Object obj) {
    return new LinkedSeq(obj, this);
  }

  @Override
  public String toString() {
    return "()";
  }

  private Null() {
    ;
  }
}
