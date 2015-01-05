package san.conc04.msg;

public class TestPump {

  static MessageCrap p = new MessageCrap();

  static Listener l1 = new Listener() {
    @Override
    public void onMessage(Object msg) {
      System.out.println("Mam wiadomość " + msg);
      Runnable r = new Runnable() {
        @Override
        public void run() {
          p.unregister(l1);
        }
      };
      Thread t = new Thread(r);
      t.start();
      try {
        t.join();
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }      
    }
  };

  static Listener l2 = new Listener() {
    @Override
    public void onMessage(Object msg) {
      System.out.println("Mam komunikat " + msg);
    }
  };

  public static void main(String[] args) {
    p.register(l1);
    p.register(l2);
    p.fire("A");
    p.fire("B");
  }

}
