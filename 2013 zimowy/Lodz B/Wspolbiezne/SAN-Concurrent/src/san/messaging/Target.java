package san.messaging;

public interface Target {

  void onMessage(Object message, Object source);

}
