package san.lect05;

import san.util.message.MessagePump;
import san.util.message.MessageSink;

public class TestPumps {

  public static void main(String[] args) {
    final MessagePump pump = MessagePump.sync();

    MessageSink s1 = new MessageSink() {
      @Override
      public void onMessage(Object tag, Object source, Object message) {
        System.out.println("s1 " + tag + " " + source + " " + message);
      }
    };

    MessageSink s2 = new MessageSink() {

      final MessageSink s = this;

      @Override
      public void onMessage(final Object tag, final Object source,
          final Object message) {
        Runnable task = new Runnable() {
          @Override
          public void run() {
            pump.unregister(s, "abc");
            System.out.println("s2 po wyrejestrowaniu " + tag + " " + source + " " + message);
          }
        };
        Thread t = new Thread(task);
        t.start();
        
        try {
          t.join();
        }
        catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    };

    pump.register(s1, Integer.class, "abc");
    pump.register(s2, "abc");

    pump.send("abc", TestPumps.class, "Wiadomość 1");
    pump.send(Integer.class, TestPumps.class, "Wiadomość 1");
  }
}
