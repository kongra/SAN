package san.util.message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MessagePump {

  public static MessagePump sync() {
    return new SyncMessagePump();
  }

  public static MessagePump async() {
    return new AsyncMessagePump();
  }

  public abstract void send(final Object tag, final Object source,
      final Object message);

  // }

  public void send(Object source, Object message) {
    throw new UnsupportedOperationException();
  }

  public final void register(MessageSink sink, Object... tags) {
    if (tags.length == 0) {
      throw new IllegalArgumentException("Określ przynajmniej jeden tag");
    }

    synchronized (this) {
      for (Object tag : tags) {
        Set<MessageSink> sinks = registry.get(tag);
        if (null == sinks) {
          sinks = new HashSet<>();
          registry.put(tag, sinks);
        }

        sinks.add(sink);
      }
    }
  }

  public final synchronized void unregister(MessageSink sink, Object... tags) {
    if (tags.length == 0) {
      for (Set<MessageSink> sinks : registry.values()) {
        sinks.remove(sink);
      }
    }
    else {
      for (Object tag : tags) {
        Set<MessageSink> sinks = registry.get(tag);
        if (null != sinks) {
          sinks.remove(sink);
        }
      }
    }
  }

  public synchronized final Iterable<MessageSink> sinks(Object tag) {
    Set<MessageSink> sinks = registry.get(tag);
    if (null == sinks) {
      return NOSINKS;
    }
    return new HashSet<>(sinks);
  }

  private static final Set<MessageSink> NOSINKS = new HashSet<>();

  protected final Map<Object, Set<MessageSink>> registry = new HashMap<>();
}
