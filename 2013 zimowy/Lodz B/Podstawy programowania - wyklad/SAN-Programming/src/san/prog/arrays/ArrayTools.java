package san.prog.arrays;

public class ArrayTools {

  public static String toString(double[] array) {
    StringBuilder buf = new StringBuilder("[");
    
    for (int i = 0; i < array.length; i++) {
      buf.append(array[i]);
      if (i != array.length - 1) {
        buf.append(", ");
      }
    }

    return buf.append("]").toString();
  }

  public static String toString(double[][] matrix) {
    // TODO Auto-generated method stub
    return null;
  }

}
