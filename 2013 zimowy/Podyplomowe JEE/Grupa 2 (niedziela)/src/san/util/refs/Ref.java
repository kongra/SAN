package san.util.refs;

public class Ref<T> {

  public static <S> Ref<S> initially(S value) {
    return new Ref<S>(value);
  }

  private Ref(T value) {
    this.value = value;
  }

  public T value;

}
