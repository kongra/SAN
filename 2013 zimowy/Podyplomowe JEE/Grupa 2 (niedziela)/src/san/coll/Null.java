package san.coll;

import java.util.Iterator;

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

  @Override
  public Iterator iterator() {
    return Utils.iterator(this);
  }
  
  private Null() {
    ;
  }
}
