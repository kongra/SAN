package san.pp;

public class Program01 {

  static int sumNaturals(int n) {
    int i = 0;
    int s = 0;
    while(i <= n) {
      s = s + i;
      i = i + 1;
    }
    return s;
  }

  static int sumNats(int n, int i, int s) {
    if (i <= n) {
      return sumNats(n, i + 1, s + i);
    } else {
      return s;
    }
  }

  public static void main(String[] args) {
    System.out.println(sumNaturals(5));
    System.out.println(sumNats(5, 0, 0));
  }

}
