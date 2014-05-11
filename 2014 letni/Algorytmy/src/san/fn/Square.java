package san.fn;

public class Square implements Unary {

  @Override
  public Object call(Object arg) {
    Number x = (Number) arg;
    return x.longValue() * x.longValue();
  }

}
