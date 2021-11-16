class Program {

  // Zadanie 1. Napisz program, który liczy średnią wartość z ciągu
  //            liczb: 4, 3, 6, 7, 8, 1, 1, 4
  public static void main(String[] args) {
    double x1 = 4;
    double x2 = 3;
    double x3 = 6;
    // ...
    double xn = 4; // TO PODEJŚCIE JEST B. NIEEFEKTYWNE

    // TABLICA:
    // tab1 -----> |4|3|6|7|8|1|1|4| (obiekt: tablica 8 elementów typu double)
    //      -----> REFERENCJA (~WSKAŹNIK) DO OBIEKTU
    // tab1        JEST NAZWĄ ZMIENNEJ (typu tablicowego)
    //
    // * Obiekt tablicowy wskazywany przez tab1 umieszczony jest na STERCIE maszyny
    //   wirtualnej (JVM). Sterta to obszar tzw. dynamicznej alokacji pamięci
    //   (w odróżnieniu od tzw. stosu - obszaru alokacji statycznej).

    double[] tab1;
    // Definicja zmiennej tab1. Jest to zmienna typu tablicowego, w której
    // możliwe jest przechowywanie referencji do tablicy o elementach typu double.
    // W tym miejscu żadna tablica liczb jeszcze nie istnieje, przygotowaliśmy tylko
    // miejsce (zmienną), w którą można będzie wpisać referencję do obiektu tablicowego.
    // Obiekt ten musimy dopiero stworzyć.
    //
    // tab1 -----> ???

    tab1 = new double[8];
    // a.  new double[8] => |0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|
    // b.       tab1 =      |0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|;
    // c.       tab1 -----> |0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|;

    // 4, 3, 6, 7, 8, 1, 1, 4

    // Elementy tablicy są identyfikowane przy użyciu tzw. indeksów, tj.
    // liczb naturalnych 0, 1, 2, ..., gdzie element pierwszy N-elementowej tablicy
    // ma indeks 0, a element ostatni ma indeks N-1.
    tab1[0] = 4; // |4.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|
    tab1[1] = 3; // |4.0|3.0|0.0|0.0|0.0|0.0|0.0|0.0|
    tab1[2] = 6; // |4.0|3.0|6.0|0.0|0.0|0.0|0.0|0.0|
    // ...
    tab1[7] = 4; // |4.0|3.0|6.0|7.0|8.0|1.0|1.0|4.0|

    // TRZEBA TO UPROŚCIĆ:
    double[] tab2 = new double[] {4, 3, 6, 7, 8, 1, 1, 4};
    // a nawet
    double[] tab3 = {4, 3, 6, 7, 8, 1, 1, 4};

    int len = tab3.length; // tab3.length - długość tablicy (STAŁA!!!)
    System.out.println(len);

    // Musimy mieć l. całkowitą pełniącą rolę indeksu, którą będziemy "jeździć"
    // po elementach tablicy od pierwszego do ostatniego (od indeksu 0 do 7).
    double sum = 0;
    int i = 0;
    // while (<predicate>) {
    //   <body>
    // }

    while (i <= 7) {
      sum = sum + tab3[i];
      i = i + 1;
    }

    double mean = sum / tab3.length;
    System.out.println("Suma elementów w tablicy wynosi " + sum);
    System.out.println("Średnia elementów w tablicy wynosi " + mean);

    i = -1;
    sum = 0;
    while (i <= 7) { // Próbuję się odwołać do nieistniejącej komórki w tab3.
      sum = sum + tab3[i];
      i = i + 1;
    }
  }

}
