package san.util.message;

public interface MessageSink {

  void onMessage(Object tag, Object source, Object message);
  
}
