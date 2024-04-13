// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Arrays;

interface Program2 {

  static void main(String[] args) {
    final int[] tab1 = { 1, 2, 3, 4 };
    System.out.println(Arrays.toString(tab1));

    final var tab2 = new double[5];
    System.out.println(Arrays.toString(tab2));

    tab2[0] = 4;
    System.out.println(Arrays.toString(tab2));

    // tab2[-1] = 5;
    // tab2[10] = 5;
    // System.out.println(Arrays.toString(tab2));

    System.out.println(tab2.length);
    // tab2.length = 6;

    final var eq1 = tab2.equals(tab2);
    System.out.println(eq1);

    final var tab3 = new double[4];
    final var tab4 = new double[4];
    System.out.println(tab3.equals(tab4));

    System.out.println(Arrays.equals(tab3, tab4));
  }

}
