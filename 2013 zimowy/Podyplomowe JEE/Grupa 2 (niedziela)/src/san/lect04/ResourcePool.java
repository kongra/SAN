package san.lect04;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool<T> {

  public ResourcePool(@SuppressWarnings("unchecked") T... resources) {
    buffer = new ArrayList<>(resources.length);
    for (T t : resources) {
      buffer.add(new Element(t));
    }
  }

  public synchronized T get() {
    while (true) {
      for (Element e : buffer) {
        if (!e.inUse) {
          e.inUse = true;
          return e.value;
        }
      }

      try {
        this.wait();
      }
      catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public synchronized void release(T resource) {
    for (Element e : buffer) {
      if (e.value.equals(resource) && e.inUse) {
        e.inUse = false;
        this.notify();
      }
    }
  }

  private class Element {

    final T value;

    boolean inUse;

    Element(T value) {
      this.value = value;
    }

  }

  private final List<Element> buffer;
}
