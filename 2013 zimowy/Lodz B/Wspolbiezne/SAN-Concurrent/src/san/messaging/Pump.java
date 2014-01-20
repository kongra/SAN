package san.messaging;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Pump implements IPump {

  public static IPump createSync() {
    return new SynchronousPump();
  }

  public static IPump createAsync() {
    return new AsynchronousPump();
  }

  private final Map<Class, Set<Target>> registry = new HashMap<>();

  @Override
  public final synchronized void register(Target target, Class... messageTypes) {
    for (Class c : messageTypes) {
      c = c != null ? c : Void.class;
      register(c, target);
    }
  }

  private void register(Class c, Target target) {
    Set<Target> targets = registry.get(c);
    if (targets == null) {
      targets = new HashSet<>();
      registry.put(c, targets);
    }
    targets.add(target);
  }

  @Override
  public final void send(Object message, Object source) {
    final Set<Target> copy;

    synchronized (this) {
      Class c = message != null ? message.getClass() : Void.class;
      Set<Target> targets = registry.get(c);
      if (targets == null || targets.isEmpty()) {
        return;
      }
      copy = new HashSet<>(targets);
    }

    sendAll(copy, message, source);
  }

  protected abstract void sendAll(Set<Target> targets, Object message,
      Object source);

}
