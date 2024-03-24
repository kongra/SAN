// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    final T1 fun = String::length;    
    final var n = fun.call("abcdef");
    System.out.println(n);
  }

}
