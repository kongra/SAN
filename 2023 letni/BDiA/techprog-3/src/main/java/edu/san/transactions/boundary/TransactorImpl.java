// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.transactions.boundary;

import java.util.function.Supplier;

import edu.san.transactions.control.Transactor;
import io.quarkus.narayana.jta.runtime.context.TransactionContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

// @Adapter(AdapterType.SECONDARY)
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
