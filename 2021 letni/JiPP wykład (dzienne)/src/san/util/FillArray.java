package san.util;

public interface FillArray<T> { // T - zmienna typowa (ang. type-variable)
  // W miejsce T (zmiennej typowej) można "podstawić" różne typy (klasy)
  // W Javie mogą to być WYŁĄCZNIE klasy, natomiast w innych językach (Haskell, C++)
  // mogą się tam pojawiać również typy proste, int (C++), Int (Haskell).

  // Od tej pory FillArray NIE JEST już TYPEM (klasą). Jest KONSTRUKTOREM typów.
  // FillArray <T : class>  => ??? : class
  // np. FillArray<Integer> => ???
  //     FillArray<String>  => ...
  // FillArray<String> != FillArray<Integer>

  // FillArray jest przykładem TYPU GENERYCZNEGO

  FillArray add(T x);

  T get(int i);

  int length();

  int maxLength();

}
