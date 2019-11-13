public class Tablice2D {

  public static void main(String[] args) {
    double[][] matrix =
        {{1, 2, 3},
         {4, 5, 6}};

    System.out.println(matrix.length);
    double[] tab = matrix[0];
    System.out.println(tab.length);

    double[][][] tensor =
        {{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
            {{10, 11, 12}, {13, 14, 15}, {16, 17, 18}}};

    var x = tensor[0];
    System.out.println(x.length);
    System.out.println(x[1].length);

  }

}
