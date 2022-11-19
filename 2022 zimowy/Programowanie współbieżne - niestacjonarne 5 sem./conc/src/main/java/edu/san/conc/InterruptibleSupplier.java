package edu.san.conc;

@FunctionalInterface
public interface InterruptibleSupplier<T> {
  
  T get() throws InterruptedException;
  
}