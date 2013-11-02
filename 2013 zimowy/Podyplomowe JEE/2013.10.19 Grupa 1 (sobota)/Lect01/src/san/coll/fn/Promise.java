package san.coll.fn;

public class Promise {

  public static Promise create(NoArg producer) {
    return new Promise(producer);
  }

  private final NoArg producer;

  private boolean produced;
  
  private Object value;

  private Promise(NoArg producer) {
    this.producer = producer;
  }

  public Object call() {
    if (!produced) {
      value = producer.call();
      produced = true;
      System.out.println("Wyprodukowałem " + value);
    }
    return value;
  }

}
