// Copyright (c) Konrad Grzanek
// Created 28.11.2020

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadTalks {

  private static class Accounts {
    private static long account1 = 5000;
    private static long account2 = 5000;
    private static final Semaphore s1 = new Semaphore(1, true);

    public void addSubtract(long delta) {
      ThreadTools.run(s1::acquire);
      try {
        account1 = account1 - delta;
        account2 = account2 + delta;
      } finally {
        s1.release();
      }
    }

    public void dump() {
      ThreadTools.run(s1::acquire);
      try {
        System.out.println("Total " + (account1 + account2));
        System.out.println("account1 " + account1);
        System.out.println("account2 " + account2);
      } finally {
        s1.release();
      }
    }
  }

  private static final Accounts accounts = new Accounts();
  private static final int N = 100;

  public static void main(String... args) {
    final List<Thread> threads = new ArrayList<>(N);
    final Random rnd = new SecureRandom();

    System.out.println("Przed wszystkim");
    accounts.dump();

    for (int i = 0; i < N; i++) {
      final long n = 1;
      final var th = new Thread(() -> {
        accounts.addSubtract(n);
      });

      threads.add(th);
      th.start();
    }

    System.out.println("Czekam na zakończenie wątków");
    for (Thread th : threads) {
      ThreadTools.run(th::join);
    }

    System.out.println("Po wszystkim");
    accounts.dump();
  }

}
