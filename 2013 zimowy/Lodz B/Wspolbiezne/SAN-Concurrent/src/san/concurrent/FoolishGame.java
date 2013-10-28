package san.concurrent;

public class FoolishGame {

  public void problems(FoolishGame foreign) {
    synchronized (this) {
      foreign.foo();
    }
  }

  public void foo() {
    // TODO Auto-generated method stub
  }

}
