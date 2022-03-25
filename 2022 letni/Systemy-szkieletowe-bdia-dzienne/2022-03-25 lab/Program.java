import telsos.Agent;

class Program {

  static class Foo {
    int n; // 4B
  }

  static class Goo {
    long n; // 8B
  }

  static class Moo {
    long n;    // 8B
    boolean b; // 1B
  }

  static class Joo {
    int n;     // 4B
    boolean b; // 1B
  }

  public static void main(String... args) {
    System.out.println("Test agenta");

    var instr = Agent.instrumentation();
    System.out.println("Pobrałem " + instr);

    // Pytanie: Jak wyjaśnić poniższe wyniki???
    System.out.println("Wielkość Object to " + instr.getObjectSize(new Object())); // 16B
    System.out.println("Wielkość Foo    to " + instr.getObjectSize(new Foo()));    // 16B
    System.out.println("Wielkość Goo    to " + instr.getObjectSize(new Goo()));    // 24B
    System.out.println("Wielkość Moo    to " + instr.getObjectSize(new Moo()));    // 24B
    System.out.println("Wielkość Joo    to " + instr.getObjectSize(new Joo()));    // 24B
  }

}
