// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program13 {

  public static void main(String[] args) {
    try (DynVar<Long> number = DynVar.initially(0L)) {
      System.out.println("--1 " + number.value());
      
      number.binding(5L, () -> {
        System.out.println("--2 " + number.value());
        number.binding(7L, () -> {
          System.out.println("--3 " + number.value());
        });
      });
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
