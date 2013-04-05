package san.jipp;

public class ArrayTools {

  public static String str(double[] x) {
    StringBuilder out = new StringBuilder("[");
    
    for (int i = 0; i < x.length; i++) {
      out.append(x[i]);
      if(i != x.length - 1) {
        out.append(", ");
      }
    }
    
    return out.append("]").toString();
  }

}
