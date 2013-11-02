package san.coll;

import san.coll.fn.Unary;

public class LinkedSeq implements ISeq {

  public static ISeq create(Object... elements) {
    ISeq result = EMPTY;
    for (int i = elements.length - 1; i >= 0; i--) {
      result = result.cons(elements[i]);
    }
    return result;
  }

  public static final ISeq EMPTY = new ISeq() {
    @Override
    public Object first() {
      return null;
    }

    @Override
    public ISeq rest() {
      return this;
    }

    @SuppressWarnings("synthetic-access")
    @Override
    public ISeq cons(Object obj) {
      return new LinkedSeq(obj, this);
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    
  };

  private final Object first;

  private final ISeq rest;

  private LinkedSeq(Object first, ISeq rest) {
    this.first = first;
    this.rest = rest;
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

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public String toString() {
    final StringBuilder buf = new StringBuilder("(");

    Utils.doSeq(this, new Unary() {
      @Override
      public Object call(Object element) {
        buf.append(element);
        return null;
      }
    });

    return buf.append(")").toString();
  }

}
