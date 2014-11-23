package test.san.coll;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import san.math.Delay;
import san.math.Nfn;

public class TestDelay {

  @Test
  public void test() {
    List<Integer> l = new ArrayList<Integer>(10000);

    Delay<Integer> sum = new Delay<Integer>(new Nfn<Integer>() {
      @Override
      public Integer call() {
        System.out.println("Obliczam");
        return 1 + 2;
      }
    });

    System.out.println(sum);
    System.out.println(sum.deref());
    System.out.println(sum);
    System.out.println(sum);
  }

}
