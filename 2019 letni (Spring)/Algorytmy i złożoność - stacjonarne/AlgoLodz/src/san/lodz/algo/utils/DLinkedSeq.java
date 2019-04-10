package san.lodz.algo.utils;

import san.lodz.math.Binary;

public class DLinkedSeq<T> {

  private Node head;

  private Node tail;

  public void addHead(T x) {
    Node node = new Node(null, null, x);
    if (isEmpty()) {
      this.head = this.tail = node;
    } else {
      this.head.left = node;
      node.right = this.head;
      this.head = node;
    }
  }

  public void addTail(T x) {
    Node node = new Node(null, null, x);
    if (isEmpty()) {
      this.head = this.tail = node;
    } else {
      this.tail.right = node;
      node.left = this.tail;
      this.tail = node;
    }
  }

  public T head() {
    if (isEmpty())
      throw new UnsupportedOperationException();

    return this.head.value;
  }

  public T tail() {
    if (isEmpty())
      throw new UnsupportedOperationException();

    return this.tail.value;
  }

  public boolean isEmpty() {
    return this.head == null;
  }

  public void forEach(Binary<T, Boolean, Void> body) {
    Node current = this.head;
    while(null != current) {
      body.call(current.value, current == this.tail);
      current = current.right;
    }
  }

  public void forEachReverse(Binary<T, Boolean, Void> body) {
    Node current = this.tail;
    while(null != current) {
      body.call(current.value, current == this.head);
      current = current.left;
    }
  }

  public String asString() {
    StringBuilder buf = new StringBuilder("(");
    this.forEach((e, isLast) -> {
      buf.append(e);
      if (!isLast) {
        buf.append(",");
      }
      return null;
    });
    return buf.append(")").toString();
  }

  private class Node {
    Node left;
    Node right;
    T value;

    private Node(Node left, Node right, T value) {
      this.left = left;
      this.right = right;
      this.value = value;
    }
  }

}
