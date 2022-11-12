package edu.san.authentication.entity;

import java.util.concurrent.Callable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.Transactor;
import edu.san.hexagonal.Adapter;
import edu.san.hexagonal.PortOrAdapterType;
import io.quarkus.narayana.jta.runtime.context.TransactionContext;
import telsos.Exceptions;

@ApplicationScoped
@Adapter(PortOrAdapterType.OUTBOUND)
class TransactorImpl implements Transactor {

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
    } catch (final Exception e) {
      return Exceptions.fail(e);
    }
  }

  @Override
  public boolean isActiveTransaction() {
    return new TransactionContext().isActive();
  }

}
