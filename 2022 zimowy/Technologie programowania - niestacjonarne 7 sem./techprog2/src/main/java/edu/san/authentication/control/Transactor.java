package edu.san.authentication.control;

import java.util.concurrent.Callable;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;

@Port(PortType.OUTPUT)
public interface Transactor {

  void invoke(Runnable runnable);

  <T> T invoke(Callable<T> callable);

  boolean isActiveTransaction();
}
