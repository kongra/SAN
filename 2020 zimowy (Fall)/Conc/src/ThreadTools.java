// Copyright (c) Konrad Grzanek
// Created 24.10.2020

import org.jetbrains.annotations.NotNull;

public class ThreadTools {

  public static void run(@NotNull Interruptible body) {
    try {
      body.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void sleep(long millis) {
    run(() -> {
      Thread.sleep(millis);
    });
  }

}
