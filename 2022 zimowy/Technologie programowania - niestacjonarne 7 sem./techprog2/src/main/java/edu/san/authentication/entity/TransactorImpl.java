package edu.san.authentication.entity;

import java.util.concurrent.Callable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.Transactor;
import edu.san.hexagonal.Adapter;
import edu.san.hexagonal.PortOrAdapterType;
import telsos.Exceptions;

@ApplicationScoped
@Adapter(PortOrAdapterType.OUTBOUND)
public class TransactorImpl implements Transactor {

  @Transactional
  @Override
  public void invoke(Runnable runnable) {
    runnable.run();
  }

  @Transactional
  @Override
  public <T> T invoke(Callable<T> callable) {
    try {
      return callable.call();
    } catch (Exception e) {
      return Exceptions.fail(e);
    }
  }

}
