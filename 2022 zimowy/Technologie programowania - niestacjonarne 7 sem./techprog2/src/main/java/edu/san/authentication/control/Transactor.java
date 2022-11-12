package edu.san.authentication.control;

import java.util.concurrent.Callable;

import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;

@Port(PortOrAdapterType.OUTBOUND)
public interface Transactor {

  void invoke(Runnable runnable);

  <T> T invoke(Callable<T> callable);

  boolean isActiveTransaction();
}
