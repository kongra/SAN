package san.conc.msg;

public class Test {

  static MessagePump pump = new MessagePump();

  static class ListenerA implements Listener {
    @Override
    public void onMessage(Object msg) {
      System.out.println("A :: Dostałem komunikat " + msg);
      // System.out.println("A :: teraz ...");
      // pump.unregister(this);
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          System.out.println("A::Próbuję się wyrejestrować...");
          pump.unregister(ListenerA.this);
        }
      });

      t.start();
      try {
        t.join();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      System.out.println("Udało się wyrejestrować");
    }
  }

  static class ListenerB implements Listener {
    @Override
    public void onMessage(Object msg) {
      System.out.println("B :: Dostałem komunikat " + msg);
    }
  }

  public static void main(String[] args) {
    pump.register(new ListenerA());
    pump.register(new ListenerB());

    pump.fire("komunikat1");
    pump.fire("komunikat2");
  }

}
