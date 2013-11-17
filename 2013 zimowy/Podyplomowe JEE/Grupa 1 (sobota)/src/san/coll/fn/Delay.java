package san.coll.fn;

public class Delay {

  public static Delay create(NoArg producer) {
    return new Delay(producer);
  }

  private final NoArg producer;

  private boolean produced;
  
  private Object value;

  private Delay(NoArg producer) {
    this.producer = producer;
  }

  public Object call() {
    if (!produced) {
      value = producer.call();
      produced = true;
    }
    return value;
  }

}
