package san.algo;

import san.fn.Unary;

public class List {

  private static class Cons {

    final Object first;

    final Object rest;

    Cons(Object first, Object rest) {
      this.first = first;
      this.rest = rest;
    }

    @Override
    public String toString() {
      return new StringBuilder("(").append(first).append(" . ").append(rest)
          .append(")").toString();
    }
  }

  public static Object cons(Object first, Object rest) {
    return new Cons(first, rest);
  }

  public static Object first(Object cons) {
    if (cons == null) {
      return null;
    }
    return ((Cons) cons).first;
  }

  public static Object rest(Object cons) {
    if (cons == null) {
      return null;
    }
    return ((Cons) cons).rest;
  }

  public static void dolist(Object lst, Unary work) {
    if (lst != null) {
      work.call(lst);
      dolist(List.rest(lst), work);
    }
  }

  public static String show(Object lst) {
    final StringBuilder s = new StringBuilder("(");
    dolist(lst, new Unary() {
      @Override
      public Object call(Object theList) {
        s.append(List.first(theList));
        if(List.rest(theList) != null) {
          s.append(", ");
        }        
        return null;
      }
    });
    return s.append(")").toString();
  }
  
  public static Object create(Object... elements) {
    Cons c = null;
    for(int i = elements.length - 1; i != -1; i--) {
      c = new Cons(elements[i], c);
    }    
    return c;
  }
  
  public static Object map(Unary f, Object lst) {
    if(lst == null) {
      return null;
    }
    
    return List.cons(f.call(List.first(lst)), map(f, List.rest(lst)));
  }
}
