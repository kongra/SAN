package san.jipp;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import san.utils.IntArray;

public class IntArrayTest {

  @Test
  void testCreation() {
    IntArray a1 = IntArray.ofLenght(10);
    // System.out.println(a1);

    IntArray a2 = IntArray.initWith(10, 1);
    a2.setFillPointer(5);
    // System.out.println(a2);

    IntArray a3 = IntArray.ofValues(10, 1, 2, 3);
    a3.setFillPointer(5);
    // System.out.println(a3);
  }

  @Test
  void testFillPointer() {
    IntArray a1 = IntArray.initWith(10, 3);
    System.out.println(a1);
    assertThat(a1.fillPointer()).isEqualTo(0);

    a1.setFillPointer(5);
    assertThat(a1.fillPointer()).isEqualTo(5);
    System.out.println(a1);

    assertThatThrownBy(() -> a1.setFillPointer(10)).
        isInstanceOf(IllegalArgumentException.class);

    IntArray a2 = IntArray.ofValues(10, 4, 5, 6, 7);
    System.out.println(a2);
    assertThat(a2.fillPointer()).isEqualTo(4);
    a2.push(8);
    assertThat(a2.fillPointer()).isEqualTo(5);
    System.out.println(a2);

    a2.setFillPointer(2);
    System.out.println(a2);

  }

}
