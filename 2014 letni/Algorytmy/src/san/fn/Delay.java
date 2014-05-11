package san.fn;

public class Delay implements Nullary {

  public static Delay of(Nullary generator) {
    return new Delay(generator);
  }

  private Nullary generator;

  private Object value;

  private Delay(Nullary generator) {
    this.generator = generator;
  }

  @Override
  public Object call() {
    if (generator != null) {
      value = generator.call();
      generator = null;
    }
    return value;
  }

}
