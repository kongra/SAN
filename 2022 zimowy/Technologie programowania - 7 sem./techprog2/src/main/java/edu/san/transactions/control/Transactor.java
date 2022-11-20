package edu.san.transactions.control;

import java.util.function.Supplier;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;

@Port(PortType.OUTPUT)
public interface Transactor {

  void inTransaction(Runnable runnable);

  <T> T inTransaction(Supplier<T> supplier);

  boolean isActiveTransaction();
}
