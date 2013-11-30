package san.jee.lect02;

public class TestPoli {

  public static void bar(final T obj) {
    obj.foo();
  }
  
  public static void main(final String[] args) {
    @SuppressWarnings("unused")
    T obj = T.create();
    bar(new S(null));

  }

}
