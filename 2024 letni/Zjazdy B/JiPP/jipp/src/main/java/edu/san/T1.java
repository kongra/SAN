// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

//Haskell:
//String -> Int - STRUKTURALNIE OKREŚLONY typ funkcji, która przyjmuje String i zwraca Int
//type T1 = String -> Int
//     T1 jest typem nominalnym (nominalnie określonym)
@FunctionalInterface
public interface T1 {

  int call(String s);

}
