package san.algo;

public class EmptySeq implements ISeq {

  public static final ISeq INSTANCE = new EmptySeq();

  private EmptySeq() {
  }

  @Override
  public Object first() {
    return null;
  }

  @Override
  public ISeq rest() {
    return this;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public ISeq cons(Object x) {
    return new LinkedSeq(x, INSTANCE);
  }

  @Override
  public String toString() {
    return "()";
  }

}
