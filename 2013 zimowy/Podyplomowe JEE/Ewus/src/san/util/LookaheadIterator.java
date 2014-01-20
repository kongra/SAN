package san.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LookaheadIterator<T> implements Iterator<T> {

  protected T next;

  @Override
  public boolean hasNext() {
    if (next != null) {
      return true;
    }

    // we havent done it already, so go find the next thing...
    if (!doesHaveNext()) {
      return false;
    }

    return getNext();
  }

  protected boolean doesHaveNext() {
    return true;
  }

  protected boolean getNext() {
    next = loadNext();

    return next != null;
  }

  protected abstract T loadNext();

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    T result = next;

    next = null;

    return result;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}