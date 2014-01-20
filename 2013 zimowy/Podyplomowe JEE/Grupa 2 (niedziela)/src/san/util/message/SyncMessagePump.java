package san.util.message;

class SyncMessagePump extends MessagePump {

  @Override
  public void send(Object tag, Object source, Object message) {
    for (MessageSink messageSink : sinks(tag)) {
      messageSink.onMessage(tag, source, message);
    }
  }

}
