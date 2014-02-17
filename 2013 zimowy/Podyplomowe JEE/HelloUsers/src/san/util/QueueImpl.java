package san.util;

import java.util.LinkedList;
import java.util.List;

public class QueueImpl<T> implements Queue<T> {

  private final List<T> data;

  public QueueImpl() {
    this(new LinkedList<T>());
  }

  public QueueImpl(List<T> data) {
    this.data = data;
  }

  @Override
  public void put(T element) {
    data.add(0, element);
  }

  @Override
  public T get() {
    return data.get(lastIndex());
  }

  private int lastIndex() {
    return data.size() - 1;
  }
  
  @Override
  public T get(T notFound) {
    if(isEmpty()) {
      return notFound;
    }
    return get();
  }

  @Override
  public T remove() {
    return data.remove(lastIndex());
  }

  @Override
  public boolean isEmpty() {
    return data.isEmpty();
  }

  
}
