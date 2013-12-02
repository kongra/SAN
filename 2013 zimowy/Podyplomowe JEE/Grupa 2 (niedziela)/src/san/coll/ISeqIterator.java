package san.coll;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ISeqIterator implements Iterator {

  private ISeq coll;

  ISeqIterator(ISeq coll) {
    this.coll = coll;
  }

  @Override
  public boolean hasNext() {
    return this.coll != ISeq.NULL;
  }

  @Override
  public Object next() {
    if (this.coll == ISeq.NULL) {
      throw new NoSuchElementException();
    }
    Object obj = coll.first();
    coll = coll.rest();
    return obj;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

}
