package san.util;

import java.io.Serializable;

public class Ref<T extends Serializable> {

  public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
  }

  public Ref() {
    ;
  }

  public Ref(T value) {
    this.value = value;
  }

  private T value;

}
