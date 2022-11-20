package edu.san.transactions.entity;

import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.transactions.control.Transactor;
import io.quarkus.narayana.jta.runtime.context.TransactionContext;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class TransactorImpl implements Transactor {

  @Transactional
  @Override
  public void inTransaction(Runnable runnable) {
    runnable.run();
  }

  @Transactional
  @Override
  public <T> T inTransaction(Supplier<T> supplier) {
    return supplier.get();
  }

  @Override
  public boolean isActiveTransaction() {
    return new TransactionContext().isActive();
  }

}
