package san.fn;

public class Delay implements Nullary {

  public static Delay of(Nullary provider) {
    return new Delay(provider);
  }

  @Override
  public Object call() {
    if (pending) {
      value = provider.call();
      pending = false;
    }

    return value;
  }

  private boolean pending = true;

  private Object value;

  private final Nullary provider;

  private Delay(Nullary provider) {
    this.provider = provider;
  }

}
