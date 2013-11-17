package san.jee.lect02;

public class S extends T {

  public S(T coleague) {
    super(coleague);
  }

  @Override
  public void foo() {
    System.out.println("Działa foo() przesłonięte");
  }

}
