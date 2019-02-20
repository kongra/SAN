package san.animals.boxes2;

public class Box<T> {

  private T obj;

  public T get() {
    T o = this.obj;
    this.obj = null;
    return o;
  }

  public void put(T obj) {
    this.obj = obj;
  }

}
