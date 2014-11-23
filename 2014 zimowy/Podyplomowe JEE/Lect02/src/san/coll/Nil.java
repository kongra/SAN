package san.coll;

public final class Nil implements ISeq {

  private Nil() {
  }

  @Override
  public Object first() {
    throw new UnsupportedOperationException("Should never happen!");
  }

  @Override
  public ISeq rest() {
    throw new UnsupportedOperationException("Should never happen!");
  }

  @SuppressWarnings("unchecked")
  @Override
  public ISeq cons(Object first) {
    return new LinkedSeq(first, this);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public String toString() {
    return Seqs.toString(this);
  }

  public static final Nil nil = new Nil();
}
