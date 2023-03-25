// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.animals;

public class Zoo {

  public static void main(String... args) {
    final var a1 = new Elephant();
    final var a2 = new Zebra();
    final var a3 = new Lion();

    final var zooKeeper = new ZooKeeper("Tom");
    zooKeeper.feed("Delicious grass", a1, a2, a3);

  }

}
