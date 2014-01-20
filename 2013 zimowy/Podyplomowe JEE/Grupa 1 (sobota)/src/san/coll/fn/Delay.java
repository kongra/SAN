package san.coll.fn;

public class Delay<T> {

  public static <S> Delay create(NoArg<S> producer) {
    return new Delay<S>(producer);
  }

  private NoArg<T> producer;

  private T value;

  private Delay(NoArg<T> producer) {
    this.producer = producer;
  }

  public T call() {
    synchronized (this) {
      if (producer == null) {
        return value;
      }
    }

    T val = producer.call();
    
    synchronized (this) {
      value = val;
      producer = null;
      return value;
    }
  }

}
