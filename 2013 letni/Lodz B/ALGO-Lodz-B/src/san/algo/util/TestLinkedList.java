package san.algo.util;

public class TestLinkedList {

  public static void main(String[] args) {
    LinkedList coll = new LinkedList();
    System.out.println(coll.repr());
    
    coll.addToFront(1);
    coll.addToEnd(2);
    coll.addToEnd(3);
    coll.addToFront(4);
    
    System.out.println(coll.repr());
    
//    System.out.println(coll.isEmpty());
//    System.out.println(coll.size());
//    
//    
//    System.out.println(coll.isEmpty());
//    System.out.println(coll.size());
  }

}
