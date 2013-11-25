package san.prog.arrays;

public class Lect03 {

  public static void main(String[] args) {
    // test1();
    
    double[][] macierz = {{1, 2, 3},
                          {4, 5, 6}, 
                          {7, 8, 9}};

    System.out.println(ArrayTools.toString(macierz[0]));
    System.out.println(ArrayTools.toString(macierz[1]));
    System.out.println(ArrayTools.toString(macierz[2]));
    
    System.out.println(macierz[2][1]);
    
    System.out.println(ArrayTools.toString(macierz));
  }

  private static void test1() {
    double[] tablica = {
        3.14, 2.71, 6.0, -256.3, 1 / 3
    };
    System.out.println(tablica[4]);
    System.out.println(ArrayTools.toString(tablica));
  }

}
