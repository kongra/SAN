class Program {

  public static void main(String[] args) {
    int n = 1; // 32b = 4B
    System.out.println(n);

    n = 3;
    System.out.println(n);

    System.out.println(Integer.MIN_VALUE);
    System.out.println(Integer.MAX_VALUE);

    byte  b = 0; //  8b = 1B, -128 .. 0 .. 127
    short c = b; // 16b = 2B, -??  .. 0 .. ??
    int   i = c; // 32b = 4B, ... (D)
    long  l = i; // 64b = 8B, ...

    //    A <: B - A jest PODTYPEM B (A jest PODZBIOREM B)
    // Jeżeli A <: B oraz x należy do A, to należy on również do B
    // A < B
    // Ada: N : Positive := 2;

    float  x = 0.25f; // 32b
    double d = x;     // 64b (D)

    // 1/3 = 0.3333333333...

    x = l;
    char c1 = 'ą'; // 16b

    // byte <: short <: int <: long <: float <: double
    //         char  <: int <: long <: float <: double

  }

}
