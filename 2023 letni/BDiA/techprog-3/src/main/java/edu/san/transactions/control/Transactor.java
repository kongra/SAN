// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.transactions.control;

//import telsos.architecture.hexagonal.annotations.Port;
//import telsos.architecture.hexagonal.annotations.PortType;

import java.util.function.Supplier;

// @Port(PortType.OUTPUT)
public interface Transactor {

  void inTransaction(Runnable runnable);

  <T> T inTransaction(Supplier<T> supplier);

  boolean isActiveTransaction();
}
