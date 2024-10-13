// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.accounts;

import edu.san.bank.money.Currency;
import edu.san.bank.money.Money;

public interface Account {

  Currency currency(); // Q

  Money getBalance(); // Q

  void addToBalance(Money money); // C

  void subtractFromBalance(Money money); // C
}
