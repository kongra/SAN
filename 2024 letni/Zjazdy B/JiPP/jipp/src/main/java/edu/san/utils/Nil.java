// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

final class Nil<E> implements Seq<E> {

  @SuppressWarnings("rawtypes")
  static final Nil INSTANCE = new Nil();

  @Override
  public E first() {
    throw new IllegalArgumentException();
  }

  @Override
  public Seq<E> rest() {
    return this;
  }

  @Override
  public Seq<E> cons(E x) {
    return new LinkedSeq<>(x, this);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public String toString() {
    return Seq.asString(this);
  }

  private Nil() {}

}
