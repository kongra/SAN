package san.algo.util;

public class LinkedList {

  private class Node {

    Object first;

    Node rest;

  }

  private Node head;

  public void addToFront(Object obj) {
    Node node = new Node();
    node.first = obj;

    if (!isEmpty()) {
      node.rest = head;
    }
    head = node;
  }

  public void addToEnd(Object obj) {
    Node node = new Node();
    node.first = obj;

    if (isEmpty()) {
      head = node;
    }
    else {
      Node lastNode = head;
      while (lastNode.rest != null) {
        lastNode = lastNode.rest;
      }

      lastNode.rest = node;
    }
  }

  public boolean isEmpty() {
    return head == null;
  }

  public int size() {
    if (isEmpty()) {
      return 0;
    }

    int count = 0;
    Node node = head;
    while (node != null) {
      count += 1;
      node = node.rest;
    }

    return count;
  }

  public String repr() {
    StringBuilder buf = new StringBuilder("(");
    Node node = head;
    while (node != null) {
      buf.append(node.first);
      node = node.rest;
      if (node != null) {
        buf.append(", ");
      }
    }
    return buf.append(")").toString();
  }

}
