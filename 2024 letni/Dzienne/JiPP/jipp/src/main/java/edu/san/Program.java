// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program {

  public static void main(String[] args) {
    byte b1 = 3;
    short s1 = b1;

    short s2 = 1000;
    // byte b2 = s2;

    long n1 = Long.MAX_VALUE - 4;
    float f1 = n1;

    System.out.println("n1=" + n1);
    System.out.println("f1=" + f1);

    long n2 = (long) f1;
    System.out.println("n2=" + n2);

    byte[] byteArray1 = new byte[10];
    System.out.println(byteArray1);
    System.out.println(byteArray1[0]);

    int[] intArray = new int[100];
    intArray[0] = 4;
    System.out.println(intArray[0]);

    int[] intArray2 = { 1, 2, 7, 5, 6, 0 };
    System.out.println(intArray2[3]);

    for (int i = 0; i < intArray2.length; i++) {
      var e = intArray2[i];
      System.out.println(e);
    }

    intArray2 = addToArray(intArray2, 123);
    for (int e : intArray2) {
      System.out.println(e);
    }

  }

  static int[] addToArray(int[] array, int value) {
    int n = array.length;
    int[] newArray = new int[n + 1];
    newArray[n] = value;
    System.arraycopy(array, 0, newArray, 0, array.length);
    return newArray;
  }

}
