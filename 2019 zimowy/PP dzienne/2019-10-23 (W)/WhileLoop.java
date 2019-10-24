class WhileLoop {

  static void printNextInt(final int i, final int limit) {
    if (i <= limit) {
      System.out.println(i);
      printNextInt(i + 1, limit);
    }
  }

  public static void main(String[] args) {
    printNextInt(1, 100000);

    // int i = 1;
    // while(true) {
    //   System.out.println(i);
    //   i = i + 1;
    // }
  }

}
