package test.san.coll;

import static org.junit.Assert.*;

import org.junit.Test;

import san.coll.ISeq;
import san.coll.Nil;
import san.coll.Seqs;
import san.math.Bfn;
import san.math.Ufn;

public class TestLinkedSeq {

  @Test
  public void test() {
    // ISeq<Integer> lst1 = Nil.nil.cons(3).cons(2).cons(1);
    // System.out.println(lst1);
    //
    // Ufn<Integer, Integer> intSquare = new Ufn<Integer, Integer>() {
    // @Override
    // public Integer call(Integer i) {
    // return i * i;
    // }
    // };
    //
    // Bfn<Integer, Integer, Integer> intSum =
    // new Bfn<Integer, Integer, Integer>() {
    // @Override
    // public Integer call(Integer x, Integer y) {
    // return x + y;
    // }
    // };
    //
    // ISeq<Integer> lst2 = Seqs.map(intSquare, lst1);
    // System.out.println(lst2);
    // System.out.println(Seqs.reduce(intSum, 0, lst2));
    //
    // Adder.F goo = Adder.adder(5);
    // System.out.println(goo);
    //
    // Adder.F moo = Adder.adder(6);
    // System.out.println(moo);

    ISeq<Long> n = Seqs.naturals();
    ISeq<Long> first10 = Seqs.take(10, n);
    System.out.println(first10);
    
    ISeq<Long> lst2 = n.cons(3L).cons(4L).cons(5L);
    System.out.println(Seqs.take(20, lst2));
  }

}
