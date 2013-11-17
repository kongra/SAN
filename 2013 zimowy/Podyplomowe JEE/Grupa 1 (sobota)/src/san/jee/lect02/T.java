package san.jee.lect02;

public class T {

  final T coleague;
  
  public static T create() {
    return create(null);
  }
  
  public static T create(final T coleague) {
    return new T(coleague);
  }
  
  public void foo() {
    System.out.println("Działa foo()");
  }
  
  public T(final T coleague) {
    this.coleague = coleague;
  }
  
}
