// Copyright (c) Konrad Grzanek
// Created 24.10.2020

@FunctionalInterface
public interface Interruptible {

  void run() throws InterruptedException;

}
