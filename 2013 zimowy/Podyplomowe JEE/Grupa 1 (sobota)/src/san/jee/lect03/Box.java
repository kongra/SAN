package san.jee.lect03;

public class Box<T> {

  private T obj;

  public Box(T obj) {
    this.obj = obj;
  }

  public Box() {
  }

  public T get() {
    return this.obj;
  }

  public T set(T obj) {
    T result = this.obj;
    this.obj = obj;
    return result;
  }
}
