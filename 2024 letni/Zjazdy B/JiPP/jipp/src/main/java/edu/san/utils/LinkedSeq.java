// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

final class LinkedSeq<E> implements Seq<E> {

  private final E first;

  private final Seq<E> rest;

  // (1, 2, 3, 4)
  // [1] -> [2] -> [3] -> [4] -> Nil

  LinkedSeq(E first, Seq<E> rest) {
    this.first = first;
    this.rest  = rest;
  }

  @Override
  public E first() {
    return first;
  }

  @Override
  public Seq<E> rest() {
    return rest;
  }

  @Override
  public Seq<E> cons(E x) {
    return new LinkedSeq<>(x, this);
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public String toString() {
    return Seq.asString(this);
  }
}
