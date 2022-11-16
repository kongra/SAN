package edu.san.commons.entity;

import java.util.concurrent.Callable;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.commons.control.Transactor;
import io.quarkus.narayana.jta.runtime.context.TransactionContext;
import telsos.Exceptions;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;

@ApplicationScoped
@Adapter(AdapterType.SECONDARY)
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
