package san.coll.fn;

public class Delay<T> {

  public static <S> Delay create(NoArg<S> producer) {
    return new Delay<S>(producer);
  }

  private final NoArg<T> producer;

  private boolean produced;
  
  private T value;

  private Delay(NoArg<T> producer) {
    this.producer = producer;
  }

  public T call() {
    if (!produced) {
      value = producer.call();
      produced = true;
    }
    return value;
  }

}
