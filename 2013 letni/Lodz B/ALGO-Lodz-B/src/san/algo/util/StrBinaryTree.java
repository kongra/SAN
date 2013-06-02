package san.algo.util;

public class StrBinaryTree {

  private static class Node {

    final String value;

    Node left;

    Node right;

    Node(String value) {
      this.value = value;
    }

  }

  private static enum Order {
    LEFT, RIGHT, NO
  }

  private static class Location {

    final Order order;

    final Node parent;

    Location(Order order, Node parent) {
      this.order = order;
      this.parent = parent;
    }

  }

  private Node root;

  public boolean add(String value) {
    if (root == null) {
      root = new Node(value);
      return true;
    }

    Location location = searchParent(root, value);
    if (location.order == Order.NO) {
      return false;
    }

    Node newNode = new Node(value);
    if (location.order == Order.LEFT) {
      location.parent.left = newNode;
    }
    else {
      location.parent.right = newNode;
    }
    return true;
  }

  private Location searchParent(Node start, String value) {
    int comp = start.value.compareTo(value);
    if (comp == 0) {
      return new Location(Order.NO, start);
    }
    if (comp < 0) {
      if (start.right == null) {
        return new Location(Order.RIGHT, start);
      }
      return searchParent(start.right, value);
    }
    if (start.left == null) {
      return new Location(Order.LEFT, start);
    }
    return searchParent(start.left, value);
  }

}
