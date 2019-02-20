package san.conc;

import san.utils.Seq;
import san.utils.Seqs;

public class Program5 {

  public static void main(String... args) {
    Seq<Long> ns = Seqs.naturals();
    Seq<Double> xs = Seqs.map((n) -> (double) n * n, ns);

    Seq<Long> ns1 = Seqs.take(10, ns);
    System.out.println(Seqs.reduce((m, n) -> m + n, 0L, ns1));

//    System.out.println(ns.first());
//    System.out.println(ns.rest().first());
//    System.out.println(ns.rest().rest().rest().rest().rest().rest().first());
//
//    System.out.println(xs.first());
//    System.out.println(xs.rest().first());
//    System.out.println(xs.rest().rest().rest().rest().rest().rest().first());

  }

}
