package san.jipp;

import org.junit.jupiter.api.Test;
import san.utils.IntArray;

public class IntArrayTest {

  @Test
  void testCreation() {
    IntArray a1 = IntArray.ofLenght(10);
    System.out.println(a1);

    IntArray a2 = IntArray.initWith(10, 1);
    a2.setFillPointer(5);
    System.out.println(a2);

    IntArray a3 = IntArray.ofValues(10, 1, 2, 3);
    a3.setFillPointer(5);
    System.out.println(a3);
  }

}
