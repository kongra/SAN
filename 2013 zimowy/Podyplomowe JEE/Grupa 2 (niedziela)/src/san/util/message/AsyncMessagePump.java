package san.util.message;

class AsyncMessagePump extends MessagePump {

  @Override
  public void send(final Object tag, final Object source, final Object message) {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        for (MessageSink messageSink : sinks(tag)) {
          messageSink.onMessage(tag, source, message);
        }
      }

    };

    new Thread(task).start();
  }

}
